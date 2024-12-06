package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import com.technix.repository.TransactionDetailsRepository;
import com.technix.repository.TransactionMainRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepo;

    @Autowired
    private TransactionMainRepository transactionMainRepo;

  /*  @Override
    public ResponseEntity<List<TransactionDetails>> createTransactionDetails(TransactionMain transactionMain, String details) {
        JSONArray jsonArray;
        List<TransactionDetails> allDetails = new ArrayList<>(); // List to hold all entries (both debit and credit)
        try {
            jsonArray = new JSONArray(details);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format");
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            TransactionDetails detail = new TransactionDetails();

            detail.setCompanyId(jsonObject.optInt("companyId"));
            detail.setLedgerId(jsonObject.optInt("ledgerId"));
            detail.setLedgerName(jsonObject.optString("ledgerName"));
            detail.setNarration(jsonObject.optString("narration"));
            detail.setDebit(jsonObject.optDouble("debit"));
            detail.setCredit(jsonObject.optDouble("credit"));
//            detail.setDBcR(jsonObject.optString("dbCr"));
            detail.setRefNo(jsonObject.optString("refNo"));
            detail.setParticularsId(jsonObject.optString("particularsId"));
            detail.setParticulars(jsonObject.optString("particulars"));
            detail.setConfirm(jsonObject.optString("confirm"));
            detail.setConfirmedBy(jsonObject.optString("confirmedBy"));
            detail.setCreatedBy(jsonObject.optString("createdBy"));
            detail.setPaymentMode(jsonObject.optString("paymentMode"));
            detail.setChequeNo(jsonObject.optString("chequeNo"));
            detail.setBankAccount(jsonObject.optBoolean("isBankAccount"));
            detail.setBranchId(jsonObject.optInt("branchId"));

            // setting common data
            detail.setTransactionId(transactionMain.getTransactionId());
            detail.setTransactionNo(transactionMain.getTransactionNo());
            detail.setVoucherType(transactionMain.getVoucherType());
            String typeOfVoucher = transactionMain.getVoucherType();
            switch (typeOfVoucher) {
                case "Payment": {
                    detail.setDBcR("Dr");
                }
                case "Contra": {
                    detail.setDBcR("Cr");
                }
                case "Receipt": {
                    detail.setDBcR("Cr");
                }
                case "Journal": {
                    detail.setDBcR("Cr");
                }
            }
            detail.setVoucherNo(transactionMain.getVoucherNo());
            detail.setFinancialPeriodId(transactionMain.getFinancialPeriodId());
            detail.setTransactionDate(LocalDateTime.now());
            detail.setCreationDate(LocalDateTime.now());

            allDetails.add(detail);
        }
        try {
            transactionDetailsRepo.saveAll(allDetails);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction data insertion failed");
        }
        return ResponseEntity.ok(allDetails);
    }*/


    @Override
    public ResponseEntity<List<TransactionDetails>> createTransactionDetails(TransactionMain transactionMain, String details) {
        JSONArray jsonArray;
        List<TransactionDetails> allDetails = new ArrayList<>(); // List to hold all entries (both debit and credit)
        try {
            jsonArray = new JSONArray(details);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format");
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            TransactionDetails detail = new TransactionDetails();

            detail.setCompanyId(jsonObject.optInt("companyId"));
            detail.setLedgerId(jsonObject.optInt("ledgerId"));
            detail.setLedgerName(jsonObject.optString("ledgerName"));
            detail.setNarration(jsonObject.optString("narration"));
            detail.setDebit(jsonObject.optDouble("debit"));
            detail.setCredit(jsonObject.optDouble("credit"));
//            detail.setDBcR(jsonObject.optString("dbCr"));
            detail.setRefNo(jsonObject.optString("refNo"));
            detail.setParticularsId(jsonObject.optString("particularsId"));
            detail.setParticulars(jsonObject.optString("particulars"));
            detail.setConfirm(jsonObject.optString("confirm"));
            detail.setConfirmedBy(jsonObject.optString("confirmedBy"));
            detail.setCreatedBy(jsonObject.optString("createdBy"));
            detail.setPaymentMode(jsonObject.optString("paymentMode"));
            detail.setChequeNo(jsonObject.optString("chequeNo"));
            detail.setBankAccount(jsonObject.optBoolean("isBankAccount"));
            detail.setBranchId(jsonObject.optInt("branchId"));

            // setting common data
            detail.setTransactionId(transactionMain.getTransactionId());
            detail.setTransactionNo(transactionMain.getTransactionNo());
            detail.setVoucherType(transactionMain.getVoucherType());
            String typeOfVoucher = transactionMain.getVoucherType();
            switch (typeOfVoucher) {
                case "Payment": {
                    detail.setDBcR("Dr");
                }
                case "Contra": {
                    detail.setDBcR("Cr");
                }
                case "Receipt": {
                    detail.setDBcR("Cr");
                }
                case "Journal": {
                    detail.setDBcR("Cr");
                }
            }
            detail.setVoucherNo(transactionMain.getVoucherNo());
            detail.setFinancialPeriodId(transactionMain.getFinancialPeriodId());
            detail.setTransactionDate(LocalDateTime.now());
            detail.setCreationDate(LocalDateTime.now());

            allDetails.add(detail);
        }
        try {
            transactionDetailsRepo.saveAll(allDetails);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction data insertion failed");
        }
        return ResponseEntity.ok(allDetails);
    }









    @Override
    public ResponseEntity<TransactionDetails> getTransactionDetailsById(int transactionDetailsId) {
        return ResponseEntity.ok(transactionDetailsRepo.findById(transactionDetailsId)
                .orElseThrow(() -> new IdNotFoundException("TransactionId not found")));
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(int transactionDetailsId) {
        return null;
    }
}
