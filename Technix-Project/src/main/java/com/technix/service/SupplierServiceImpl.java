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


   /* @Override
    public ResponseEntity<Supplier> createSupplier(int companyId, String supplier, String description, int createdBy) {
        return null;
    }

    @Override
    public ResponseEntity<Supplier> updateSupplier(int supplierId, int companyId, String supplier, String description, int createdBy) {
        return null;
    }

    @Override
    public ResponseEntity<Supplier> getSupplierById(int supplierId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Supplier>> getSupplierByCompanyId(int companyId) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteSupplierById(int supplierId) {
        return null;
    }*/
}
