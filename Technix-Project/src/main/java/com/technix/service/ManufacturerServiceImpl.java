package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Manufacturer;
import com.technix.repository.ManufacturerRepository;
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
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepo;

    @Override
    public ResponseEntity<Manufacturer> createManufacturer(int companyId, String manufacturer, String description, int createdBy) {
        Manufacturer mf = new Manufacturer();
        mf.setCompanyId(companyId);
        mf.setManufacturer(manufacturer);
        mf.setDescription(description);
        mf.setCreatedBy(createdBy);
        mf.setCreatedAt(LocalDateTime.now());
        try {
            return ResponseEntity.ok(manufacturerRepo.save(mf));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Manufacturer> updateManufacturer(int manufacturerId, int companyId, String manufacturer, String description, int createdBy) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepo.findById(manufacturerId);
        if (optionalManufacturer.isPresent()) {
            Manufacturer mf = optionalManufacturer.get();
            mf.setManufacturerId(manufacturerId);
            mf.setCompanyId(companyId);
            mf.setManufacturer(manufacturer);
            mf.setDescription(description);
            mf.setCreatedBy(createdBy);
            mf.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(manufacturerRepo.save(mf));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Manufacturer> getManufacturerById(int mfId) {
        Optional<Manufacturer> manufacturer = manufacturerRepo.findById(mfId);
        if (manufacturer.isPresent()) {
            return ResponseEntity.ok(manufacturer.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<List<Manufacturer>> getManufacturerByCompanyId(int companyId) {
        List<Manufacturer> manufacturer = manufacturerRepo.findByCompanyId(companyId);
        if (!manufacturer.isEmpty()) {
            return ResponseEntity.ok(manufacturer);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteManufacturerById(int mfId) {
        Optional<Manufacturer> manufacturer = manufacturerRepo.findById(mfId);
        if (manufacturer.isPresent()) {
            manufacturerRepo.deleteById(mfId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Manufacturer data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
