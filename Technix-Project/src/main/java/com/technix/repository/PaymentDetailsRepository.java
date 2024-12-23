package com.technix.repository;

import com.technix.entity.PaymentDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {
    void deleteByTransactionId(int transactionId);

    @Query(value = "SELECT COALESCE(MAX(receipt_no), 0) FROM tblpayment WHERE company_id = :companyId", nativeQuery = true)
    int findMaxReceiptNo(@Param("companyId") int companyId);

    List<PaymentDetails> findByReceiptNo(int receiptNo);

    @Modifying
    @Transactional
    @Query("DELETE FROM PaymentDetails p WHERE p.receiptNo = :receiptNo")
    void deleteByReceiptNo(@Param("receiptNo") int receiptNo);
}
