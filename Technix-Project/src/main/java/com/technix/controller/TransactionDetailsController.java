package com.technix.controller;

import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import com.technix.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactionDetails")
public class TransactionDetailsController {

    @Autowired
    private TransactionDetailsService detailsService;

    @PostMapping("/create")
    public ResponseEntity<List<TransactionDetails>> createTransactionDetails(@RequestBody TransactionMain transactionMain,  @RequestParam("details") String  details) {
       /* TransactionDetails transactionResponse = (TransactionDetails) detailsService.createTransactionDetails(transactionMain, details).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("transactionDetails", transactionResponse);
        response.put("status", true);*/
        return  detailsService.createTransactionDetails(transactionMain, details);
    }

    @GetMapping("/transactionDetailsById/{transactionDetailsId}")
    public ResponseEntity<Map<String, Object>> getTransactionDetailsById(@RequestParam("transactionDetailsId") int transactionDetailsId) {
        TransactionDetails transactionResponse = detailsService.getTransactionDetailsById(transactionDetailsId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("transactionDetails", transactionResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
