package com.technix.repository;

import com.technix.entity.Brand;
import com.technix.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    List<Manufacturer> findByCompanyId(int companyId);
}
