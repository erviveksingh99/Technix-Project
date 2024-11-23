package com.technix.service;

import com.technix.entity.Category;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    public ResponseEntity<Category> createCategory(Category category, MultipartFile image) throws Exception;

    public ResponseEntity<Category> updateCategory(Category category, MultipartFile image) throws Exception;

    public ResponseEntity<Category> getCategoryById(int categoryId);

    public ResponseEntity<List<Category>> getCategoryByCompanyId(int companyId);

    public ResponseEntity<UrlResource> getCategoryImage(int id) throws Exception;

    public ResponseEntity<Map<String, Object>> deleteCategoryById(int categoryId);

}
