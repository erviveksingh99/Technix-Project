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


}
