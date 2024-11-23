package com.technix.repository;

import com.technix.entity.Supplier;
import com.technix.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByCompanyId(int companyId);
}
