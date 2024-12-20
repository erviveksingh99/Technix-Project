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
                                                             @RequestParam("branchId") int branchId,
                                                             @RequestParam("companyId") int companyId) {
        List<PaymentDetails> paymentResponse = paymentDetailsService.
                createPayment(paymentDetails,
                        voucherType,
                        contactId,
                        companyId,
                        branchId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", paymentResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePayment(@PathVariable("paymentId") int paymentId, @RequestBody PaymentDetails paymentDetails) {
        PaymentDetails paymentResponse = paymentDetailsService.updatePayment(paymentId, paymentDetails);
        Map<String, Object> response = new HashMap<>();
        response.put("data", paymentResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
