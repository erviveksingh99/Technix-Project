package com.technix.controller;

import com.technix.entity.Company;
import com.technix.repository.CompanyRepository;
import com.technix.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepo;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> createCompany(@RequestParam("CustomerId") int customerId,
                                                             @RequestParam("CompanyName") String companyName,
                                                             @RequestParam("BussinessType") String bussinessType,
                                                             @RequestParam("cinNumber") String cinNumber,
                                                             @RequestParam("Address") String address,
                                                             @RequestParam("City") String city,
                                                             @RequestParam("State") String state,
                                                             @RequestParam("Country") String country,
                                                             @RequestParam("PostalCode") String postalCode,
                                                             @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                             @RequestParam("MobileNumber") String mobileNumber,
                                                             @RequestParam(value = "Fax", required = false) String fax,
                                                             @RequestParam("EmailId") String emailId,
                                                             @RequestParam(value = "Website", required = false) String website,
                                                             @RequestParam(value = "Logo", required = false) MultipartFile logo,
                                                             @RequestParam(value = "LogoPosition", required = false) String logoPosition,
                                                             @RequestParam(value = "Logo_on_invoice", required = false) boolean logoOnInvoice,
                                                             @RequestParam("CreatedBy") int createdBy) {

        Company cmp = new Company();
        cmp.setCompanyName(companyName);
        cmp.setBussinesType(bussinessType);
        cmp.setCinNumber(cinNumber);
        cmp.setAddress(address);
        cmp.setCity(city);
        cmp.setState(state);
        cmp.setCountry(country);
        cmp.setPostalCode(postalCode);
        cmp.setPhoneNumber(phoneNumber);
        cmp.setMobileNumber(mobileNumber);
        cmp.setFax(fax);
        cmp.setEmailId(emailId);
        cmp.setWebsite(website);
        cmp.setLogoPosition(logoPosition);
        cmp.setLogoOnInvoice(logoOnInvoice);
        cmp.setCreatedBy(createdBy);
        cmp.setCreatedAt(LocalDateTime.now());
        cmp.setRegistrationDate(LocalDateTime.now());
        return companyService.createCompany(cmp, logo, customerId);
    }


    @PutMapping(value = "/update/{companyId}", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> updateCompany(@PathVariable("companyId") int companyId,
                                                             @RequestParam("CustomerId") int customerId,
                                                             @RequestParam("CompanyName") String companyName,
                                                             @RequestParam("BussinessType") String bussinessType,
                                                             @RequestParam("cinNumber") String cinNumber,
                                                             @RequestParam("Address") String address,
                                                             @RequestParam("City") String city,
                                                             @RequestParam("State") String state,
                                                             @RequestParam("Country") String country,
                                                             @RequestParam("PostalCode") String postalCode,
                                                             @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                             @RequestParam("MobileNumber") String mobileNumber,
                                                             @RequestParam(value = "Fax", required = false) String fax,
                                                             @RequestParam("EmailId") String emailId,
                                                             @RequestParam(value = "Website", required = false) String website,
                                                             @RequestParam(value = "Logo", required = false) MultipartFile logo,
                                                             @RequestParam(value = "LogoPosition", required = false) String logoPosition,
                                                             @RequestParam(value = "Logo_on_invoice", required = false) boolean logoOnInvoice,
                                                             @RequestParam("CreatedBy") int createdBy) throws Exception {
        // Fetch the company by ID
        Optional<Company> existingCompany = companyRepo.findById(companyId);

        if (existingCompany.isPresent()) {
            Company cmp = existingCompany.get();

            // Update fields

            cmp.setCompanyName(companyName);
            cmp.setBussinesType(bussinessType);
            cmp.setCinNumber(cinNumber);
            cmp.setAddress(address);
            cmp.setCity(city);
            cmp.setState(state);
            cmp.setCountry(country);
            cmp.setPostalCode(postalCode);
            cmp.setPhoneNumber(phoneNumber);
            cmp.setMobileNumber(mobileNumber);
            cmp.setFax(fax);
            cmp.setEmailId(emailId);
            cmp.setWebsite(website);
            cmp.setLogoPosition(logoPosition);
            cmp.setLogoOnInvoice(logoOnInvoice);
            cmp.setCreatedBy(createdBy);
            cmp.setCreatedAt(LocalDateTime.now());

            // Update logo if provided
            if (logo != null && !logo.isEmpty()) {
                return companyService.updateCompany(cmp, logo, customerId);  // Update with new logo
            } else {
                return companyService.updateCompany(cmp, null, customerId);  // Update without changing logo
            }
        } else {
            // Handle company not found
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Company not found");
            response.put("status", "false");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCompany/{customerId}")
    public ResponseEntity<List<Company>> getAllCompany(@RequestParam("customerId") int customerId) {
        return companyService.getAllCompany(customerId);
    }

    @GetMapping("/logo/{companyId}")
    public ResponseEntity<UrlResource> getCompanyLogo(@RequestParam("companyId") int companyId) throws Exception {
        return companyService.getCompanyLogo(companyId);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<Map<String, Object>> deleteCompanyById(@RequestParam("companyId") int companyId) {
        return companyService.deleteCompanyById(companyId);
    }
}
