package com.technix.service;

import com.technix.entity.Unit;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UnitService {

    public ResponseEntity<Unit> createUnit(Unit unit);

    public ResponseEntity<Unit> updateUnit(Unit unit);

    public ResponseEntity<Unit> getUnitById(int unitId);

    public ResponseEntity<List<Unit>> getUnitByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteUnitById(int unitId);
}
