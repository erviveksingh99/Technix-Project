package com.technix.controller;

import com.technix.entity.Directors;
import com.technix.service.DirectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/director")
public class DirectorsController {

    @Autowired
    private DirectorsService directorsService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDirector(@RequestBody Directors directors) {
        Directors directorsData = directorsService.createDirector(directors).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("directors", directorsData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateDirector(@RequestBody Directors directors) {
        Directors updatedDirectors = directorsService.updateDirector(directors).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("directors", updatedDirectors);
        response.put("status", true);
        response.put("message", "Directors data updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDirector/{directorId}")
    public ResponseEntity<Map<String, Object>> getDirectorById(@RequestParam("directorId") int directorId) {
        Directors directorsData = directorsService.getDirectorById(directorId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("directors", directorsData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDirectorByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getDirectorByCompanyId(@RequestParam("companyId") int companyId) {
        List<Directors> directorList = directorsService.getDirectorByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("directors", directorList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{directorId}")
    public ResponseEntity<Map<String, Object>> deleteDirectorById(@RequestParam("directorId") int directorId) {
        return directorsService.deleteDirectorById(directorId);
    }
}
