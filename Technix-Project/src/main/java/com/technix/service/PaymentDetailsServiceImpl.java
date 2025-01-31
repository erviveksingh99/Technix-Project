package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.*;
import com.technix.repository.*;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private PaymentDetailsRepository paymentDetailsRepo;
    private TransactionMainRepository transactionMainRepo;
    private ContactsRepository contactsRepo;
    private BillRepository billRepos;
    private LedgerRepository ledgerRepo;
    private TransactionDetailsRepository transactionDetailsRepo;


    @Autowired
    public PaymentDetailsServiceImpl(PaymentDetailsRepository paymentDetailsRepo,
                                     TransactionMainRepository transactionMainRepo,
                                     ContactsRepository contactsRepo,
                                     BillRepository billRepos,
                                     LedgerRepository ledgerRepo,
                                     TransactionDetailsRepository transactionDetailsRepo) {
        this.paymentDetailsRepo = paymentDetailsRepo;
        this.transactionMainRepo = transactionMainRepo;
        this.contactsRepo = contactsRepo;
        this.billRepos = billRepos;
        this.ledgerRepo = ledgerRepo;
        this.transactionDetailsRepo = transactionDetailsRepo;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> createPayment(String details,
                                                             String voucherType,
                                                             int contactId,
                                                             int companyId,
                                                             String invoiceNo, double totalPayment) {

        TransactionMain transactionMain1 = new TransactionMain();
        transactionMain1.setCompanyId(companyId);
        transactionMain1.setTransactionNo(transactionMainRepo.findMaxTransactionNo(companyId) + 1);
        transactionMain1.setVoucherType(voucherType);
        transactionMain1.setVoucherNo(transactionMainRepo.findMaxVoucherNo(voucherType, companyId) + 1);
        transactionMain1.setFinancialPeriodId(1);
        transactionMain1.setTransactionDate(LocalDate.now());
        TransactionMain savedTransaction;

        try {
            savedTransaction = transactionMainRepo.save(transactionMain1);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction main data saving failed");
        }

        Optional<Contacts> contacts = contactsRepo.findById(contactId);
        if (contacts.isEmpty()) {
            throw new IdNotFoundException("Contact is invalid");
        }
        Contacts contacts1 = contacts.get();

        JSONArray array = new JSONArray(details);
        JSONObject jsonObject;

        double deductedAmount = 0;
        Bill bill = null;
        try {
            bill = billRepos.findByInvoiceNo(invoiceNo);
        } catch (Exception e) {
            throw new IdNotFoundException("Invoice no is invalid");
        }

        List<PaymentDetails> paymentList = new ArrayList<>();
        PaymentDetails savedPayment = null;
        int sumOfPayment = 0;
        int receiptNum = 0;


        for (int i = 0; i < array.length(); i++) {
            jsonObject = array.getJSONObject(i);

            PaymentDetails paymentDetails = new PaymentDetails();
            // paymentDetails.setReceiptNo(jsonObject.optString("receiptNo", null));
            if (i == 0) {
                receiptNum = paymentDetailsRepo.findMaxReceiptNo(companyId) + 1;
            }
            paymentDetails.setReceiptNo(receiptNum);
            paymentDetails.setTransactionId(savedTransaction.getTransactionId());
            paymentDetails.setTransactionDate(savedTransaction.getTransactionDate());
            paymentDetails.setContactId(contactId);
            paymentDetails.setContactName(contacts1.getContactName());
            paymentDetails.setLedgerId(contacts1.getLedgerId());
          //  paymentDetails.setTotalPayment(jsonObject.optDouble("totalPayment", 0.0));
            paymentDetails.setTotalPayment(totalPayment);

            paymentDetails.setPaymentMethod(jsonObject.optString("paymentMethod", null));
            paymentDetails.setChequeNo(jsonObject.optString("chequeNo", null));
            paymentDetails.setChequeDate(jsonObject.optString("chequeDate", null));
            paymentDetails.setReferenceNo(jsonObject.optString("referenceNo", null));
            paymentDetails.setRemarks(jsonObject.optString("remark", null));
            paymentDetails.setBankLedgerId(jsonObject.optInt("bankLedgerId", 0));
            paymentDetails.setCompanyId(companyId);
            paymentDetails.setBranchId(jsonObject.optInt("branchId", 0));
            paymentDetails.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            //  paymentDetails.setInvoiceNo(jsonObject.optString("invoiceNo", null));
            paymentDetails.setInvoiceNo(invoiceNo);
            paymentDetails.setRefNo(jsonObject.optString("refNo", null));
            paymentDetails.setDueDate(jsonObject.optString("dueDate", null));
            paymentDetails.setInvoiceDate(jsonObject.optString("invoiceDate", null));
            paymentDetails.setBillAmount(jsonObject.optDouble("billAmount", 0.0));
            paymentDetails.setAmountDue(jsonObject.optDouble("amountDue", 0.0));
            paymentDetails.setPayment(jsonObject.optDouble("payment", 0.0));
            paymentDetails.setCreatedBy(jsonObject.optInt("createdBy", 0));
            paymentDetails.setVoucherType(voucherType);

            //This for transaction details1 to set all credited ammount;
            sumOfPayment += paymentDetails.getPayment();
            paymentDetails.setAmountDue(paymentDetails.getBillAmount() - sumOfPayment);

            try {
                savedPayment = paymentDetailsRepo.save(paymentDetails);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment details saving failed");
            }

            // Bank Debit - Transaction Entry 2
            TransactionDetails detail2 = new TransactionDetails();
            detail2.setTransactionId(savedTransaction.getTransactionId());
            detail2.setTransactionNo(savedTransaction.getTransactionNo());

            Optional<Ledger> ledger = ledgerRepo.findById(savedPayment.getBankLedgerId());
            if (ledger.isEmpty()) {
                throw new IdNotFoundException("Bank ledger id not found");
            }
            Ledger ledger1 = ledger.get();

            detail2.setLedgerId(ledger1.getLedgerId());
            detail2.setLedgerName(ledger1.getLedgerName());
            detail2.setTransactionDate(savedTransaction.getTransactionDate());
            detail2.setVoucherType(savedTransaction.getVoucherType());
            detail2.setVoucherNo(savedTransaction.getVoucherNo());
            detail2.setPaymentMode(savedPayment.getPaymentMethod());
            detail2.setDebit(savedPayment.getPayment());
            detail2.setCredit(0.0);
            detail2.setDBcR("Dr");
            detail2.setConfirm("1");
            detail2.setConfirmedBy(1);
            detail2.setConfirmationDate(LocalDate.now());
            detail2.setCreatedBy(1);
            detail2.setCompanyId(companyId);
            detail2.setBranchId(savedPayment.getBranchId());
            detail2.setParticularsId(bill.getBillId());
            detail2.setParticulars(invoiceNo);
            detail2.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail2);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry 2 saving failed");
            }
        }
        // Customer Credit - Transaction Entry 1
        TransactionDetails detail1 = new TransactionDetails();
        detail1.setTransactionId(savedTransaction.getTransactionId());
        detail1.setTransactionNo(savedTransaction.getTransactionNo());
        detail1.setLedgerId(contacts1.getLedgerId());
        detail1.setLedgerName(contacts1.getContactName());
        detail1.setTransactionDate(savedTransaction.getTransactionDate());
        detail1.setVoucherType(savedTransaction.getVoucherType());
        detail1.setVoucherNo(savedTransaction.getVoucherNo());
        detail1.setPaymentMode(savedPayment.getPaymentMethod());
        detail1.setDebit(0.0);
        detail1.setCredit(sumOfPayment);
        detail1.setDBcR("Cr");
        detail1.setConfirm("1");
        detail1.setConfirmedBy(1);
        detail1.setConfirmationDate(LocalDate.now());
        detail1.setCompanyId(companyId);
        detail1.setBranchId(savedPayment.getBranchId());
        detail1.setParticularsId(bill.getBillId());
        detail1.setParticulars(invoiceNo);
        detail1.setCreatedBy(1);
        detail1.setCreationDate(LocalDateTime.now());

        try {
            transactionDetailsRepo.save(detail1);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry 1 saving failed");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", "Payment is created");
        response.put("status", true);
        return ResponseEntity.ok(response);
    }


    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> updatePayment(String details,
                                                             int transactionId,
                                                             int contactId,
                                                             String invoiceNo) {

        // First delete the Payment details
        try {
            paymentDetailsRepo.deleteByTransactionId(transactionId);
        } catch (IdNotFoundException e) {
            throw new IdNotFoundException("Payment data is not deleted");
        }

        // First delete the Transaction details
        try {
            transactionDetailsRepo.deleteByTransactionId(transactionId);
        } catch (IdNotFoundException e) {
            throw new IdNotFoundException("Transaction details is not deleted");
        }

        // Only update the transaction main transaction_date
        Optional<TransactionMain> mainOptional = transactionMainRepo.findById(transactionId);
        if (mainOptional.isEmpty()) {
            throw new IdNotFoundException("Transaction id is invalid");
        }
        TransactionMain transactionMain = mainOptional.get();
        transactionMain.setTransactionDate(LocalDate.now());
        TransactionMain savedTransaction;

        try {
            savedTransaction = transactionMainRepo.save(transactionMain);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction main data saving failed");
        }

        Optional<Contacts> contacts = contactsRepo.findById(contactId);
        if (contacts.isEmpty()) {
            throw new IdNotFoundException("Contact is invalid");
        }
        Contacts contacts1 = contacts.get();

        JSONArray array = new JSONArray(details);
        JSONObject jsonObject;

        //  double deductedAmount = 0;
        Bill bill = null;
        try {
            bill = billRepos.findByInvoiceNo(invoiceNo);
        } catch (Exception e) {
            throw new IdNotFoundException("Invoice no is invalid");
        }

        //  List<PaymentDetails> paymentList = new ArrayList<>();
        PaymentDetails savedPayment = null;
        int sumOfPayment = 0;
        int totalPayment = 0;

        for (int i = 0; i < array.length(); i++) {
            jsonObject = array.getJSONObject(i);

            PaymentDetails paymentDetails = new PaymentDetails();
            //  paymentDetails.setReceiptNo(jsonObject.optString("receiptNo", null));
            paymentDetails.setTransactionId(savedTransaction.getTransactionId());
            paymentDetails.setTransactionDate(savedTransaction.getTransactionDate());
            paymentDetails.setContactId(contactId);
            paymentDetails.setContactName(contacts1.getContactName());
            paymentDetails.setLedgerId(contacts1.getLedgerId());
            paymentDetails.setTotalPayment(jsonObject.optDouble("totalPayment", 0.0));

            totalPayment += paymentDetails.getTotalPayment();
            paymentDetails.setTotalPayment(totalPayment);

            paymentDetails.setPaymentMethod(jsonObject.optString("paymentMethod", null));
            paymentDetails.setChequeNo(jsonObject.optString("chequeNo", null));
            paymentDetails.setChequeDate(jsonObject.optString("chequeDate", null));
            paymentDetails.setReferenceNo(jsonObject.optString("referenceNo", null));
            paymentDetails.setRemarks(jsonObject.optString("remark", null));
            paymentDetails.setBankLedgerId(jsonObject.optInt("bankLedgerId", 0));
            paymentDetails.setCompanyId(transactionMain.getCompanyId());
            paymentDetails.setBranchId(jsonObject.optInt("branchId", 0));
            paymentDetails.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            // paymentDetails.setInvoiceNo(jsonObject.optString("invoiceNo", null));
            paymentDetails.setInvoiceNo(invoiceNo);
            paymentDetails.setRefNo(jsonObject.optString("refNo", null));
            paymentDetails.setDueDate(jsonObject.optString("dueDate", null));
            paymentDetails.setInvoiceDate(jsonObject.optString("invoiceDate", null));
            paymentDetails.setBillAmount(jsonObject.optDouble("billAmount", 0.0));
            //   paymentDetails.setAmountDue(jsonObject.optDouble("amountDue", 0.0));
            paymentDetails.setPayment(jsonObject.optDouble("payment", 0.0));
            paymentDetails.setCreatedBy(jsonObject.optInt("createdBy", 0));
            paymentDetails.setVoucherType(savedTransaction.getVoucherType());

            //This for transaction details1 to set all credited ammount;
            sumOfPayment += paymentDetails.getPayment();
            paymentDetails.setAmountDue(paymentDetails.getBillAmount() - sumOfPayment);

            try {
                savedPayment = paymentDetailsRepo.save(paymentDetails);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment details saving failed");
            }

            // Bank Debit - Transaction Entry 2
            TransactionDetails detail2 = new TransactionDetails();
            detail2.setTransactionId(savedTransaction.getTransactionId());
            detail2.setTransactionNo(savedTransaction.getTransactionNo());

            Optional<Ledger> ledger = ledgerRepo.findById(savedPayment.getBankLedgerId());
            if (ledger.isEmpty()) {
                throw new IdNotFoundException("Bank ledger id not found");
            }
            Ledger ledger1 = ledger.get();

            detail2.setLedgerId(ledger1.getLedgerId());
            detail2.setLedgerName(ledger1.getLedgerName());
            detail2.setTransactionDate(savedTransaction.getTransactionDate());
            detail2.setVoucherType(savedTransaction.getVoucherType());
            detail2.setVoucherNo(savedTransaction.getVoucherNo());
            detail2.setPaymentMode(savedPayment.getPaymentMethod());
            detail2.setDebit(savedPayment.getPayment());
            detail2.setCredit(0.0);
            detail2.setDBcR("Dr");
            detail2.setConfirm("1");
            detail2.setConfirmedBy(1);
            detail2.setConfirmationDate(LocalDate.now());
            detail2.setCreatedBy(1);
            detail2.setCompanyId(savedPayment.getCompanyId());
            detail2.setBranchId(savedPayment.getBranchId());
            detail2.setParticularsId(bill.getBillId());
            detail2.setParticulars(invoiceNo);
            detail2.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail2);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry 2 saving failed");
            }
        }
        // Customer Credit - Transaction Entry 1
        TransactionDetails detail1 = new TransactionDetails();
        detail1.setTransactionId(savedTransaction.getTransactionId());
        detail1.setTransactionNo(savedTransaction.getTransactionNo());
        detail1.setLedgerId(contacts1.getLedgerId());
        detail1.setLedgerName(contacts1.getContactName());
        detail1.setTransactionDate(savedTransaction.getTransactionDate());
        detail1.setVoucherType(savedTransaction.getVoucherType());
        detail1.setVoucherNo(savedTransaction.getVoucherNo());
        detail1.setPaymentMode(savedPayment.getPaymentMethod());
        detail1.setDebit(0.0);
        detail1.setCredit(sumOfPayment);
        detail1.setDBcR("Cr");
        detail1.setConfirm("1");
        detail1.setConfirmedBy(1);
        detail1.setConfirmationDate(LocalDate.now());
        detail1.setCompanyId(savedPayment.getCompanyId());
        detail1.setBranchId(savedPayment.getBranchId());
        detail1.setParticularsId(bill.getBillId());
        detail1.setParticulars(invoiceNo);
        detail1.setCreatedBy(1);
        detail1.setCreationDate(LocalDateTime.now());

        try {
            transactionDetailsRepo.save(detail1);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry 1 saving failed");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", "Payment is updated");
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<PaymentDetails> getPaymentByReceiptNo(int receiptNo) {
        List<PaymentDetails> detailsList = paymentDetailsRepo.findByReceiptNo(receiptNo);
        if (detailsList.isEmpty()) {
            throw new IdNotFoundException("ReceiptNo is invalid");
        }
        return detailsList;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deletePayment(int receiptNo, int transactionId) {
        try {
            paymentDetailsRepo.deleteByReceiptNo(receiptNo);
            transactionDetailsRepo.deleteByTransactionId(transactionId);
            transactionMainRepo.deleteById(transactionId);

            Map<String, Object> response = new HashMap<>();
            response.put("data", "Payment is deleted");
            response.put("status", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new IdNotFoundException("Payment data is not deleted. Reason: " + e.getMessage());
        }
    }
}
