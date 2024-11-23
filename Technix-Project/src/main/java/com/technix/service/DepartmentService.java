package com.technix.service;

import com.technix.entity.Department;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    public ResponseEntity<Department> createDepartment(Department dpt);

    public ResponseEntity<Department> updateDepartment(int departmentId, int companyId, String department, String description, int createdBy);

    public ResponseEntity<Department> getDepartmentById(int departmentId);

    public ResponseEntity<List<Department>> getDepartmentByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteDepartment(int departmentId);
}
