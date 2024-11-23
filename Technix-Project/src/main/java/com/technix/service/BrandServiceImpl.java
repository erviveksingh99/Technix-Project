package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Brand;
import com.technix.entity.Company;
import com.technix.repository.BrandRepository;
import com.technix.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private CompanyRepository companyRepo;


    @Override
    public ResponseEntity<Brand> createBrand(Brand brand, int companyId) {
        try {
            Optional<Company> company = companyRepo.findById(companyId);
            if (company.isPresent()) {
                brand.setCompanyId(companyId);
                return ResponseEntity.ok(brandRepo.save(brand));
            } else {
                throw new IdNotFoundException("Company not found");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save brand data");
        }
    }


    @Override
    public ResponseEntity<Brand> updateBrand(Brand brand, int companyId) {
        Optional<Brand> optionalBrand = brandRepo.findById(brand.getBrandId());
        if (optionalBrand.isPresent()) {
            Optional<Company> company = companyRepo.findById(companyId);
            if (company.isPresent()) {
                brand.setCompany(company.get());
                return ResponseEntity.ok(brandRepo.save(brand));
            } else {
                throw new IdNotFoundException("Company id not found");
            }
        } else {
            throw new IdNotFoundException("Brand id not found");
        }
    }

    @Override
    public ResponseEntity<Brand> getBrandById(int brandId) {
        Brand brand = brandRepo.findById(brandId)
                .orElseThrow(() -> new IdNotFoundException("Brand ID not found"));
        return ResponseEntity.ok(brand);
    }

    @Override
    public ResponseEntity<List<Brand>> getBrandByCompanyId(int companyId) {
        List<Brand> brand = brandRepo.findByCompanyId(companyId);
        if (!brand.isEmpty()) {
            return ResponseEntity.ok(brand);
        } else {
            throw new IdNotFoundException("company id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBrandById(int brandId) {
        Optional<Brand> optionalBrand = brandRepo.findById(brandId);
        if (optionalBrand.isPresent()) {
            brandRepo.deleteById(brandId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Brand data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
