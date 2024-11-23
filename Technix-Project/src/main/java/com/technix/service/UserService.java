package com.technix.service;

import com.technix.dto.RegisterRequest;
import com.technix.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    public ResponseEntity<Map<String, Object>> createUser(User user, MultipartFile profilePic) throws Exception;

    public List<User> getAllUser();

    public ResponseEntity<?> getUserById(int userId);

    public ResponseEntity<?> updateUser(User user, MultipartFile profilePic, String password) throws Exception;

    public ResponseEntity<?> deleteUserById(int userId);

    public ResponseEntity<?> login(RegisterRequest loginRequest, HttpServletRequest request) throws Exception;

    public ResponseEntity<UrlResource> getProfilePicture(int id) throws Exception;
}
