package com.technix.repository;

import com.technix.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // Custom query to find companies by customerId
    List<Company> findByCustomerId(int customerId);
}
