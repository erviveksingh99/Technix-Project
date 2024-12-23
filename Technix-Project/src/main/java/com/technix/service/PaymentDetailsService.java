package com.technix.service;

import com.technix.entity.PaymentDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PaymentDetailsService {

    public ResponseEntity<Map<String, Object>> createPayment(String paymentDetails,
                                                             String voucherType,
                                                             int contactId,
                                                             int companyId,
                                                             String invoiceNo,
                                                             double totalPayment);

    public ResponseEntity<Map<String, Object>> updatePayment(String details,
                                                             int transactionId,
                                                             int contactId,
                                                             String invoiceNo);

    public List<PaymentDetails> getPaymentByReceiptNo(int receiptNo);

    public ResponseEntity<Map<String, Object>> deletePayment(int receiptNo, int transactionId);
}
