package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.entity.Company;
import com.technix.entity.Customer;
import com.technix.entity.FinancialPeriod;
import com.technix.repository.CompanyRepository;
import com.technix.repository.CustomerRepository;
import com.technix.repository.FinancialPeriodRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${companyImage.url}")
    private String imageUrl;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private FinancialPeriodRepository financialPeriodRepo;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ResponseEntity<Map<String, Object>> createCompany(Company cmp, MultipartFile logo, int customerId, LocalDate startDate, LocalDate endDate) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            cmp.setCustomerId(customerId);
        } else {
            throw new IdNotFoundException("Customer id not found");
        }

        try {
            // Handle profile picture upload
            if (logo != null && !logo.isEmpty()) {
                try {

//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

                    // Save the company first to generate an ID for the folder
                    Company savedCompany = companyRepo.save(cmp);
                    int cmpId = savedCompany.getCompanyId();

                    // Define company directory with ID
                    String companyDirectoryWithId = "/company/" + cmpId;
                    File directory = new File(uploadDir + File.separator + "company" + File.separator + cmpId);

                    // Create the directory if it doesn't exist
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String exte = logo.getOriginalFilename().substring(logo.getOriginalFilename().lastIndexOf("."));

                    // Generate the file name and path where the logo will be saved
                    String filePath = directory.getAbsolutePath() + File.separator + cmpId + exte;

                    // Save the file to the disk
                    logo.transferTo(new File(filePath));

                    // Set the relative path to the company entity (this will be stored in the DB)
                    cmp.setLogo(companyDirectoryWithId + "/" + cmpId + exte);

                    cmp.setImageUrl(imageUrl + cmpId);

                    // Save the updated company entity with the logo path
                    Company company = companyRepo.save(cmp);

                    //Creating financial period:
                    FinancialPeriod financialPeriod = new FinancialPeriod();
                    financialPeriod.setCompanyId(company.getCompanyId());
                    financialPeriod.setSDate(startDate);
                    financialPeriod.setEDate(endDate);
                    financialPeriod.setStatus(true);

                    try {
                        financialPeriodRepo.save(financialPeriod);
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Financial Period data saving failed reason: " + e.getMessage());
                    }

                } catch (IOException e) {
                    throw new Exception("Failed to save company logo: " + e.getMessage());
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("Company", cmp);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create company", e);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateCompany(Company cmp, MultipartFile logo, int customerId) throws Exception {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (!customer.isPresent()) {
            throw new IdNotFoundException("Customer id not found");
        }

        cmp.setCustomerId(customerId);

        try {
            // Handle logo upload if the file is provided
            if (logo != null && !logo.isEmpty()) {
                try {
                    // Fetch the company record to get the companyId (if not already present)
                    Optional<Company> existingCompany = companyRepo.findById(cmp.getCompanyId());
                    if (!existingCompany.isPresent()) {
                        throw new IdNotFoundException("Company id not found");
                    }

                    int cmpId = existingCompany.get().getCompanyId();

                    // Define the directory based on the company ID
                    String companyDirectoryWithId = "/company/" + cmpId;
                    File directory = new File(uploadDir + File.separator + "company" + File.separator + cmpId);

                    // Create the directory if it doesn't exist
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String exte = logo.getOriginalFilename().substring(logo.getOriginalFilename().lastIndexOf("."));
                    // Define the full path where the logo will be saved
                    String filePath = directory.getAbsolutePath() + File.separator + cmpId + exte;

                    // Save the file to disk
                    logo.transferTo(new File(filePath));

                    // Set the relative path to be stored in the Company entity (not the absolute path)
                    String relativeLogoPath = companyDirectoryWithId + "/" + cmpId;
                    cmp.setLogo(relativeLogoPath);
                    cmp.setImageUrl(imageUrl + cmpId);
                } catch (IOException e) {
                    throw new Exception("Failed to save company logo: " + e.getMessage());
                }
            }

            // Save the updated company entity in the database
            Company updatedCompany = companyRepo.save(cmp);

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("message", "Company updated successfully");
            response.put("company", updatedCompany);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update company", e);
        }
    }

    @Override
    public ResponseEntity<List<Company>> getAllCompany(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (!customer.isPresent()) {
            throw new IdNotFoundException("Customer id not found");
        }

        // Fetch companies based on customerId
        List<Company> companies = companyRepo.findByCustomerId(customerId);

        for (int i = 0; i < companies.size(); i++) {
            companies.get(i).setImageUrl(imageUrl + companies.get(i).getCompanyId());
        }
        // Return the list of companies in the response
        return ResponseEntity.ok(companies);
    }


    @Override
    public ResponseEntity<UrlResource> getCompanyLogo(int id) throws Exception {
        Optional<Company> companyLogo = companyRepo.findById(id);
        if (companyLogo.isPresent()) {
            String filePath = uploadDir + companyLogo.get().getLogo(); // Get the path to the profile picture
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


    @Override
    public ResponseEntity<Map<String, Object>> deleteCompanyById(int companyId) {
        Optional<Company> deleteCompany = companyRepo.findById(companyId);

        if (deleteCompany.isPresent()) {
            companyRepo.deleteById(companyId);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", true);
            response.put("message", "Company has been deleted");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", false);
            response.put("message", "company id is invalid");
            return ResponseEntity.ok(response);
        }
    }
}
