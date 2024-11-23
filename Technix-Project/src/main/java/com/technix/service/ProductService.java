package com.technix.service;

import com.technix.entity.Product;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface ProductService {

    public ResponseEntity<Product> createProduct(Product product, MultipartFile image) throws Exception;

    public ResponseEntity<Product> updateProduct(Product product, MultipartFile image) throws Exception;

    public ResponseEntity<Product> getProductById(int productId);

    public ResponseEntity<List<Product>> getProductByCompanyId(int companyId);

    public ResponseEntity<UrlResource> getProductImage(int productId) throws Exception;

    public ResponseEntity<Map<String, Object>> deleteProduct(int productId);
}
