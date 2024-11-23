package com.technix.service;

import com.technix.entity.Manufacturer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ManufacturerService {

    public ResponseEntity<Manufacturer> createManufacturer(int companyId, String manufacturer, String description, int createdBy);

    public ResponseEntity<Manufacturer> updateManufacturer(int manufacturerId, int companyId, String manufacturer, String description, int createdBy);

    public ResponseEntity<Manufacturer> getManufacturerById(int mfId);

    public ResponseEntity<List<Manufacturer>> getManufacturerByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteManufacturerById(int mfId);
}
