package com.technix.repository;

import com.technix.entity.Company;
import com.technix.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByCompanyId(int companyId);
}
