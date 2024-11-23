package com.technix.repository;

import com.technix.entity.Manufacturer;
import com.technix.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByCompanyId(int companyId);
}
