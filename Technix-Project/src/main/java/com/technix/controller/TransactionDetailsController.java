package com.technix.controller;

import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import com.technix.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactionDetails")
public class TransactionDetailsController {

    @Autowired
    private TransactionDetailsService detailsService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransactionDetails(@RequestBody TransactionMain transactionMain,
                                                                        @RequestParam("details") String details,
                                                                        @RequestParam("ledgerId") Integer ledgerId,
                                                                        @RequestParam("totalAmount") Double totalAmount,
                                                                        @RequestParam(value = "chequeNo", required = false) String chequeNo,
                                                                        @RequestParam(value = "chequeDate", required = false) LocalDate chequeDate,
                                                                        @RequestParam(value = "transactionDate", required = true) LocalDate transactionDate,
                                                                        @RequestParam(value = "referenceNo", required = false) String referenceNo,
                                                                        @RequestParam("mode") String mode,
                                                                        @RequestParam("branchId") int branchId) {
        TransactionMain transactionResponse = detailsService.createTransactionDetails(transactionMain,
                details,
                ledgerId,
                totalAmount,
                chequeNo,
                chequeDate,
                transactionDate,
                referenceNo,
                mode,
                branchId);
        Map<String, Object> response = new HashMap<>();
        response.put("details", transactionResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactionByTransactionNo/{transactionNo}")
    public ResponseEntity<Map<String, Object>> getTransactionDetailsByTransactionNo(@RequestParam("transactionNo") int transactionNo) {
        List<TransactionDetails> transactionResponse = detailsService.getTransactionDetailsByTransactionNo(transactionNo).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("transactionDetails", transactionResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/{transactionDetailsId}")
    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(@RequestParam("transactionDetailsId") int transactionDetailsId) {
        return detailsService.deleteTransactionDetailsById(transactionDetailsId);
    }
}
