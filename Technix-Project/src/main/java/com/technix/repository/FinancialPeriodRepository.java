package com.technix.repository;

import com.technix.entity.FinancialPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialPeriodRepository extends JpaRepository<FinancialPeriod, Integer> {

    void deleteByCompanyId(int companyId);
}
