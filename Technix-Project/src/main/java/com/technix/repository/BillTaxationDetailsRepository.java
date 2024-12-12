package com.technix.repository;

import com.technix.entity.BillTaxationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTaxationDetailsRepository extends JpaRepository<BillTaxationDetails, Integer> {
    void deleteByBillId(int billId);
}
