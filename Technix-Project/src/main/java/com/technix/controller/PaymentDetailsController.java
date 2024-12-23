package com.technix.controller;

import com.technix.entity.PaymentDetails;
import com.technix.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentDetailsController {

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestParam("paymentDetails") String paymentDetails,
                                                             @RequestParam("voucherType") String voucherType,
                                                             @RequestParam("contactId") int contactId,
                                                             @RequestParam("invoiceNo") String invoiceNo,
                                                             @RequestParam("totalPayment") double totalPayment,
                                                             @RequestParam("companyId") int companyId) {
        return paymentDetailsService.createPayment(paymentDetails,
                voucherType,
                contactId,
                companyId,
                invoiceNo,
                totalPayment);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePayment(@RequestParam("details") String details,
                                                             @RequestParam("transactionId") int transactionId,
                                                             @RequestParam("contactId") int contactId,
                                                             @RequestParam("invoiceNo") String invoiceNo) {
        return paymentDetailsService.updatePayment(details,
                transactionId,
                contactId,
                invoiceNo);
    }

    @GetMapping("/getPayment/{receiptNo}")
    public ResponseEntity<Map<String, Object>> getPaymentByReceiptNo(@PathVariable("receiptNo") int receiptNo) {
        List<PaymentDetails> detailsList = paymentDetailsService.getPaymentByReceiptNo(receiptNo);
        Map<String, Object> response = new HashMap<>();
        response.put("data", detailsList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deletePayment(@RequestParam("receiptNo") int receiptNo, @RequestParam("transactionId") int transactionId) {
        return paymentDetailsService.deletePayment(receiptNo, transactionId);
    }
}
