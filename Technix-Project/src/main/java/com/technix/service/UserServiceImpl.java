package com.technix.service;

import com.technix.custome.EmailNotFoundException;
import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.dto.RegisterRequest;
import com.technix.entity.User;
import com.technix.entity.UserLogRecord;
import com.technix.repository.UserLogRecordRepository;
import com.technix.repository.UserRepository;
import com.technix.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${userImage.url}")
    private String imageUrl;

    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserLogRecordRepository userLogRecordRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepo,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    //User Register
    @Override
    public ResponseEntity<Map<String, Object>> createUser(User user, MultipartFile profilePic) throws Exception {
        try {
            Optional<User> findUser = userRepo.findByEmail(user.getEmail());
            if (findUser.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                Boolean status = false;
                response.put("Status", status);
                response.put("Message", "Email already exist");
                return ResponseEntity.ok(response);
            }
        } catch (EmailNotFoundException e) {
            throw new EmailNotFoundException("Email not found");
        }
        if (user.getGoogle() == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        user.setCreatedAt(LocalDateTime.now());

        //****************************************************

        // Save the company first to generate an ID for the folder
        User savedUser = userRepo.save(user);
        int userId = savedUser.getUserId();
        // Handle profile picture upload
        if (profilePic != null && !profilePic.isEmpty()) {
            try {

//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

                // Define company directory with ID
                String companyDirectoryWithId = "/users/" + userId;
                File directory = new File(uploadDir + File.separator + "users" + File.separator + userId);

                // Create the directory if it doesn't exist
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String exte = profilePic.getOriginalFilename().substring(profilePic.getOriginalFilename().lastIndexOf("."));

                // Generate the file name and path where the logo will be saved
                String filePath = directory.getAbsolutePath() + File.separator + userId + exte;

                // Save the file to the disk
                profilePic.transferTo(new File(filePath));

                // Set the relative path to the company entity (this will be stored in the DB)
                user.setProfilePicture(companyDirectoryWithId + "/" + userId + exte);

                // Save the updated company entity with the logo path
            } catch (IOException e) {
                throw new Exception("Failed to save company logo: " + e.getMessage());
            }
        }
        //****************************************************

        try {
            user.setImageUrl(imageUrl + userId);
            userRepo.save(user);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("User", user);
        response.put("Status", true);
        return ResponseEntity.ok(response);
    }

    //User login
    @Override
    public ResponseEntity<?> login(RegisterRequest loginRequest, HttpServletRequest request) throws Exception {
        Optional<User> findUser = userRepo.findByEmail(loginRequest.getEmail());
        if (findUser.isPresent()) {
            //Original
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            } catch (BadCredentialsException e) {
                //throw new Exception("Bad Credential", e);
                throw new Exception("email password not matched", e);
            }
            User user = findUser.get();
            user.setImageUrl(imageUrl + user.getUserId());

            //****************  Add user log records  ******************************//
            UserLogRecord userLogRecord = new UserLogRecord();
            int id = findUser.get().getUserId();
            Optional<User> userId = userRepo.findById(id);

            // log.info("User id found :{}",userId.get().getUser_id());
            if (userId.isPresent()) {
                userLogRecord.setUser(userId.get());
            } else {
                throw new IdNotFoundException("User id not found");
            }

            // Set basic user log info
            userLogRecord.setIp_address("49.47.132.152");
            userLogRecord.setBrowser(request.getHeader("User-Agent"));
            userLogRecord.setPlatform(request.getHeader("platform"));

            // Make a request to the IPInfo service to get IP details
            // String url = "https://ipinfo.io/" + userLogRecord.getIp_address() + "/json";
            String url = "https://ipinfo.io/" + "49.47.132.152" + "/json";

            Map<String, Object> ipInfoMap = restTemplate.getForObject(url, Map.class);

            // log.info("User id found :{}",ipInfoMap.get("city"));

            try {
                if (ipInfoMap != null) {
                    // Populate UserLogRecord with IPInfo data
                    userLogRecord.setCity((String) ipInfoMap.get("city"));
                    userLogRecord.setRegion((String) ipInfoMap.get("region"));
                    userLogRecord.setCountry((String) ipInfoMap.get("country"));
                    userLogRecord.setPostal((String) ipInfoMap.get("postal"));
                    userLogRecord.setLocation((String) ipInfoMap.get("loc")); // "loc" contains latitude,longitude
                    userLogRecord.setIsp((String) ipInfoMap.get("org")); // "org" contains ISP info
                }

                // Set login timestamp
                userLogRecord.setLogin_at(LocalDateTime.now());

                // Save the record to the database and return the response
                UserLogRecord savedRecord = userLogRecordRepository.save(userLogRecord);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data base communication failed ", e);
            }

            //**************** Generate token and send the request  *****************//
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Successful");
            // response.put("User log record", "Added Successful");
            response.put("user", user);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            throw new EmailNotFoundException("Email not found");
        }
    }

    @Override
    public List<User> getAllUser() {
        try {
            List<User> usersList = userRepo.findAll();
            for (int i = 0; i < usersList.size(); i++) {
                usersList.get(i).setImageUrl(imageUrl + usersList.get(i).getUserId());
            }
            return usersList;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get all users data", e);
        }
    }

    @Override
    public ResponseEntity<?> getUserById(int userId) {
        Optional<User> findByUserId = userRepo.findById(userId);
        if (findByUserId.isPresent()) {
            User user = findByUserId.get();
            user.setImageUrl(imageUrl + userId);
            return ResponseEntity.ok(user);
        } else {
            String result = "UserId not found";
            Boolean status = false;
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", status);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(User user, MultipartFile profilePic, String password) throws Exception {
        Optional<User> findByUserId;
        try {
            findByUserId = userRepo.findById(user.getUserId());
        } catch (IdNotFoundException e) {
            throw new IdNotFoundException("Id not found");
        }

        if (findByUserId.isPresent()) {

            //****************************************************
            // Handle profile picture upload
            if (profilePic != null && !profilePic.isEmpty()) {
                try {

//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

                    int userId = user.getUserId();
                    // Define company directory with ID
                    String companyDirectoryWithId = "/users/" + userId;
                    File directory = new File(uploadDir + File.separator + "users" + File.separator + userId);

                    // Create the directory if it doesn't exist
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String exte = profilePic.getOriginalFilename().substring(profilePic.getOriginalFilename().lastIndexOf("."));

                    // Generate the file name and path where the logo will be saved
                    String filePath = directory.getAbsolutePath() + File.separator + userId + exte;

                    // Save the file to the disk
                    profilePic.transferTo(new File(filePath));

                    // Set the relative path to the company entity (this will be stored in the DB)
                    user.setProfilePicture(companyDirectoryWithId + "/" + userId + exte);
                    user.setImageUrl(imageUrl + userId);

                    // Save the updated company entity with the logo path
                } catch (IOException e) {
                    throw new Exception("Failed to save company logo: " + e.getMessage());
                }
            }

            if (user.getGoogle() == 0) {
                user.setPassword(passwordEncoder.encode(password));
            } else {
                user.setPassword(null);
            }

            ResponseEntity.ok(userRepo.save(user));
            String result = "User updated successfully";
            Boolean status = true;
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", status);
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            String result = "UserId not found";
            Boolean status = false;
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", status);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserById(int userId) {
        Optional<User> findByUserId;
        try {
            findByUserId = userRepo.findById(userId);
        } catch (IdNotFoundException e) {
            throw new IdNotFoundException("Id not found");
        }
        if (findByUserId.isPresent()) {
            userRepo.deleteById(userId);
            String result = "User deleted successfully";
            Boolean status = true;
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", status);
            return ResponseEntity.ok(response);
        } else {
            String result = "User UserId not found";
            Boolean status = false;
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", status);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<UrlResource> getProfilePicture(int id) throws Exception {
        Optional<User> profilePic = userRepo.findById(id);
        if (profilePic.isPresent()) {
            String filePath = uploadDir + profilePic.get().getProfilePicture(); // Get the path to the profile picture
            File file = new File(filePath);

            // Load the file as a resource
            UrlResource resource = new UrlResource(file.toURI());

            // Check if the resource exists and is readable
            if (resource.exists() && resource.isReadable()) {
                // Set headers for the response
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on the actual file type
                headers.setContentDisposition(ContentDisposition.builder("inline").filename(file.getName()).build()); // Use "inline" for displaying in the browser

                // Return the file as a resource
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                throw new FileNotFoundException("File not found: " + filePath);
            }
        } else {
            throw new PictureNotFoundException("Image not found");
        }
    }
}
