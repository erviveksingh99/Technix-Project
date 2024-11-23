package com.technix.service;

import com.technix.entity.Brand;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public ResponseEntity<Brand> createBrand(Brand brand, int companyId);

    public ResponseEntity<Brand> updateBrand(Brand brand, int companyId);

    public ResponseEntity<Brand> getBrandById(int brandId);

    public ResponseEntity<List<Brand>> getBrandByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteBrandById(int brandId);
}
