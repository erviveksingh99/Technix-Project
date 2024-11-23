package com.technix.controller;

import com.technix.entity.Brand;
import com.technix.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createBrand(@RequestParam("companyId") int companyId,
                                                           @RequestParam(value = "brandName") String brandName,
                                                           @RequestParam(value="description", required = false) String description,
                                                           @RequestParam("createdBy") String createdBy) {
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setCreatedBy(createdBy);
        brand.setCreatedAt(LocalDateTime.now());

        Brand brand1 = brandService.createBrand(brand, companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("brand", brand1);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateBrand(@RequestParam("BrandId") int brandId,
                                                           @RequestParam("companyId") int companyId,
                                                           @RequestParam("brandName") String brandName,
                                                           @RequestParam(value = "description", required = false) String description,
                                                           @RequestParam("createdBy") String createdBy) {
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setCreatedBy(createdBy);
        brand.setCreatedAt(LocalDateTime.now());

        Brand updatedBrand = brandService.updateBrand(brand, companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("brand", updatedBrand);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBrand/{brandId}")
    public ResponseEntity<Map<String, Object>> getBrandById(@RequestParam("brandId") int brandId) {
        Brand brandById = brandService.getBrandById(brandId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("brand", brandById);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allBrand/{companyId}")
    public ResponseEntity<List<Brand>> getBrandByCompanyId(@RequestParam("companyId") int companyId) {
        return brandService.getBrandByCompanyId(companyId);
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<Map<String, Object>> deleteBrandById(@RequestParam("brandId") int brandId) {
        return brandService.deleteBrandById(brandId);
    }
}
