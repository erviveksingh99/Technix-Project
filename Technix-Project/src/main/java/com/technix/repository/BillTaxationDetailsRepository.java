package com.technix.repository;

import com.technix.entity.BillTaxationDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTaxationDetailsRepository extends JpaRepository<BillTaxationDetails, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM BillTaxationDetails btd WHERE btd.billId = :billId")
    void deleteByBillId(@Param("billId") int billId);
}
