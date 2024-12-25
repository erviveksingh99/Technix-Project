package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.FinancialPeriod;
import com.technix.entity.Ledger;
import com.technix.entity.TransactionDetails;
import com.technix.entity.TransactionMain;
import com.technix.repository.FinancialPeriodRepository;
import com.technix.repository.LedgerRepository;
import com.technix.repository.TransactionDetailsRepository;
import com.technix.repository.TransactionMainRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepo;

    @Autowired
    private TransactionMainRepository transactionMainRepo;

    @Autowired
    private LedgerRepository ledgerRepo;

    @Autowired
    private FinancialPeriodRepository financialPeriodRepo;

    @Transactional
    @Override
    public TransactionMain createTransactionDetails(TransactionMain transactionMain,
                                                    String details,
                                                    Integer ledgerId,
                                                    Double total,
                                                    String chequeNo,
                                                    LocalDate chequeDate,
                                                    LocalDate transactionDate, String referenceNo,
                                                    String mode, int branchId) {

        JSONArray array = new JSONArray(details);
        JSONObject jsonObject;

        TransactionMain transactionMain1 = new TransactionMain();
        transactionMain1.setTransactionId(transactionMain.getTransactionId());
        transactionMain1.setCompanyId(transactionMain.getCompanyId());
        transactionMain1.setTransactionNo(transactionMainRepo.findMaxTransactionNo(transactionMain.getCompanyId()) + 1);
        transactionMain1.setVoucherType(transactionMain.getVoucherType());
        transactionMain1.setVoucherNo(transactionMainRepo.findMaxVoucherNo(transactionMain.getVoucherType(), transactionMain.getCompanyId()) + 1);
        transactionMain1.setFinancialPeriodId(transactionMain.getFinancialPeriodId());
        transactionMain1.setTransactionDate(transactionDate);

        //Checking financial period date:
        checkFinancialPeriod(transactionMain.getFinancialPeriodId(), transactionDate);

        TransactionMain savedTransaction = transactionMainRepo.save(transactionMain1);

        Ledger ledger = ledgerRepo.findById(ledgerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ledger Id not found"));

        TransactionDetails detail1 = new TransactionDetails();
        detail1.setTransactionId(savedTransaction.getTransactionId());
        detail1.setTransactionNo(savedTransaction.getTransactionNo());
        detail1.setLedgerId(ledger.getLedgerId());
        detail1.setLedgerName(ledger.getLedgerName());
        detail1.setTransactionDate(savedTransaction.getTransactionDate());
        detail1.setVoucherType(savedTransaction.getVoucherType());
        detail1.setVoucherNo(savedTransaction.getVoucherNo());
        detail1.setPaymentMode(mode);
        if (savedTransaction.getVoucherType().equals("Payment")) {
            detail1.setDebit(0.0);
            detail1.setCredit(total);
            detail1.setDBcR("Cr");
        }

        if (savedTransaction.getVoucherType().equals("Receipt") || savedTransaction.getVoucherType().equals("Contra")) {
            detail1.setDebit(total);
            detail1.setCredit(0.0);
            detail1.setDBcR("Dr");
        }

        if (array.length() > 0) {
            jsonObject = array.getJSONObject(0);
            String narration = jsonObject.optString("narration", "");
            detail1.setNarration(narration.isBlank() ? null : narration);
            detail1.setConfirm(jsonObject.optString("confirm", null));
            detail1.setConfirmedBy(jsonObject.optInt("confirmedBy", 0));
            detail1.setConfirmationDate(jsonObject.optString("confirmationDate", null) != null
                    ? LocalDate.parse(jsonObject.getString("confirmationDate")) : null);
            detail1.setCreatedBy(jsonObject.optInt("createdBy", 0));
        }

        detail1.setChequeNo(chequeNo != null && !chequeNo.trim().isEmpty() ? chequeNo : "");
        detail1.setChequeDate(chequeDate);
        detail1.setRefNo(referenceNo != null ? referenceNo : "");
        detail1.setIsBankAccount(1);
        detail1.setFinancialPeriodId(1);
        detail1.setBranchId(branchId);
        detail1.setCreationDate(LocalDateTime.now());
        detail1.setCompanyId(savedTransaction.getCompanyId());

        transactionDetailsRepo.save(detail1);

        List<TransactionDetails> transactionDetailsList = new ArrayList<>(); // List to hold all entries (both debit and credit)

        for (int i = 0; i < array.length(); i++) {
            jsonObject = array.getJSONObject(i);

            TransactionDetails transactionDetailsItem = new TransactionDetails();
            transactionDetailsItem.setTransactionId(savedTransaction.getTransactionId());
            transactionDetailsItem.setTransactionNo(savedTransaction.getTransactionNo());
            transactionDetailsItem.setLedgerId(jsonObject.getInt("ledgerId"));
            transactionDetailsItem.setLedgerName(jsonObject.getString("ledgerName"));
            transactionDetailsItem.setTransactionDate(transactionMain.getTransactionDate());
            transactionDetailsItem.setVoucherType(jsonObject.getString("voucherType"));
            transactionDetailsItem.setVoucherNo(savedTransaction.getVoucherNo());
            transactionDetailsItem.setNarration(jsonObject.getString("narration"));
            transactionDetailsItem.setDebit(jsonObject.optDouble("debit", 0.0));
            transactionDetailsItem.setCredit(jsonObject.optDouble("credit", 0.0));
            transactionDetailsItem.setDBcR(jsonObject.optString("dBcR", jsonObject.getString("dBcR")));
           // transactionDetailsItem.setFinancialPeriodId(jsonObject.getInt("financialPeriodID"));
            transactionDetailsItem.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            transactionDetailsItem.setRefNo(referenceNo);
            transactionDetailsItem.setParticularsId(jsonObject.optInt("particularsId", 0));
            transactionDetailsItem.setParticulars(jsonObject.optString("particulars", null));
            transactionDetailsItem.setConfirm(jsonObject.optString("confirm", null));
            transactionDetailsItem.setConfirmedBy(jsonObject.optInt("confirmedBy", 0));
            transactionDetailsItem.setConfirmationDate(jsonObject.optString("confirmationDate", null) != null
                    ? LocalDate.parse(jsonObject.getString("confirmationDate")) : null);
            transactionDetailsItem.setCreatedBy(jsonObject.optInt("createdBy", 0));
            transactionDetailsItem.setPaymentMode(mode);
            transactionDetailsItem.setChequeNo(chequeNo != null && !chequeNo.trim().isEmpty() ? chequeNo : "");
            transactionDetailsItem.setChequeDate(chequeDate);
            transactionDetailsItem.setIsBankAccount(1);
            transactionDetailsItem.setRefNo(referenceNo != null ? referenceNo : "");
            //  transactionDetailsItem.setBranchId(jsonObject.optInt("branchId", 0));
            transactionDetailsItem.setBranchId(branchId);
           // transactionDetailsItem.setCompanyId(jsonObject.optInt("companyId", 0));
            transactionDetailsItem.setCompanyId(savedTransaction.getCompanyId());
            transactionDetailsItem.setTransactionDate(savedTransaction.getTransactionDate());
            transactionDetailsItem.setCreationDate(LocalDateTime.now());

            transactionDetailsList.add(transactionDetailsItem);
        }
        try {
            transactionDetailsRepo.saveAll(transactionDetailsList);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction details saving failed");
        }
        return savedTransaction;
    }

    private boolean checkFinancialPeriod(int financialPeriodId, LocalDate transactionDate) {
        Optional<FinancialPeriod> financialPeriod = financialPeriodRepo.findById(financialPeriodId);
        if (financialPeriod.isEmpty()) {
            throw new IdNotFoundException("Financial period id not found");
        }

        FinancialPeriod fp = financialPeriod.get();

        // Check if the transaction date is outside the financial period
        if (transactionDate.isBefore(fp.getSDate()) || transactionDate.isAfter(fp.getEDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Financial period is not matched");
        }

        // If everything is valid, return true
        return true;
    }

    @Override
    public ResponseEntity<List<TransactionDetails>> getTransactionDetailsByTransactionNo(int transactionNo) {
        return ResponseEntity.ok(transactionDetailsRepo.findByTransactionNo(transactionNo));
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTransactionDetailsById(int transactionDetailsId) {
        Optional<TransactionDetails> transactionDetails = transactionDetailsRepo.findById(transactionDetailsId);
        if (transactionDetails.isPresent()) {
            transactionDetailsRepo.deleteById(transactionDetailsId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Transaction details data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
