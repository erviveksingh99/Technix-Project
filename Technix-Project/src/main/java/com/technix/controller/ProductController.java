package com.technix.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.technix.custome.IdNotFoundException;
import com.technix.dto.Views;
import com.technix.entity.Product;
import com.technix.repository.ProductRepository;
import com.technix.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> createProduct(@Parameter @ModelAttribute Product product, @RequestParam("file") MultipartFile image) throws Exception {
        Product prod = productService.createProduct(product, image).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("product", prod);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> updateProduct(@Parameter @ModelAttribute Product product, @RequestParam("file") MultipartFile image) throws Exception {
        Optional<Product> productOptional = productRepo.findById(product.getProductId());
        if (productOptional.isPresent()) {
            Product product1 = productOptional.get();
            Product prod = productService.updateProduct(product1, image).getBody();
            Map<String, Object> response = new HashMap<>();
            response.put("product", prod);
            response.put("status", true);
            response.put("message", "Product data is updated");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @GetMapping("/getProductId/{productId}")
//    @JsonView(Views.ParentCategoryView.class)
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable("productId") int productId) {
        Product prod = productService.getProductById(productId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("product", prod);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getOpeningStocks/{productId}")
    public ResponseEntity<Map<String, Object>> getOpeningStock(@PathVariable int productId) {
        List<Double> stocks = productService.getOpeningStock(productId);
        Map<String, Object> response = new HashMap<>();
        response.put("Your opening stocks", stocks);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getReorderLevelByProductId/{productId}")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getReorderLevel(@PathVariable int productId) {
        List<Map<String, Object>> prod = productService.getReorderLevelByProductId(productId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", prod);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllReorderData")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getAllReorderLevel() {
        List<Map<String, Object>> prod = productService.getAllReorderLevel();
        Map<String, Object> response = new HashMap<>();
        response.put("data", prod);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/image/{productId}")
    public ResponseEntity<UrlResource> getProductImage(@PathVariable("productId") int productId) throws Exception {
        return productService.getProductImage(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestParam("productId") int productId) {
        return productService.deleteProduct(productId);
    }
}
