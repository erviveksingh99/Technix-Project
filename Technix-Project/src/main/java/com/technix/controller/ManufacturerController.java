package com.technix.controller;

import com.technix.entity.Manufacturer;
import com.technix.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createManufacturer(@RequestParam("companyId") int companyId,
                                                                  @RequestParam("manufacturer") String manufacturer,
                                                                  @RequestParam(value = "description",required = false) String description,
                                                                  @RequestParam("createdBy") int createdBy) {
        Manufacturer mnfc = manufacturerService
                .createManufacturer(companyId, manufacturer, description, createdBy)
                .getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("manufacturer", mnfc);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{manufacturerId}")
    public ResponseEntity<Map<String, Object>> updateManufacturer(@RequestParam("manufacturerId") int manufacturerId,
                                                                  @RequestParam("companyId") int companyId,
                                                                  @RequestParam("manufacturer") String manufacturer,
                                                                  @RequestParam(value = "description", required = false) String description,
                                                                  @RequestParam("createdBy") int createdBy) {
        Manufacturer mnfc = manufacturerService
                .updateManufacturer(manufacturerId, companyId, manufacturer, description, createdBy)
                .getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("manufacturer", mnfc);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getManufacturer/{manufacturerId}")
    public ResponseEntity<Map<String, Object>> getManufacturerById(@RequestParam("manufacturerId") int manufacturerId) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("manufacturer", manufacturer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getManufacturerByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getManufacturerByCompanyId(@RequestParam("companyId") int companyId) {
        List<Manufacturer> manufacturer = manufacturerService.getManufacturerByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("manufacturer", manufacturer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{manufacturerId}")
    public ResponseEntity<Map<String, Object>> deleteManufacturerById(@RequestParam("manufacturerId") int manufacturerId) {
        return manufacturerService.deleteManufacturerById(manufacturerId);
    }
}
