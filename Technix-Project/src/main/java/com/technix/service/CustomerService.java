package com.technix.service;

import com.technix.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CustomerService {
    public ResponseEntity<Map<String, Object>> signup(Customer customer,
                                                      MultipartFile profilePic,
                                                      int google,
                                                      String password, int roleId) throws Exception;

   /*
   public Customer getCustomerById(int id);

    public ResponseEntity<?> customerLogin(RegisterRequest loginRequest, int google) throws Exception;

    public Customer getCustomerByEmail(String email);
    */
}
