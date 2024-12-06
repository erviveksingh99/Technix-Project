package com.technix.service;

import com.technix.entity.TransactionMain;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface TransactionMainService {

    public ResponseEntity<TransactionMain> createTransactionMain(TransactionMain transactionMain);

    public ResponseEntity<TransactionMain> getTransactionMainById(int transactionId);

    public ResponseEntity<Map<String, Object>> deleteTransactionMain(int id);
}
