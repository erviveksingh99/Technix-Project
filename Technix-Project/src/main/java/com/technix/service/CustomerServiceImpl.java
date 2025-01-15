package com.technix.service;

import com.technix.custome.EmailNotFoundException;
import com.technix.entity.Customer;
import com.technix.entity.User;
import com.technix.repository.CustomerRepository;
import com.technix.repository.UserRepository;
import com.technix.utility.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepo;
    private UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${userImage.url}")
    private String imageUrl;

    @Autowired
    public CustomerServiceImpl(AuthenticationManager authenticationManager,
                               JwtUtil jwtUtil,
                               PasswordEncoder passwordEncoder,
                               UserRepository userRepo,
                               CustomerRepository customerRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.customerRepo = customerRepo;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> signup(Customer customer,
                                                      MultipartFile profilePic,
                                                      int google,
                                                      String password, int roleId) throws Exception {

        // Check if the customer email already exists in the database
        Optional<Customer> customerEmail = Optional.empty();
        try {
            customerEmail = customerRepo.findByEmail(customer.getEmail());
        } catch (EmailNotFoundException e) {
            throw new EmailNotFoundException("Email not found");
        }

        if (customerEmail.isPresent()) {
            // If email exists, return error message
            String result = "Email already exists !!";
            Boolean emailExists = false;

            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            response.put("status", emailExists);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // Return HTTP 409 Conflict
        }

        // Setting the creation timestamp
        LocalDateTime dateTime = LocalDateTime.now();
        customer.setCreatedAt(dateTime);

        // Save the customer entity first
        Customer savedCustomer = null;
        try {
            savedCustomer = customerRepo.save(customer);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save customer", e);
        }

        // Creating the User entity and setting fields from Customer
        User user = new User();
        user.setCustomerId(savedCustomer.getCustomerId());  // Set the customer foreign key relationship
        user.setName(customer.getFullName());
        user.setOrganisationName(customer.getCompanyName());
        user.setEmail(customer.getEmail());
        if (google == 0) {
            user.setPassword(passwordEncoder.encode(password));
        } else {
            user.setPassword(null);
        }
        user.setPhoneNumber(customer.getPhoneNumber());
        user.setRoleId(roleId);
        user.setUserType("ADMIN");

        // Save the company first to generate an ID for the folder
        User savedUser = userRepo.save(user);
        int userId = savedUser.getUserId();
        //****************************************************
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

        // Other user fields
        user.setStatus(1);  // Assuming 1 means active
        user.setCreatedAt(dateTime);
        user.setImageUrl(imageUrl + userId);
        Optional<Customer> customer1 = customerRepo.findByEmail(customer.getEmail());
        if (customer1.isPresent()) {
            Customer foundCustomer = customer1.get();
            user.setCreatedBy(foundCustomer.getCustomerId());  // Customer created the user
        } else {
            throw new EmailNotFoundException("Email not found");
        }
        // Save the user entity
        try {
            userRepo.save(user);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save user", e);
        }

        // Return success message in JSON format
        String result = "Signup successful !!";
        Boolean status = true;
        //optional
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("status", status);
        response.put("Customer", savedCustomer);
        response.put("User", user);
        return ResponseEntity.ok(response);  // Return HTTP 200 OK with the success message
    }


}

