package com.technix.service;

import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TransactionDetailsService {

    public TransactionMain createTransactionDetails(TransactionMain transactionMain, String transactionDetails, Integer ledgerId, Double total, String chequeNo, LocalDate chequeDate, String referenceNo, String mode, int branchId);

   // public ResponseEntity<TransactionDetails> updateTransactionDetails(TransactionMain transactionMain, String transactionDetails);

    public ResponseEntity<List<TransactionDetails>> getTransactionDetailsByTransactionNo(int transactionNo);

    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(int transactionDetailsId);
}
