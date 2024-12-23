package com.technix.repository;

import com.technix.entity.TransactionDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionDetailsRepository  extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByTransactionNo(int transactionNo);


   // void deleteByTransactionId(int transactionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TransactionDetails td WHERE td.transactionId = :transactionId")
    void deleteByTransactionId(@Param("transactionId") int transactionId);
}
