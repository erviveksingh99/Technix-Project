package com.technix.controller;

import com.technix.entity.Unit;
import com.technix.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUnit(@RequestBody Unit unit) {
        Unit unitResponse = unitService.createUnit(unit).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("unit", unitResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUnit(@RequestBody Unit unit) {
        Unit unitResponse = unitService.updateUnit(unit).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("unit", unitResponse);
        response.put("status", true);
        response.put("message", "Unit data updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUnit/{unitId}")
    public ResponseEntity<Map<String, Object>> getUnitById(@RequestParam("unitId") int unitId) {

        Unit unitResponse = unitService.getUnitById(unitId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("unit", unitResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getUnitByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getUnitByCompanyId(@RequestParam("companyId") int companyId)
    {
        List<Unit> unitList = unitService.getUnitByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("unit", unitList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{unitId}")
    public ResponseEntity<Map<String, Object>> deleteUnitById(@RequestParam("unitId") int unitId)
    {
        return  unitService.deleteUnitById(unitId);
    }
}
