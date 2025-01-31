package com.technix.service;

import com.technix.dto.MonthlyTransactionSummary;
import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TransactionDetailsService {

    public TransactionMain createTransactionDetails(TransactionMain transactionMain,
                                                    String transactionDetails,
                                                    Integer ledgerId,
                                                    Double total,
                                                    String chequeNo,
                                                    LocalDate chequeDate,
                                                    LocalDate transactionDate,
                                                    String referenceNo,
                                                    String mode,
                                                    int branchId);

   // public ResponseEntity<TransactionDetails> updateTransactionDetails(TransactionMain transactionMain, String transactionDetails);

    public ResponseEntity<List<TransactionDetails>> getTransactionDetailsByTransactionNo(int transactionNo);

    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(int transactionDetailsId);

    public List<MonthlyTransactionSummary> getMonthlyTransactionSummary(int ledgerId, String startDate, String endDate);

    public List<Map<String, Object>>  getListOfVoucherReport(LocalDate startDate, LocalDate endDate,  String voucherType);

    public List<Map<String, Object>> findAllVoucherTypeTransaction(LocalDate startDate, LocalDate endDate);

}
