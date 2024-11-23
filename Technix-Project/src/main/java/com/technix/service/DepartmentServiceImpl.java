package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Department;
import com.technix.repository.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public ResponseEntity<Department> createDepartment(Department dpt) {
        try {
            return ResponseEntity.ok(departmentRepo.save(dpt));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data is not saved in the database");
        }
    }

    @Override
    public ResponseEntity<Department> updateDepartment(int departmentId, int companyId, String department, String description, int createdBy) {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department dpt = optionalDepartment.get();
            dpt.setDepartmentId(departmentId);
            dpt.setCompanyId(companyId);
            dpt.setDepartment(department);
            dpt.setDescription(description);
            dpt.setCreatedBy(createdBy);
            dpt.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(departmentRepo.save(dpt));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data is not updated in the database");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Department> getDepartmentById(int departmentId) {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            return ResponseEntity.ok(optionalDepartment.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<List<Department>> getDepartmentByCompanyId(int companyId) {
        List<Department> dept = departmentRepo.findByCompanyId(companyId);
        if (!dept.isEmpty()) {
            return ResponseEntity.ok(dept);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteDepartment(int departmentId) {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            try {
                departmentRepo.deleteById(departmentId);
            } catch (IdNotFoundException e) {
                throw new IdNotFoundException("Id not found");
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Department data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
