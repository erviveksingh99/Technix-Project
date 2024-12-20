package com.technix.repository;

import com.technix.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByCompanyId(int companyId);

    Bill findByBranchId(int branchId);
}
