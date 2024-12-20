package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.TransactionMain;
import com.technix.repository.TransactionMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionMainServiceImpl implements TransactionMainService {

    @Autowired
    private TransactionMainRepository transactionMainRepo;

    @Override
    public ResponseEntity<TransactionMain> createTransactionMain(TransactionMain transactionMain) {
        transactionMain.setTransactionDate(LocalDate.now());
        return ResponseEntity.ok(transactionMainRepo.save(transactionMain));
    }

    @Override
    public ResponseEntity<TransactionMain> getTransactionMainById(int transactionId) {
        return ResponseEntity.ok(transactionMainRepo.findById(transactionId).orElseThrow(() -> new IdNotFoundException(" transactionId not found")));
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTransactionMain(int id) {
        Optional<TransactionMain> tm = transactionMainRepo.findById(id);
        if (tm.isPresent()) {
            transactionMainRepo.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "TransactionMain data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
