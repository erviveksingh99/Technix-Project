package com.technix.controller;

import com.technix.entity.Customer;
import com.technix.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Signup for a customer and user
    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> signupCustomer(
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam(value = "phone_number", required = false) String phoneNumber,
            @RequestParam(value = "company_name") String companyName,
            @RequestParam("bussiness_type") String bussinessType,
            @RequestParam("company_size") String companySize,
            @RequestParam("country") String country,
            @RequestParam(value = "profilePic" , required = false) MultipartFile file,
            @RequestParam("google") Byte google,
            @RequestParam("roleId") int roleId,
            @RequestParam("password") String password) throws Exception
    {
        Customer nc = new Customer();
        nc.setFullName(fullname);
        nc.setEmail(email);
        nc.setPhoneNumber(phoneNumber);
        nc.setCompanyName(companyName);
        nc.setBusinessType(bussinessType);
        nc.setCompanySize(companySize);
        nc.setCountry(country);

        // Call service method to process sign-up
        return customerService.signup(nc, file, google, password, roleId);
    }

   /*
   @PostMapping(value = "/login", consumes = "multipart/form-data")
    public ResponseEntity<?> customerLogin(@ModelAttribute RegisterRequest loginRequest, @RequestParam int google) throws Exception {
        return customerService.customerLogin(loginRequest, google);
    }

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/get-email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }
    */


}
