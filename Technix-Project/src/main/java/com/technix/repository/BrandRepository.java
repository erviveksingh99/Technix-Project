package com.technix.repository;

import com.technix.entity.Brand;
import com.technix.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
    List<Brand> findByCompanyId(int companyId);
}
