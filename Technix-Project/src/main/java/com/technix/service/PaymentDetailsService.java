package com.technix.service;

import com.technix.entity.PaymentDetails;

import java.util.List;

public interface PaymentDetailsService {

    public List<PaymentDetails> createPayment(String paymentDetails,
                              String voucherType,
                              int contactId,
                              int companyId,
                              int branchId);

    public PaymentDetails updatePayment(int paymentId,PaymentDetails paymentDetails);

    public String deletePayment(int paymentId);
}
