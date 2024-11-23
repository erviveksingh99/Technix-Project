package com.technix.service;

import com.technix.entity.Company;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface CompanyService {

    public ResponseEntity<Map<String, Object>> createCompany(Company cmp, MultipartFile logo, int customerId);

    public ResponseEntity<Map<String, Object>> updateCompany(Company cmp, MultipartFile logo, int customerId) throws Exception;

    public ResponseEntity<List<Company>> getAllCompany(int customerId);

    public ResponseEntity<UrlResource> getCompanyLogo(int id) throws Exception;

    public ResponseEntity<Map<String, Object>> deleteCompanyById(int companyId);
}
