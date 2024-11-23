package com.technix.controller;

import com.technix.entity.Supplier;
import com.technix.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createSupplier(@RequestParam("companyId") int companyId,
                                                              @RequestParam("supplier") String supplier,
                                                              @RequestParam(value = "description", required = false) String description,
                                                              @RequestParam("createdBy") int createdBy) {
        Supplier sp = supplierService
                .createSupplier(companyId, supplier, description, createdBy)
                .getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("supplier", sp);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{supplierId}")
    public ResponseEntity<Map<String, Object>> updateSupplier(@RequestParam("supplierId") int supplierId,
                                                                  @RequestParam("companyId") int companyId,
                                                                  @RequestParam("supplier") String supplier,
                                                                  @RequestParam(value = "description", required = false) String description,
                                                                  @RequestParam("createdBy") int createdBy) {
        Supplier sp = supplierService
                .updateSupplier(supplierId, companyId, supplier, description, createdBy)
                .getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("supplier", sp);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSupplier/{supplierId}")
    public ResponseEntity<Map<String, Object>> getSupplierById(@RequestParam("supplierId") int supplierId) {
        Supplier supplier = supplierService.getSupplierById(supplierId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("supplier", supplier);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSupplierByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getSupplierByCompanyId(@RequestParam("companyId") int companyId) {
        List<Supplier> supplier = supplierService.getSupplierByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("supplier", supplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<Map<String, Object>> deleteManufacturerById(@RequestParam("supplierId") int supplierId) {
        return supplierService.deleteSupplierById(supplierId);
    }
}
