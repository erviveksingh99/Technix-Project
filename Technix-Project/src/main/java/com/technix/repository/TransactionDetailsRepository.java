package com.technix.repository;

import com.technix.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailsRepository  extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByTransactionNo(int transactionNo);

}
