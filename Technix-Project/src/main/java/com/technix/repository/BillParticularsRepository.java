package com.technix.repository;

import com.technix.entity.BillParticulars;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillParticularsRepository extends JpaRepository<BillParticulars, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM BillParticulars bp WHERE bp.billId = :billId")
    void deleteByBillId(@Param("billId") int billId);
}
