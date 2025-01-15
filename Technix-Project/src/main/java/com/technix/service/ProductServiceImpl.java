package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.entity.Product;
import com.technix.repository.ProductRepository;
import jakarta.transaction.Transactional;
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
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${productImage.url}")
    private String imageUrl;

    @Override
    public ResponseEntity<Product> createProduct(Product product, MultipartFile image) throws Exception {

        Product savedProduct = null;
        int productId;
        try {
            savedProduct = productRepo.save(product);
            productId = savedProduct.getProductId();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }

        //****************************************************
        // Handle profile picture upload
        if (image != null && !image.isEmpty()) {
            try {

//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

                // Define company directory with ID
                String companyDirectoryWithId = "/product/" + productId;
                File directory = new File(uploadDir + File.separator + "product" + File.separator + productId);

                // Create the directory if it doesn't exist
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String exte = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
                // Generate the file name and path where the logo will be saved
                String filePath = directory.getAbsolutePath() + File.separator + productId + exte;

                // Save the file to the disk
                image.transferTo(new File(filePath));

                // Set the relative path to the company entity (this will be stored in the DB)
                product.setImage(companyDirectoryWithId + "/" + productId + exte);

                // Save the updated company entity with the logo path
            } catch (IOException e) {
                throw new Exception("Failed to save company logo: " + e.getMessage());
            }
        }
        product.setImageUrl(imageUrl + productId);
        product.setCreationDate(LocalDateTime.now());
        try {
            return ResponseEntity.ok(productRepo.save(product));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, MultipartFile image) throws Exception {

        Product savedProduct = null;
        int productId;
        try {
            savedProduct = productRepo.save(product);
            productId = savedProduct.getProductId();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }

        //****************************************************
        // Handle profile picture upload
        if (image != null && !image.isEmpty()) {
            try {

//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

                // Define company directory with ID
                String companyDirectoryWithId = "/product/" + productId;
                File directory = new File(uploadDir + File.separator + "product" + File.separator + productId);

                // Create the directory if it doesn't exist
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String exte = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
                // Generate the file name and path where the logo will be saved
                String filePath = directory.getAbsolutePath() + File.separator + productId + exte;

                // Save the file to the disk
                image.transferTo(new File(filePath));

                // Set the relative path to the company entity (this will be stored in the DB)
                product.setImage(companyDirectoryWithId + "/" + productId + exte);

                // Save the updated company entity with the logo path
            } catch (IOException e) {
                throw new Exception("Failed to save company logo: " + e.getMessage());
            }
        }
        product.setImageUrl(imageUrl + productId);
        product.setCreationDate(LocalDateTime.now());
        try {
            return ResponseEntity.ok(productRepo.save(product));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Product> getProductById(int productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            Product product1 = product.get();
            product1.setImageUrl(imageUrl + productId);
            product1.getCategory().setImageUrl(imageUrl + product1.getCategory().getCategoryId());
            return ResponseEntity.ok(product1);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public List<Product> getItemWiseMasterReport(int companyId) {
        List<Product> productList = productRepo.findByCompanyId(companyId);
        if (!productList.isEmpty()) {
            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).setImageUrl(imageUrl + productList.get(i).getProductId());
                productList.get(i).getCategory().setImageUrl(imageUrl + productList.get(i).getCategory().getCategoryId());
            }
            return (productList);
        } else {
            throw new IdNotFoundException("Company id not found");
        }
    }

    @Override
    public ResponseEntity<UrlResource> getProductImage(int productId) throws Exception {
        Optional<Product> productImage = productRepo.findById(productId);
        if (productImage.isPresent()) {
            String filePath = uploadDir + productImage.get().getImage(); // Get the path to the profile picture
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
    public List<Product> getBrandWiseMasterReport(int brandId) {
        List<Product> productList = productRepo.findByBrandId(brandId);
        if (!productList.isEmpty()) {
            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).setImageUrl(imageUrl + productList.get(i).getProductId());
                productList.get(i).getCategory().setImageUrl(imageUrl + productList.get(i).getCategory().getCategoryId());
            }
            return (productList);
        } else {
            throw new IdNotFoundException("Brand id not found");
        }
    }

    @Override
    public List<Product> getProductGroupWise(int categoryId) {
        List<Product> productList = productRepo.findByCategoryId(categoryId);
        if (!productList.isEmpty()) {
            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).setImageUrl(imageUrl + productList.get(i).getProductId());
                productList.get(i).getCategory().setImageUrl(imageUrl + productList.get(i).getCategory().getCategoryId());
            }
            return (productList);
        } else {
            throw new IdNotFoundException("Category id not found");
        }
    }

    @Override
    public List<Product> getHsnCodeWise(String HsnCode) {
        List<Product> productList = productRepo.findByHsnCode(HsnCode);
        if (!productList.isEmpty()) {
            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).setImageUrl(imageUrl + productList.get(i).getProductId());
                productList.get(i).getCategory().setImageUrl(imageUrl + productList.get(i).getCategory().getCategoryId());
            }
            return (productList);
        } else {
            throw new IdNotFoundException("HsnCode not found");
        }
    }

    @Override
    public List<Double> getOpeningStock(int productId) {
        try {
            double stocks = productRepo.getProductStock(productId);
            List<Double> addStocks = new ArrayList<>();
            addStocks.add(stocks);
            return addStocks;
        } catch (Exception e) {
            throw new IdNotFoundException("Product id not found");
        }
    }

    @Transactional
    @Override
    public List<Map<String, Object>> getReorderLevelByProductId(int productId) {
        double getStock = productRepo.getProductStock(productId);
        List<Object[]> results = productRepo.findReorderLevel(getStock, productId);

        List<Map<String, Object>> productDetails = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", result[0]);
            productMap.put("productName", result[1]);
            productMap.put("categoryName", result[2]);
            productMap.put("brandName", result[3]);
            productMap.put("price", result[4]);
            productMap.put("tax", result[5]);
            productMap.put("reorderPoint", result[6]);
            productMap.put("getStock", getStock);

            System.out.println("reorder " + result[6]);
            System.out.println("getstock " + getStock);

            productDetails.add(productMap);
        }
        return productDetails;
    }

    @Override
    public List<Map<String, Object>> getAllReorderLevel() {
        List<Object[]> results = productRepo.findAllReorderLevel();

        List<Map<String, Object>> productDetails = new ArrayList<>();

        for (Object[] result : results) {

            Map<String, Object> productMap = new HashMap<>();
            Double getStock = productRepo.getProductStock((Integer) result[0]);
            productMap.put("productId", result[0]);
            productMap.put("productName", result[1]);
            productMap.put("categoryName", result[2]);
            productMap.put("brandName", result[3]);
            productMap.put("price", result[4]);
            productMap.put("tax", result[5]);
            productMap.put("reorderPoint", result[6]);
            productMap.put("getStock", getStock);

            if (getStock < (Integer) result[6]) {
                productDetails.add(productMap);
            }
        }
        return productDetails;
    }


    @Override
    public ResponseEntity<Map<String, Object>> deleteProduct(int productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            productRepo.deleteById(productId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product has removed");
            response.put("status", true);
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
