package com.technix.controller;

import com.technix.custome.IdNotFoundException;
import com.technix.dto.RegisterRequest;
import com.technix.entity.User;
import com.technix.repository.UserRepository;
import com.technix.service.UserService;
import com.technix.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // this PostMapping for swagger to send data in the form of form data.
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam("name") String name,
                                                          @RequestParam("organisation_name") String organisationName,
                                                          @RequestParam("email") String email,
                                                          @RequestParam("phone_number") String phoneNumber,
                                                          @RequestParam(value = "profilePic", required = false) MultipartFile profilePic,
                                                          @RequestParam("user_type") String userType,
                                                          @RequestParam("role_id") int roleId,
                                                          @RequestParam("google") int google,
                                                          @RequestParam("password") String password,
                                                          @RequestParam("status") int status,
                                                          @RequestParam("created_by") Integer createdBy,
                                                          @RequestParam("customer_id") int customerId) throws Exception {
        User user = new User();
        user.setName(name);
        user.setOrganisationName(organisationName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setUserType(userType);
        user.setRoleId(roleId);
        user.setGoogle(google);
        user.setPassword(password);
        user.setStatus(status);
        user.setCreatedBy(createdBy);
        user.setCustomerId(customerId);

        return userService.createUser(user, profilePic);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam("email") String email,
                                              @RequestParam("password") String password,
                                              HttpServletRequest request) throws Exception {

        RegisterRequest loginRequest = new RegisterRequest();
        log.debug("Login api is hited: {}", loginRequest.getEmail());
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        return userService.login(loginRequest, request);
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping(value = "/update/{userId}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateUser(@PathVariable int userId,
                                        @RequestParam("name") String name,
                                        @RequestParam("organisation_name") String organisationName,
                                        @RequestParam("email") String email,
                                        @RequestParam("phone_number") String phoneNumber,
                                        @RequestParam(value = "profilePic", required = false) MultipartFile profilePic,
                                        @RequestParam("user_type") String userType,
                                        @RequestParam("role_id") int roleId,
                                        @RequestParam("google") int google,
                                        @RequestParam("password") String password,
                                        @RequestParam("status") int status,
                                        @RequestParam("created_by") Integer createdBy,
                                        @RequestParam("customer_id") int customerId) throws Exception {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserId(userId);
            user.setName(name);
            user.setOrganisationName(organisationName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setUserType(userType);
            user.setRoleId(roleId);
            user.setGoogle(google);
            user.setStatus(status);
            user.setCreatedBy(createdBy);
            user.setCustomerId(customerId);

            return userService.updateUser(user, profilePic, password);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        return userService.deleteUserById(userId);
    }

    @GetMapping("/profilePic/{userId}")
    public ResponseEntity<UrlResource> getProfilePictureById(@PathVariable int userId) throws Exception {
        return userService.getProfilePicture(userId);
    }
}



