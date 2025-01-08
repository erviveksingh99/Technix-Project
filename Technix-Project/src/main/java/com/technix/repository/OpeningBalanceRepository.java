package com.technix.repository;

import com.technix.entity.OpeningBalance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningBalanceRepository extends JpaRepository<OpeningBalance, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OpeningBalance ob WHERE ob.ledgerId = :ledgerId")
    void deleteByLedgerId(@Param("ledgerId") int ledgerId);

    boolean existsByFinancialPeriodId(int financialPeriodId);
}
