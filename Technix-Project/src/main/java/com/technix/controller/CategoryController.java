package com.technix.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.technix.custome.IdNotFoundException;
import com.technix.dto.Views;
import com.technix.entity.Category;
import com.technix.repository.CategoryRepository;
import com.technix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepo;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Category> createCategory(@RequestParam("companyId") int companyId,
                                                   @RequestParam("categoryName") String categoryName,
                                                   @RequestParam(value = "description", required = false) String description,
                                                   @RequestParam(value = "image", required = false) MultipartFile image,
                                                   @RequestParam("parentCategoryId") int parentCategoryId,
                                                   @RequestParam("createdBy") int createdBy) throws Exception {
        Category ctg = new Category();
        ctg.setCompanyId(companyId);
        ctg.setCategoryName(categoryName);
        ctg.setDescription(description);
        ctg.setParentCategoryId(parentCategoryId);
        ctg.setCreatedBy(createdBy);
        ctg.setCreatedAt(LocalDateTime.now());

        return categoryService.createCategory(ctg, image);
    }

    @PutMapping(value = "/update/{categoryId}", consumes = "multipart/form-data")
    public ResponseEntity<Category> updateCategory(
            @RequestParam("categoryId") int categoryId,
            @RequestParam("companyId") int companyId,
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("parentId") int parentId,
            @RequestParam("createdBy") int createdBy) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category ctg = optionalCategory.get();
            ctg.setCategoryId(categoryId);
            ctg.setCompanyId(companyId);
            ctg.setCategoryName(categoryName);
            ctg.setDescription(description);
            ctg.setParentCategoryId(parentId);
            ctg.setCreatedBy(createdBy);
            ctg.setCreatedAt(LocalDateTime.now());

            return categoryService.updateCategory(ctg, image);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @GetMapping("/getCategory/{categoryId}")
    @JsonView(Views.ChildView.class)
    public ResponseEntity<Map<String, Object>> getSupplierById(@RequestParam("categoryId") int categoryId) {
        Category category = categoryService.getCategoryById(categoryId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("category", category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCategoryByCompany/{companyId}")
    @JsonView(Views.ChildView.class)
    public ResponseEntity<Map<String, Object>> getCategoryByCompanyId(@RequestParam("companyId") int companyId) {
        List<Category> category = categoryService.getCategoryByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("category", category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/image/{categoryId}")
    public ResponseEntity<UrlResource> getCategoryImage(@RequestParam("categoryId") int categoryId) throws Exception {
        return categoryService.getCategoryImage(categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Map<String, Object>> deleteCategoryById(@RequestParam("categoryId") int categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }
}
