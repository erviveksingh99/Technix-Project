package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Supplier;
import com.technix.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepo;

    @Override
    public ResponseEntity<Supplier> createSupplier(int companyId, String supplier, String description, int createdBy) {
        Supplier sp = new Supplier();
        sp.setCompanyId(companyId);
        sp.setSupplier(supplier);
        sp.setDescription(description);
        sp.setCreatedBy(createdBy);
        sp.setCreatedAt(LocalDateTime.now());
        try {
            return ResponseEntity.ok(supplierRepo.save(sp));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Supplier> updateSupplier(int supplierId, int companyId, String supplier, String description, int createdBy) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(supplierId);
        if (optionalSupplier.isPresent()) {
            Supplier sp = optionalSupplier.get();
            sp.setSupplierId(supplierId);
            sp.setCompanyId(companyId);
            sp.setSupplier(supplier);
            sp.setDescription(description);
            sp.setCreatedBy(createdBy);
            sp.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(supplierRepo.save(sp));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Supplier> getSupplierById(int supplierId) {
        Optional<Supplier> supplierOptional = supplierRepo.findById(supplierId);
        if (supplierOptional.isPresent()) {
            return ResponseEntity.ok(supplierOptional.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<List<Supplier>> getSupplierByCompanyId(int companyId) {
        List<Supplier> supplier = supplierRepo.findByCompanyId(companyId);
        if (!supplier.isEmpty()) {
            return ResponseEntity.ok(supplier);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteSupplierById(int supplierId) {
        Optional<Supplier> supplier = supplierRepo.findById(supplierId);
        if (supplier.isPresent()) {
            supplierRepo.deleteById(supplierId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Supplier data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
