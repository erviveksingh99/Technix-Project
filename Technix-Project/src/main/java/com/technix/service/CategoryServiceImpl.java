package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.entity.Category;
import com.technix.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
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
import java.util.*;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${categoryImage.url}")
    private String imageUrl;

    @Override
    public ResponseEntity<Category> createCategory(Category category, MultipartFile image) throws Exception {

        // Handle profile picture upload
        if (image != null && !image.isEmpty()) {


//                    log.info("upPath={}", uploadDir);
//                    log.info("path={}", uploadDir + "\\" + "company");

            Category savedCategory;
            try {
                // Save the company first to generate an ID for the folder
                savedCategory = categoryRepo.save(category);
            } catch (ResponseStatusException e) {
                throw new Exception("Failed to save category image: " + e.getMessage());
            }
            int ctgId = savedCategory.getCategoryId();

            // Define company directory with ID
            String categoryDirectoryWithId = "/category/" + ctgId;
            File directory = new File(uploadDir + File.separator + "category" + File.separator + ctgId);

            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String exte = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            // Generate the file name and path where the logo will be saved
            String filePath = directory.getAbsolutePath() + File.separator + ctgId + exte;

            // Save the file to the disk
            image.transferTo(new File(filePath));

            // Set the relative path to the company entity (this will be stored in the DB)
            category.setImage(categoryDirectoryWithId + "/" + ctgId + exte);

            category.setImageUrl(imageUrl + ctgId);

        } else {
            category.setImage(null);
        }

        try {
            // Save the updated category entity with the image path
            // category.setParentCategory(categoryRepo.findById(category.getParentCategoryId()).get());
            return ResponseEntity.ok(categoryRepo.save(category));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category data not saved in database");
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category, MultipartFile image) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(category.getCategoryId());
        Category ctg = optionalCategory.get();
        String imagePath = null;

        // Handle profile picture upload
        if (image != null && !image.isEmpty()) {
            try {
                // Create the upload directory if it doesn't exist
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String filePath = uploadDir + File.separator + image.getOriginalFilename();

                image.transferTo(new File(filePath)); // Save the file to the disk

                imagePath = filePath; // Store the path for the User entity
            } catch (IOException e) {
                throw new Exception("Failed to save image " + e.getMessage());
            }
        }
        // Set the profile picture path in the Company entity
        ctg.setImage(imagePath);
        ctg.setImageUrl(imageUrl + category.getCategoryId());

        try {
            return ResponseEntity.ok(categoryRepo.save(category));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save category data");
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryById(int categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category ctg = categoryOptional.get();
            //  log.info("size:{}",ctg.getChildCategories().size());
            ctg.setImageUrl(imageUrl + categoryId);

            for (int i = 0; i < ctg.getChildCategories().size(); i++) {
                ctg.getChildCategories().get(i).setImageUrl(imageUrl + ctg.getChildCategories().get(i).getCategoryId());
            }

            return ResponseEntity.ok(ctg);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    //******************************Get Category By Company Id*********************************************************************
    @Override
    public ResponseEntity<List<Category>> getCategoryByCompanyId(int companyId) {
        // Fetch parent categories with parent_id = 0
        List<Category> parentCategories = categoryRepo.findByCompanyIdAndParentId(companyId, 0);

        for (int i = 0; i < parentCategories.size(); i++) {
            parentCategories.get(i).setImageUrl(imageUrl + parentCategories.get(i).getCategoryId());
            parentCategories.get(i).getChildCategories().get(i).setImageUrl(imageUrl + parentCategories.get(i).getChildCategories().get(i).getCategoryId());
        }

        if (parentCategories.isEmpty()) {
            throw new IdNotFoundException("No parent categories found for companyId: " + companyId);
        }
        return ResponseEntity.ok(parentCategories);
    }
    //******************************Get Category By Company Id End*********************************************************************

    @Override
    public ResponseEntity<UrlResource> getCategoryImage(int id) throws Exception {
        Optional<Category> categoryImage = categoryRepo.findById(id);
        if (categoryImage.isPresent()) {
            String filePath = uploadDir + categoryImage.get().getImage(); // Get the path to the profile picture
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
    public ResponseEntity<Map<String, Object>> deleteCategoryById(int categoryId) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            try {
                categoryRepo.deleteById(categoryId);
            } catch (IdNotFoundException e) {
                throw new IdNotFoundException("Id not found");
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Category data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
