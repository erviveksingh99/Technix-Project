package com.technix.service;

import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TransactionDetailsService {

    public ResponseEntity<List<TransactionDetails>> createTransactionDetails(TransactionMain transactionMain, String transactionDetails);

   // public ResponseEntity<TransactionDetails> updateTransactionDetails(TransactionMain transactionMain, String transactionDetails);

    public ResponseEntity<TransactionDetails> getTransactionDetailsById(int transactionDetailsId);

    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(int transactionDetailsId);
}
