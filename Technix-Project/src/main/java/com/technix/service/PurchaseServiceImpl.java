package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.dto.PurchaseDTO;
import com.technix.dto.PurchaseExpensesDTO;
import com.technix.dto.PurchaseParticularsDTO;
import com.technix.dto.PurchaseProductTaxesDTO;
import com.technix.entity.*;
import com.technix.repository.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.sound.midi.InvalidMidiDataException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepo;
    private PurchaseParticularsRepository purchaseParticularsRepo;
    private PurchaseProductTaxesRepository purchaseProductTaxesRepo;
    private PurchaseExpensesRepository purchaseExpensesRepo;
    private ContactsRepository contactsRepo;
    private TransactionMainRepository transactionMainRepo;
    private TransactionDetailsRepository transactionDetailsRepo;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepo,
                               PurchaseParticularsRepository purchaseParticularsRepo,
                               PurchaseProductTaxesRepository purchaseProductTaxesRepo,
                               PurchaseExpensesRepository purchaseExpensesRepo,
                               ContactsRepository contactsRepo,
                               TransactionMainRepository transactionMainRepo,
                               TransactionDetailsRepository transactionDetailsRepo) {
        this.purchaseRepo = purchaseRepo;
        this.purchaseParticularsRepo = purchaseParticularsRepo;
        this.purchaseProductTaxesRepo = purchaseProductTaxesRepo;
        this.purchaseExpensesRepo = purchaseExpensesRepo;
        this.contactsRepo = contactsRepo;
        this.transactionMainRepo = transactionMainRepo;
        this.transactionDetailsRepo = transactionDetailsRepo;
    }

    final static int ledgerId = 48;
    final static String ledgerName = "Purchase A/c";
    final static String voucherType = "Purchase";

    @Transactional
    @Override
    public Purchase createPurchase(PurchaseDTO purchaseDTO) throws InvalidMidiDataException {

        Optional<Contacts> existingContacts = contactsRepo.findById(purchaseDTO.getContactId());
        TransactionMain savedTransaction = null;
        if (existingContacts.isPresent()) {
            Contacts contacts = existingContacts.get();
            TransactionMain transactionMain1 = new TransactionMain();

            transactionMain1.setCompanyId(purchaseDTO.getCompanyId());
            transactionMain1.setTransactionNo(transactionMainRepo.findMaxTransactionNo(purchaseDTO.getCompanyId()) + 1);
            transactionMain1.setVoucherType(voucherType);
            transactionMain1.setVoucherNo(transactionMainRepo.findMaxVoucherNo(voucherType, purchaseDTO.getCompanyId()) + 1);
            transactionMain1.setFinancialPeriodId(1);
            transactionMain1.setTransactionDate(LocalDate.now());

            try {
                savedTransaction = transactionMainRepo.save(transactionMain1);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction main data saving failed reason: " + e.getMessage());
            }

            // Purchase creating
            Purchase purchase = new Purchase();
            purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
            int purchaseNo = purchaseRepo.findMaxPurchaseNo(purchaseDTO.getCompanyId()) + 1;
            // System.out.println("Number: "+purchaseNo);
            purchase.setPurchaseNo("24-25/" + purchaseNo);
            purchase.setReferenceNo(purchaseDTO.getReferenceNo());
            purchase.setInvoiceDate(purchaseDTO.getInvoiceDate());
            purchase.setDueDate(purchaseDTO.getDueDate());
            purchase.setPoNo(purchaseDTO.getPoNo());
            purchase.setTaxationType(purchaseDTO.getTaxationType());
            purchase.setContactId(purchaseDTO.getContactId());
            purchase.setSubTotal(purchaseDTO.getSubTotal());
            purchase.setDiscPer(purchaseDTO.getDiscPer());
            purchase.setDiscount(purchaseDTO.getDiscount());
            purchase.setOtherCharges(purchaseDTO.getOtherCharges());
            purchase.setGlobalDisc(purchaseDTO.getGlobalDisc());
            purchase.setGlobalDiscPer(purchaseDTO.getGlobalDiscPer());
            purchase.setTotalTaxes(purchaseDTO.getTotalTaxes());
            purchase.setRoundOff(purchaseDTO.getRoundOff());
            purchase.setGrandTotal(purchaseDTO.getGrandTotal());
            purchase.setNotes(purchaseDTO.getNotes());
            purchase.setStatus(purchaseDTO.getStatus());
            purchase.setCompanyId(purchaseDTO.getCompanyId());
            purchase.setTransactionId(savedTransaction.getTransactionId());
            purchase.setCreatedBy(purchaseDTO.getCreatedBy());
            Purchase savedPurchase = null;
            try {
                //   log.info("Purchase data : {}", purchaseRepo.save(purchase));
                savedPurchase = purchaseRepo.save(purchase);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase data saving failed reason: " + e.getMessage());
            }

            List<PurchaseParticularsDTO> purchaseParticularsDTOS = purchaseDTO.getPurchaseParticulars();
            for (int i = 0; i < purchaseParticularsDTOS.size(); i++) {
                PurchaseParticularsDTO particularsDTO = purchaseParticularsDTOS.get(i);
                PurchaseParticulars purchaseParticulars = new PurchaseParticulars();
                purchaseParticulars.setProductId(particularsDTO.getProductId());
                purchaseParticulars.setHsnCode(particularsDTO.getHsnCode());
                purchaseParticulars.setQuantity(particularsDTO.getQuantity());
                purchaseParticulars.setUnit(particularsDTO.getUnit());
                purchaseParticulars.setBilledQty(particularsDTO.getBilledQty());
                purchaseParticulars.setFreeQty(particularsDTO.getFreeQty());
                purchaseParticulars.setAlternateUnit(particularsDTO.getAlternateUnit());
                purchaseParticulars.setConvFactor(particularsDTO.getConvFactor());
                purchaseParticulars.setRate(particularsDTO.getRate());
                purchaseParticulars.setDiscPer(particularsDTO.getDiscPer());
                purchaseParticulars.setDiscount(particularsDTO.getDiscount());
                purchaseParticulars.setAmount(particularsDTO.getAmount());
                purchaseParticulars.setTaxId(particularsDTO.getTaxId());
                purchaseParticulars.setTaxType(particularsDTO.getTaxType());
                purchaseParticulars.setTaxationType(particularsDTO.getTaxationType());
                purchaseParticulars.setTaxPer(particularsDTO.getTaxPer());
                purchaseParticulars.setTaxableValue(particularsDTO.getTaxableValue());
                purchaseParticulars.setPurchaseId(savedPurchase.getPurchaseId());
                purchaseParticulars.setPurchaseDate(savedPurchase.getPurchaseDate());
                purchaseParticulars.setBranchId(savedPurchase.getBranchId());
                purchaseParticulars.setCompanyId(savedPurchase.getCompanyId());

                try {
                    purchaseParticularsRepo.save(purchaseParticulars);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase Particulars data saving failed reason: " + e.getMessage());
                }
            }

            List<PurchaseProductTaxesDTO> purchaseProductTaxesDTO = purchaseDTO.getPurchaseProductTaxesDTO();
            for (int i = 0; i < purchaseProductTaxesDTO.size(); i++) {
                PurchaseProductTaxesDTO productTaxesDTO = purchaseProductTaxesDTO.get(i);
                PurchaseProductTaxes productTaxes = new PurchaseProductTaxes();
                productTaxes.setPurchaseId(savedPurchase.getPurchaseId());
                productTaxes.setPurchaseDate(savedPurchase.getPurchaseDate());
                productTaxes.setTaxableValue(productTaxesDTO.getTaxableValue());
                productTaxes.setTaxId(productTaxesDTO.getTaxId());
                productTaxes.setTaxType(productTaxesDTO.getTaxType());
                productTaxes.setTaxName(productTaxesDTO.getTaxName());
                productTaxes.setTaxPer(productTaxesDTO.getTaxPer());
                productTaxes.setTaxAmount(productTaxesDTO.getTaxAmount());

                try {
                    purchaseProductTaxesRepo.save(productTaxes);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "purchase Product Taxes data saving failed reason: " + e.getMessage());
                }
            }

            List<PurchaseExpensesDTO> purchaseExpensesDTO = purchaseDTO.getPurchaseExpensesDTO();
            for (int i = 0; i < purchaseExpensesDTO.size(); i++) {
                PurchaseExpensesDTO expensesDTO = purchaseExpensesDTO.get(i);
                PurchaseExpenses expenses = new PurchaseExpenses();
                expenses.setPurchaseId(savedPurchase.getPurchaseId());
                expenses.setLedgerId(expensesDTO.getLedgerId());
                expenses.setChargesId(expensesDTO.getChargesId());
                expenses.setTaxId(expensesDTO.getTaxId());
                expenses.setValueOfField(expensesDTO.getValueOfField());
                expenses.setPercent(expensesDTO.getPercent());
                expenses.setAmount(expensesDTO.getAmount());
                try {
                    purchaseExpensesRepo.save(expenses);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "purchase Expenses data saving failed reason: " + e.getMessage());
                }
            }

            // 1st entry for Credit
            TransactionDetails detail1 = new TransactionDetails();
            detail1.setTransactionId(savedTransaction.getTransactionId());
            detail1.setTransactionNo(savedTransaction.getTransactionNo());
            detail1.setLedgerId(ledgerId);
            detail1.setLedgerName(ledgerName);
            detail1.setTransactionDate(savedTransaction.getTransactionDate());
            detail1.setVoucherType(savedTransaction.getVoucherType());
            detail1.setVoucherNo(savedTransaction.getVoucherNo());
            detail1.setPaymentMode("By Purchase");
            detail1.setDebit(0.0);
            detail1.setCredit(purchaseDTO.getGrandTotal());
            detail1.setDBcR("Cr");
            detail1.setConfirm("1");
            detail1.setConfirmedBy(1);
            detail1.setConfirmationDate(LocalDate.now());
            detail1.setCreatedBy(1);
            detail1.setChequeDate(null);
            detail1.setChequeNo(null);
            detail1.setRefNo(purchaseDTO.getReferenceNo());
            detail1.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            detail1.setBranchId(purchaseDTO.getBranchId());
            detail1.setNarration("By purchase purchase no. " + savedPurchase.getPurchaseNo());
            detail1.setCompanyId(purchaseDTO.getCompanyId());
            detail1.setParticulars(ledgerName);
            detail1.setParticularsId(ledgerId);
            detail1.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail1);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry1 data saving failed");
            }

            // 2nd entry
            TransactionDetails detail2 = new TransactionDetails();
            detail2.setTransactionId(savedTransaction.getTransactionId());
            detail2.setTransactionNo(savedTransaction.getTransactionNo());
            detail2.setLedgerId(contacts.getLedgerId());  //customer ledgerId
            detail2.setLedgerName(contacts.getContactName()); //customer ledgerName
            detail2.setTransactionDate(savedTransaction.getTransactionDate());
            detail2.setVoucherType(savedTransaction.getVoucherType());
            detail2.setVoucherNo(savedTransaction.getVoucherNo());
            detail2.setPaymentMode("By Purchase");
            detail2.setDebit(purchaseDTO.getGrandTotal());
            detail2.setCredit(0.0);
            detail2.setDBcR("Dr");
            detail2.setConfirm("1");
            detail2.setConfirmedBy(1);
            detail2.setConfirmationDate(LocalDate.now());
            detail2.setCreatedBy(1);
            detail2.setChequeDate(null);
            detail2.setChequeNo(null);
            detail2.setRefNo(purchaseDTO.getReferenceNo());
            detail2.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            detail2.setBranchId(purchaseDTO.getBranchId());
            detail2.setNarration("To purchase purchase no. " + savedPurchase.getPurchaseNo());
            detail2.setParticulars(contacts.getContactName());
            detail2.setParticularsId(contacts.getContactId());
            detail2.setCompanyId(purchaseDTO.getCompanyId());
            detail2.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail2);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry2 data saving failed");
            }

            return savedPurchase;
        } else {
            throw new IdNotFoundException("Contact id is invalid");
        }
    }

    @Transactional
    @Override
    public Purchase updatePurchase(Integer purchaseId, PurchaseDTO purchaseDTO) {

        Optional<Purchase> existingPurchase = purchaseRepo.findById(purchaseId);
        if (existingPurchase.isEmpty()) {
            throw new IdNotFoundException("Purchase id is invalid..");
        }

        Optional<Contacts> existingContacts = contactsRepo.findById(purchaseDTO.getContactId());
        if (existingContacts.isPresent()) {
            Contacts contacts = existingContacts.get();

            // Purchase creating
            Purchase purchase = existingPurchase.get();
            purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
            int purchaseNo = purchaseRepo.findMaxPurchaseNo(purchaseDTO.getCompanyId()) + 1;
            // System.out.println("Number: "+purchaseNo);
            purchase.setPurchaseNo("24-25/" + purchaseNo);
            purchase.setReferenceNo(purchaseDTO.getReferenceNo());
            purchase.setInvoiceDate(purchaseDTO.getInvoiceDate());
            purchase.setDueDate(purchaseDTO.getDueDate());
            purchase.setPoNo(purchaseDTO.getPoNo());
            purchase.setTaxationType(purchaseDTO.getTaxationType());
            purchase.setContactId(purchaseDTO.getContactId());
            purchase.setSubTotal(purchaseDTO.getSubTotal());
            purchase.setDiscPer(purchaseDTO.getDiscPer());
            purchase.setDiscount(purchaseDTO.getDiscount());
            purchase.setOtherCharges(purchaseDTO.getOtherCharges());
            purchase.setGlobalDisc(purchaseDTO.getGlobalDisc());
            purchase.setGlobalDiscPer(purchaseDTO.getGlobalDiscPer());
            purchase.setTotalTaxes(purchaseDTO.getTotalTaxes());
            purchase.setRoundOff(purchaseDTO.getRoundOff());
            purchase.setGrandTotal(purchaseDTO.getGrandTotal());
            purchase.setNotes(purchaseDTO.getNotes());
            purchase.setStatus(purchaseDTO.getStatus());
            purchase.setCompanyId(purchaseDTO.getCompanyId());
            //   purchase.setTransactionId();  // no need to update this
            purchase.setCreatedBy(purchaseDTO.getCreatedBy());
            Purchase savedPurchase = null;
            try {
                //  log.info("Purchase data : {}", purchaseRepo.save(purchase));
                savedPurchase = purchaseRepo.save(purchase);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase data saving failed reason: " + e.getMessage());
            }

            // Deleting Purchase Particular:
            try {
                purchaseParticularsRepo.deleteByPurchaseId(savedPurchase.getPurchaseId());
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase Particular data not deleted reason: " + e.getMessage());
            }

            List<PurchaseParticularsDTO> purchaseParticularsDTOS = purchaseDTO.getPurchaseParticulars();
            for (int i = 0; i < purchaseParticularsDTOS.size(); i++) {
                PurchaseParticularsDTO particularsDTO = purchaseParticularsDTOS.get(i);
                PurchaseParticulars purchaseParticulars = new PurchaseParticulars();
                purchaseParticulars.setProductId(particularsDTO.getProductId());
                purchaseParticulars.setHsnCode(particularsDTO.getHsnCode());
                purchaseParticulars.setQuantity(particularsDTO.getQuantity());
                purchaseParticulars.setUnit(particularsDTO.getUnit());
                purchaseParticulars.setBilledQty(particularsDTO.getBilledQty());
                purchaseParticulars.setFreeQty(particularsDTO.getFreeQty());
                purchaseParticulars.setAlternateUnit(particularsDTO.getAlternateUnit());
                purchaseParticulars.setConvFactor(particularsDTO.getConvFactor());
                purchaseParticulars.setRate(particularsDTO.getRate());
                purchaseParticulars.setDiscPer(particularsDTO.getDiscPer());
                purchaseParticulars.setDiscount(particularsDTO.getDiscount());
                purchaseParticulars.setAmount(particularsDTO.getAmount());
                purchaseParticulars.setTaxId(particularsDTO.getTaxId());
                purchaseParticulars.setTaxType(particularsDTO.getTaxType());
                purchaseParticulars.setTaxationType(particularsDTO.getTaxationType());
                purchaseParticulars.setTaxPer(particularsDTO.getTaxPer());
                purchaseParticulars.setTaxableValue(particularsDTO.getTaxableValue());
                purchaseParticulars.setPurchaseId(savedPurchase.getPurchaseId());
                purchaseParticulars.setPurchaseDate(savedPurchase.getPurchaseDate());
                purchaseParticulars.setBranchId(savedPurchase.getBranchId());
                purchaseParticulars.setCompanyId(savedPurchase.getCompanyId());

                try {
                    purchaseParticularsRepo.save(purchaseParticulars);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase Particulars data saving failed reason: " + e.getMessage());
                }
            }

            // Deleting purchaseProductTaxesRepo:
            try {
                purchaseProductTaxesRepo.deleteByPurchaseId(savedPurchase.getPurchaseId());
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase Product TaxesRepo data is not deleted reason: " + e.getMessage());
            }

            List<PurchaseProductTaxesDTO> purchaseProductTaxesDTO = purchaseDTO.getPurchaseProductTaxesDTO();
            for (int i = 0; i < purchaseProductTaxesDTO.size(); i++) {
                PurchaseProductTaxesDTO productTaxesDTO = purchaseProductTaxesDTO.get(i);
                PurchaseProductTaxes productTaxes = new PurchaseProductTaxes();
                productTaxes.setPurchaseId(savedPurchase.getPurchaseId());
                productTaxes.setPurchaseDate(savedPurchase.getPurchaseDate());
                productTaxes.setTaxableValue(productTaxesDTO.getTaxableValue());
                productTaxes.setTaxId(productTaxesDTO.getTaxId());
                productTaxes.setTaxType(productTaxesDTO.getTaxType());
                productTaxes.setTaxName(productTaxesDTO.getTaxName());
                productTaxes.setTaxPer(productTaxesDTO.getTaxPer());
                productTaxes.setTaxAmount(productTaxesDTO.getTaxAmount());

                try {
                    purchaseProductTaxesRepo.save(productTaxes);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "purchase Product Taxes data saving failed reason: " + e.getMessage());
                }
            }

            // Deleting Purchase Expenses:
            try {
                purchaseExpensesRepo.deleteByPurchaseId(savedPurchase.getPurchaseId());
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "purchaseExpensesRepo data is not deleted reason: " + e.getMessage());
            }

            List<PurchaseExpensesDTO> purchaseExpensesDTO = purchaseDTO.getPurchaseExpensesDTO();
            for (int i = 0; i < purchaseExpensesDTO.size(); i++) {
                PurchaseExpensesDTO expensesDTO = purchaseExpensesDTO.get(i);
                PurchaseExpenses expenses = new PurchaseExpenses();
                expenses.setPurchaseId(savedPurchase.getPurchaseId());
                expenses.setLedgerId(expensesDTO.getLedgerId());
                expenses.setChargesId(expensesDTO.getChargesId());
                expenses.setTaxId(expensesDTO.getTaxId());
                expenses.setValueOfField(expensesDTO.getValueOfField());
                expenses.setPercent(expensesDTO.getPercent());
                expenses.setAmount(expensesDTO.getAmount());
                try {
                    purchaseExpensesRepo.save(expenses);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "purchase Expenses data saving failed reason: " + e.getMessage());
                }
            }

            //Creating Transaction:
            Optional<TransactionMain> transactionMain = transactionMainRepo.findById(savedPurchase.getTransactionId());
            if (transactionMain.isEmpty()) {
                throw new IdNotFoundException("Transaction Id not found");
            }
            TransactionMain savedTransaction = transactionMain.get();

            // 1st entry for Credit
            TransactionDetails detail1 = new TransactionDetails();
            detail1.setTransactionId(savedTransaction.getTransactionId());
            detail1.setTransactionNo(savedTransaction.getTransactionNo());
            detail1.setLedgerId(ledgerId);
            detail1.setLedgerName(ledgerName);
            detail1.setTransactionDate(savedTransaction.getTransactionDate());
            detail1.setVoucherType(savedTransaction.getVoucherType());
            detail1.setVoucherNo(savedTransaction.getVoucherNo());
            detail1.setPaymentMode("By Purchase");
            detail1.setDebit(0.0);
            detail1.setCredit(purchaseDTO.getGrandTotal());
            detail1.setDBcR("Cr");
            detail1.setConfirm("1");
            detail1.setConfirmedBy(1);
            detail1.setConfirmationDate(LocalDate.now());
            detail1.setCreatedBy(1);
            detail1.setChequeDate(null);
            detail1.setChequeNo(null);
            detail1.setRefNo(purchaseDTO.getReferenceNo());
            detail1.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            detail1.setBranchId(purchaseDTO.getBranchId());
            detail1.setNarration("By purchase purchase no. " + savedPurchase.getPurchaseNo());
            detail1.setCompanyId(purchaseDTO.getCompanyId());
            //   detail1.setParticulars(ledgerName);
            detail1.setParticulars(savedPurchase.getPurchaseNo());
            //   detail1.setParticularsId(ledgerId);
            detail1.setParticularsId(savedPurchase.getPurchaseId());
            detail1.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail1);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry1 data saving failed");
            }

            // 2nd entry
            TransactionDetails detail2 = new TransactionDetails();
            detail2.setTransactionId(savedTransaction.getTransactionId());
            detail2.setTransactionNo(savedTransaction.getTransactionNo());
            detail2.setLedgerId(contacts.getLedgerId());  //customer ledgerId
            detail2.setLedgerName(contacts.getContactName()); //customer ledgerName
            detail2.setTransactionDate(savedTransaction.getTransactionDate());
            detail2.setVoucherType(savedTransaction.getVoucherType());
            detail2.setVoucherNo(savedTransaction.getVoucherNo());
            detail2.setPaymentMode("By Purchase");
            detail2.setDebit(purchaseDTO.getGrandTotal());
            detail2.setCredit(0.0);
            detail2.setDBcR("Dr");
            detail2.setConfirm("1");
            detail2.setConfirmedBy(1);
            detail2.setConfirmationDate(LocalDate.now());
            detail2.setCreatedBy(1);
            detail2.setChequeDate(null);
            detail2.setChequeNo(null);
            detail2.setRefNo(purchaseDTO.getReferenceNo());
            detail2.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
            detail2.setBranchId(savedPurchase.getBranchId());
            detail2.setNarration("To purchase purchase no. " + savedPurchase.getPurchaseNo());
            detail2.setParticulars(savedPurchase.getPurchaseNo());  //doubt
            detail2.setParticularsId(savedPurchase.getPurchaseId()); //doubt
            detail2.setCompanyId(savedPurchase.getCompanyId());
            detail2.setCreationDate(LocalDateTime.now());

            try {
                transactionDetailsRepo.save(detail2);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry2 data saving failed");
            }

            return savedPurchase;
        } else {
            throw new IdNotFoundException("Contact is invalid");
        }
    }

    @Override
    public Purchase getPurchaseById(Integer purchaseId) {
        return purchaseRepo.findById(purchaseId).orElseThrow(() -> new IdNotFoundException("Purchase id not found"));
    }

    @Override
    public List<Map<String, Object>> getRegisteredPurchasedSupplier(String taxationType, LocalDate startDate, LocalDate endDate) {

        List<Object[]> results = purchaseRepo.findPurchaseDetailsByTaxationTypeAndDateRange(taxationType, startDate, endDate);

        List<Map<String, Object>> purchaseReport = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("Supplier Name", result[0]);
            map.put("city", result[1]);
            map.put("purchaseNo", result[2]);
            map.put("invoiceDate", result[3]);
            map.put("billAmount", result[4]);

            purchaseReport.add(map);
        }
        log.debug("Checking data :{} ",purchaseReport);

        return purchaseReport;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> deletePurchase(Integer purchaseId) {
        Optional<Purchase> existingPurchase = purchaseRepo.findById(purchaseId);
        if (existingPurchase.isPresent()) {
            purchaseParticularsRepo.deleteByPurchaseId(purchaseId);
            purchaseExpensesRepo.deleteByPurchaseId(purchaseId);
            purchaseProductTaxesRepo.deleteByPurchaseId(purchaseId);
            purchaseRepo.deleteById(purchaseId);
            Purchase purchase = existingPurchase.get();
            try {
                transactionDetailsRepo.deleteByTransactionId(purchase.getTransactionId());
                transactionMainRepo.deleteById(purchase.getTransactionId());
            } catch (Exception e) {
                throw new IdNotFoundException("Transaction id not found reason: " + e.getMessage());
            }
            Map<String, Object> response = new HashMap<>();
            response.put("data", "Purchase data deleted successfully");
            response.put("status", true);
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Purchase id not found");
        }
    }
}
