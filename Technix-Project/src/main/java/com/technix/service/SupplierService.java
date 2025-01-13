package com.technix.service;

import com.technix.entity.Supplier;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface SupplierService {

    public ResponseEntity<Supplier> createSupplier(int companyId, String supplier, String description, int createdBy);

    public ResponseEntity<Supplier> updateSupplier(int supplierId, int companyId, String supplier, String description, int createdBy);

    public ResponseEntity<Supplier> getSupplierById(int supplierId);

    public ResponseEntity<List<Supplier>> getSupplierByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteSupplierById(int supplierId);
}
