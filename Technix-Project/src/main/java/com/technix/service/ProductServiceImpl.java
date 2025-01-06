package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.entity.Category;
import com.technix.entity.Product;
import com.technix.repository.ProductRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                productList.get(i).getCategory().setImageUrl(imageUrl+productList.get(i).getCategory().getCategoryId());
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
                productList.get(i).getCategory().setImageUrl(imageUrl+productList.get(i).getCategory().getCategoryId());
            }
            return (productList);
        } else {
            throw new IdNotFoundException("Brand id not found");
        }
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
