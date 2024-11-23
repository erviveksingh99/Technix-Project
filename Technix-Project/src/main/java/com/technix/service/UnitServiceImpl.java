package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Unit;
import com.technix.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepo;

    @Override
    public ResponseEntity<Unit> createUnit(Unit units) {
        try {
            return ResponseEntity.ok(unitRepo.save(units));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Unit> updateUnit(Unit unit) {
        Optional<Unit> u1 = unitRepo.findById(unit.getUnitId());
        if (u1.isPresent()) {
            Unit u2 = u1.get();
            u2.setCompanyId(unit.getCompanyId());
            u2.setUnit(unit.getUnit());
            u2.setUqc(unit.getUqc());
            u2.setUqcCode(unit.getUqcCode());
            try {
                return ResponseEntity.ok(unitRepo.save(u2));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Unit> getUnitById(int unitId) {
        return ResponseEntity.ok(unitRepo.findById(unitId).orElseThrow(()->new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<Unit>> getUnitByCompanyId(int companyId) {
        List<Unit> unitList = unitRepo.findByCompanyId(companyId);
        if (!unitList.isEmpty()) {
         //   log.info("compa:{}",unitList.get(0).getCompany());
            return ResponseEntity.ok(unitList);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteUnitById(int unitId) {
        Optional<Unit> unit = unitRepo.findById(unitId);
        if (unit.isPresent()) {
            unitRepo.deleteById(unitId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Unit data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
