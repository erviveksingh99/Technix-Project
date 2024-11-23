package com.technix.controller;

import com.technix.entity.Department;
import com.technix.repository.DepartmentRepository;
import com.technix.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepo;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDepartment(
            @RequestParam("companyId") int companyId,
            @RequestParam("department") String department,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("createdBy") int createdBy) {

        Department dept = new Department();
        dept.setCompanyId(companyId);
        dept.setDepartment(department);
        dept.setDescription(description);
        dept.setCreatedBy(createdBy);
        dept.setCreatedAt(LocalDateTime.now());

        Department departmentData = departmentService.createDepartment(dept).getBody();

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("department", departmentData);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<Map<String, Object>> updateDepartment(@RequestParam("departmentId") int departmentId,
                                                                @RequestParam("companyId") int companyId,
                                                                @RequestParam("department") String department,
                                                                @RequestParam(value = "description", required = false) String description,
                                                                @RequestParam("createdBy") int createdBy) {
        Department updatedDepartment = departmentService
                .updateDepartment(departmentId, companyId, department, description, createdBy)
                .getBody();

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("department", updatedDepartment);
        response.put("message", "Department updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDepartment/{departmentId}")
    public ResponseEntity<Map<String, Object>> getDepartmentById(@RequestParam("departmentId") int departmentId) {

        Department getDepartmentId = departmentService.getDepartmentById(departmentId).getBody();

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("department", getDepartmentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDepartmentByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getDepartmentByCompanyId(@RequestParam("companyId") int companyId) {
        List<Department> dept = departmentService.getDepartmentByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("department", dept);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<Map<String, Object>> deleteDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }
}
