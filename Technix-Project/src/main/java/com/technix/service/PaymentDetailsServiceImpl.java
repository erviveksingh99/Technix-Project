package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.*;
import com.technix.repository.*;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<PaymentDetails> createPayment(String details,
                                              String voucherType,
                                              int contactId,
                                              int companyId,
                                              int branchId) {

        TransactionMain transactionMain1 = new TransactionMain();

        transactionMain1.setCompanyId(companyId);
        transactionMain1.setTransactionNo(transactionMainRepo.findMaxTransactionNo(companyId + 1));
        transactionMain1.setVoucherType(voucherType);
        transactionMain1.setVoucherNo(transactionMainRepo.findMaxVoucherNo(voucherType, companyId + 1));
        transactionMain1.setFinancialPeriodId(1);
        transactionMain1.setTransactionDate(LocalDate.now());

        TransactionMain savedTransaction = null;
        try {
            savedTransaction = transactionMainRepo.save(transactionMain1);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction main data saving failed");
        }

        Optional<Contacts> contacts = contactsRepo.findById(contactId);
        if (contacts.isEmpty()) {
            throw new IdNotFoundException("contact is invalid");
        }

        Contacts contacts1 = contacts.get();

        JSONArray array = new JSONArray(details);
        JSONObject jsonObject;

        List<PaymentDetails> paymentList = new ArrayList<>();

        Bill bill = billRepos.findByBranchId(branchId);

        PaymentDetails savedPayment = null;

        int sumOfPayment = 0;
        int paymentSum = 0;
        double deductedAmount = bill.getGrandTotal();


        for (int i = 0; i < array.length(); i++) {
            jsonObject = array.getJSONObject(i);

            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setReceiptNo(jsonObject.optString("receiptNo", null));
            paymentDetails.setTransactionId(savedTransaction.getTransactionId());
            paymentDetails.setTransactionDate(savedTransaction.getTransactionDate());

            paymentDetails.setContactId(contactId);
            paymentDetails.setContactName(contacts1.getContactName());
            paymentDetails.setLedgerId(contacts1.getLedgerId());

            paymentDetails.setTotalPayment(jsonObject.optDouble("totalPayment", 0.0));
            paymentDetails.setPaymentMethod(jsonObject.optString("paymentMethod", null));
            paymentDetails.setChequeNo(jsonObject.optString("chequeNo", null));
            paymentDetails.setChequeDate(jsonObject.optString("chequeDate", null));
            paymentDetails.setReferenceNo(jsonObject.optString("referenceNo", null));
            paymentDetails.setRemarks(jsonObject.optString("remark", null));
            paymentDetails.setBankLedgerId(jsonObject.optInt("bankLedgerId", 0));
            paymentDetails.setCompanyId(companyId);
            paymentDetails.setBranchId(branchId);
            paymentDetails.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());

            paymentDetails.setInvoiceNo(bill.getInvoiceNo());
            paymentDetails.setRefNo(jsonObject.optString("refNo", null));
            paymentDetails.setDueDate(jsonObject.optString("dueDate", null));
            paymentDetails.setInvoiceDate(bill.getBillDate());
            // paymentDetails.setBillAmount(jsonObject.optDouble("billAmount", 0.0));  // not completed
            paymentDetails.setBillAmount(bill.getGrandTotal());
            paymentDetails.setAmountDue(jsonObject.optDouble("amountDue", 0.0));
            paymentDetails.setPayment(jsonObject.optDouble("payment", 0.0));
            paymentDetails.setCreatedBy(jsonObject.optInt("createdBy", 0));
            paymentDetails.setVoucherType(voucherType);

            deductedAmount -= paymentDetails.getPayment();
            paymentDetails.setAmountDue(deductedAmount);
            try {
                savedPayment = paymentDetailsRepo.save(paymentDetails);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment details saving failed");
            }

            //****************************************************************

            //Bank Debit
            // 2nd entry
            TransactionDetails detail2 = new TransactionDetails();
            detail2.setTransactionId(savedTransaction.getTransactionId());
            detail2.setTransactionNo(savedTransaction.getTransactionNo());

            Optional<Ledger> ledger = ledgerRepo.findById(savedPayment.getBankLedgerId());
            if (!ledger.isPresent()) {
                throw new IdNotFoundException("Bank ledger id not found");
            }
            Ledger ledger1 = ledger.get();

            detail2.setLedgerId(ledger1.getLedgerId());  //customer ledgerId
            detail2.setLedgerName(ledger1.getLedgerName()); //customer ledgerName
            detail2.setTransactionDate(savedTransaction.getTransactionDate());
            detail2.setVoucherType(savedTransaction.getVoucherType());
            detail2.setVoucherNo(savedTransaction.getVoucherNo());
            detail2.setPaymentMode(savedPayment.getPaymentMethod());
            detail2.setDebit(savedPayment.getPayment()); // doubt
            detail2.setCredit(0.0);
            detail2.setDBcR("Dr");
            detail2.setConfirm("1");
            detail2.setConfirmedBy(1);
            detail2.setConfirmationDate(LocalDate.now());
            detail2.setCreatedBy(1);
            detail2.setChequeDate(null);
            detail2.setChequeNo(null);
            detail2.setRefNo(bill.getReferenceNo());
            detail2.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            detail2.setBranchId(branchId);
            detail2.setNarration("To Amount received from " + bill.getInvoiceNo());
            detail2.setParticulars(ledger1.getLedgerName());
            detail2.setParticularsId(ledger1.getLedgerId());
            detail2.setCompanyId(companyId);
            detail2.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail2);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry2 data saving failed");
            }

            sumOfPayment += savedPayment.getPayment();
            //****************************************************************

            paymentList.add(paymentDetails);
        }

        //Customer credit
        // 1st entry for Credit
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
        detail1.setCredit(sumOfPayment);   //customer credit  bank debit
        detail1.setDBcR("Cr");
        detail1.setConfirm("1");
        detail1.setConfirmedBy(1);
        detail1.setConfirmationDate(LocalDate.now());
        detail1.setCreatedBy(1);
        detail1.setChequeDate(null);
        detail1.setChequeNo(null);
        detail1.setRefNo(bill.getReferenceNo());
        detail1.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
        detail1.setBranchId(branchId);
        detail1.setNarration("By being Amount Paid " + bill.getInvoiceNo());
        detail1.setCompanyId(companyId);
        detail1.setParticulars(contacts1.getContactName());
        detail1.setParticularsId(contacts1.getLedgerId());
        detail1.setCreationDate(LocalDateTime.now());
        try {
            transactionDetailsRepo.save(detail1);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry1 data saving failed");
        }
        //  paymentDetailsRepo.saveAll(paymentList);
        return paymentList;
    }

    @Override
    public PaymentDetails updatePayment(int paymentId, PaymentDetails paymentDetails) {
        return null;
    }

    @Override
    public String deletePayment(int paymentId) {
        return "";
    }
}
