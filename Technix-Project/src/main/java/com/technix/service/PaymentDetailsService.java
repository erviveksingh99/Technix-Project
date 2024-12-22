package com.technix.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PaymentDetailsService {

    public ResponseEntity<Map<String, Object>> createPayment(String paymentDetails,
                                                             String voucherType,
                                                             int contactId,
                                                             int companyId,
                                                             String invoiceNo);

    public ResponseEntity<Map<String, Object>> updatePayment(String details,
                                                             int transactionId,
                                                             int contactId,
                                                             String invoiceNo);

    public String deletePayment(int paymentId);
}
