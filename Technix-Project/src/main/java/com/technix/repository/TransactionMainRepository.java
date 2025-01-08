package com.technix.repository;

import com.technix.entity.TransactionMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionMainRepository extends JpaRepository<TransactionMain, Integer> {

    @Query(value = "SELECT COALESCE(MAX(transaction_no), 0) FROM tblfinancialperiodtransaction WHERE company_id = :companyId", nativeQuery = true)
    int findMaxTransactionNo(@Param("companyId") int companyId);

    @Query(value = "SELECT COALESCE(MAX(voucher_no), 0) FROM tblfinancialperiodtransaction WHERE voucher_type = :voucherType AND company_id = :companyId", nativeQuery = true)
    int findMaxVoucherNo(@Param("voucherType") String voucherType, @Param("companyId") int companyId);

    Optional<TransactionMain> findByTransactionNo(int transactionNo);

    boolean existsByFinancialPeriodId(int financialPeriodId);
}
