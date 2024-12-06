package com.technix.controller;

import com.technix.entity.TransactionMain;
import com.technix.service.TransactionMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transactionMain")
public class TransactionMainController {

    @Autowired
    private TransactionMainService mainService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransactionMain(@RequestBody TransactionMain transactionMain) {
        TransactionMain transactionResponse = mainService.createTransactionMain(transactionMain).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("transaction main", transactionResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactionMainById/{transactionId}")
    public ResponseEntity<Map<String, Object>> getTransactionMainById(@RequestParam("transactionId") int transactionId) {
        TransactionMain transactionResponse = mainService.getTransactionMainById(transactionId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("transaction main", transactionResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<Map<String, Object>> deleteTransactionMain(@RequestParam("transactionId") int transactionId) {
        return mainService.deleteTransactionMain(transactionId);
    }
}
