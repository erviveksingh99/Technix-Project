package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.dto.BillDTO;
import com.technix.dto.BillParticularsDTO;
import com.technix.dto.BillTaxationDetailsDTO;
import com.technix.entity.*;
import com.technix.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    private BillRepository billRepo;
    private BillParticularsRepository billParticularsRepo;
    private BillTaxationDetailsRepository billTaxationDetailsRepo;
    private ContactsRepository contactsRepo;
    private TransactionMainRepository transactionMainRepo;
    private TransactionDetailsRepository transactionDetailsRepo;


    @Autowired
    public BillServiceImpl(BillRepository billRepo,
                           BillParticularsRepository billParticularsRepo,
                           BillTaxationDetailsRepository billTaxationDetailsRepo,
                           ContactsRepository contactsRepo,
                           TransactionMainRepository transactionMainRepo,
                           TransactionDetailsRepository transactionDetailsRepo) {
        this.billRepo = billRepo;
        this.billParticularsRepo = billParticularsRepo;
        this.billTaxationDetailsRepo = billTaxationDetailsRepo;
        this.contactsRepo = contactsRepo;
        this.transactionMainRepo = transactionMainRepo;
        this.transactionDetailsRepo = transactionDetailsRepo;
    }

    final static int ledgerId = 20;
    final static String ledgerName = "Sales A/c";
    final static String voucherType = "Sales";

    @Override
    public ResponseEntity<Bill> createBill(BillDTO billDTO) {

        Optional<Contacts> existingContacts = contactsRepo.findById(billDTO.getContactId());
        TransactionMain savedTransaction = null;
        if (existingContacts.isPresent()) {
            Contacts contacts = existingContacts.get();
            TransactionMain transactionMain1 = new TransactionMain();

            transactionMain1.setCompanyId(billDTO.getCompanyId());
            transactionMain1.setTransactionNo(transactionMainRepo.findMaxTransactionNo(billDTO.getCompanyId()) + 1);
            transactionMain1.setVoucherType(voucherType);
            transactionMain1.setVoucherNo(transactionMainRepo.findMaxVoucherNo(voucherType, billDTO.getCompanyId()) + 1);
            transactionMain1.setFinancialPeriodId(1);
            transactionMain1.setTransactionDate(LocalDate.now());

            try {
                savedTransaction = transactionMainRepo.save(transactionMain1);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction main data saving failed");
            }

            // Bill creating
            try {
                Bill bill = new Bill();
                bill.setCompanyId(billDTO.getCompanyId());
                bill.setBillDate(billDTO.getBillDate());
                int maxInvoiceNo = billRepo.findMaxInvoiceNo(billDTO.getCompanyId()) + 1;  // Get max invoice no and increment
                String newInvoiceNo = "TAX/24-25/" + maxInvoiceNo;  // Append the new number to the prefix
                bill.setInvoiceNo(newInvoiceNo);  // Set the new invoice number

                //  bill.setInvoiceNo("TAX/24-25/"+billRepo.findMaxInvoiceNo(billDTO.getCompanyId())+1);
                bill.setReferenceNo(billDTO.getReferenceNo());
                bill.setTransactionId(savedTransaction.getTransactionId());
                bill.setDueDate(billDTO.getDueDate());
                bill.setContactId(billDTO.getContactId());
                bill.setCustomerName(billDTO.getCustomerName());
                bill.setCustomerAddress(billDTO.getCustomerAddress());
                bill.setCustomerEmail(billDTO.getCustomerEmail());
                bill.setCustomerContactNo(billDTO.getCustomerContactNo());
                bill.setPlaceOfSupply(billDTO.getPlaceOfSupply());
                bill.setSubTotal(billDTO.getSubTotal());
                bill.setDiscPer(billDTO.getDiscPer());
                bill.setDiscount(billDTO.getDiscount());
                bill.setOtherCharges(billDTO.getOtherCharges());
                bill.setTotalTaxes(billDTO.getTotalTaxes());
                bill.setRoundOff(billDTO.getRoundOff());
                bill.setGrandTotal(billDTO.getGrandTotal());
                bill.setNotes(billDTO.getNotes());
                bill.setSalesMan(billDTO.getSalesMan());
                bill.setSalesManId(billDTO.getSalesManId());
                bill.setStatus(billDTO.getStatus());
                bill.setBranchId(billDTO.getBranchId());
                bill.setCreatedBy(billDTO.getCreatedBy());
                Bill savedBill = null;
                try {
                    billRepo.save(bill);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill data saving failed reason: " + e.getMessage());
                }

                List<BillParticularsDTO> billParticularsDTOS = billDTO.getBillParticularsDTO();
                for (int i = 0; i < billParticularsDTOS.size(); i++) {

                    BillParticularsDTO particularsDTO = billParticularsDTOS.get(i);
                    BillParticulars billParticulars = new BillParticulars();
                    billParticulars.setBillId(savedBill.getBillId());
                    billParticulars.setBillingDate(savedBill.getBillDate());
                    billParticulars.setProductId(particularsDTO.getProductId());
                    billParticulars.setHsnCode(particularsDTO.getHsnCode());
                    billParticulars.setQuantity(particularsDTO.getQuantity());
                    billParticulars.setUnit(particularsDTO.getUnit());
                    billParticulars.setBilledQty(particularsDTO.getBilledQty());
                    billParticulars.setFreeQty(particularsDTO.getFreeQty());
                    billParticulars.setAlternateUnit(particularsDTO.getAlternateUnit());
                    billParticulars.setConvFactor(particularsDTO.getConvFactor());
                    billParticulars.setRate(particularsDTO.getRate());
                    billParticulars.setDiscPer(particularsDTO.getDiscPer());
                    billParticulars.setDiscount(particularsDTO.getDiscount());
                    billParticulars.setAmount(particularsDTO.getAmount());
                    billParticulars.setTaxType(particularsDTO.getTaxType());
                    billParticulars.setTaxationType(particularsDTO.getTaxationType());
                    billParticulars.setTaxPer(particularsDTO.getTaxPer());
                    billParticulars.setTaxableValue(particularsDTO.getTaxableValue());
                    billParticulars.setBranchId(savedBill.getBranchId());
                    billParticulars.setCompanyId(savedBill.getCompanyId());
                    try {
                        billParticularsRepo.save(billParticulars);
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill particulars data saving failed reason: " + e.getMessage());
                    }
                }
                List<BillTaxationDetailsDTO> billTaxationDetailsDTOS = billDTO.getBillTaxationDetailsDTO();

                for (int i = 0; i < billTaxationDetailsDTOS.size(); i++) {
                    BillTaxationDetailsDTO taxationDetailsDTO = billTaxationDetailsDTOS.get(i);
                    BillTaxationDetails details = new BillTaxationDetails();
                    details.setBillId(savedBill.getBillId());
                    details.setBillingDate(savedBill.getBillDate());
                    details.setTaxableValue(taxationDetailsDTO.getTaxableValue());
                    details.setTaxType(taxationDetailsDTO.getTaxType());
                    details.setTaxName(taxationDetailsDTO.getTaxName());
                    details.setTaxPer(taxationDetailsDTO.getTaxPer());
                    details.setTaxAmount(taxationDetailsDTO.getTaxAmount());

                    try {
                        billTaxationDetailsRepo.save(details);
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill Taxation Details data saving failed reason: " + e.getMessage());
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
                detail1.setPaymentMode("By credit");
                detail1.setDebit(0.0);
                detail1.setCredit(billDTO.getGrandTotal());
                detail1.setDBcR("Cr");
                detail1.setConfirm("1");
                detail1.setConfirmedBy(1);
                detail1.setConfirmationDate(LocalDate.now());
                detail1.setCreatedBy(1);
                detail1.setChequeDate(null);
                detail1.setChequeNo(null);
                detail1.setRefNo(billDTO.getReferenceNo());
                detail1.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
                detail1.setBranchId(billDTO.getBranchId());
                detail1.setNarration("By sales invoice no. " + billDTO.getInvoiceNo());
                detail1.setCompanyId(billDTO.getCompanyId());
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
                detail2.setPaymentMode("By credit");
                detail2.setDebit(billDTO.getGrandTotal());
                detail2.setCredit(0.0);
                detail2.setDBcR("Dr");
                detail2.setConfirm("1");
                detail2.setConfirmedBy(1);
                detail2.setConfirmationDate(LocalDate.now());
                detail2.setCreatedBy(1);
                detail2.setChequeDate(null);
                detail2.setChequeNo(null);
                detail2.setRefNo(billDTO.getReferenceNo());
                detail2.setFinancialPeriodId(savedTransaction.getFinancialPeriodId());
                detail2.setBranchId(billDTO.getBranchId());
                detail2.setNarration("To sales invoice no. " + billDTO.getInvoiceNo());
                detail2.setParticulars(contacts.getContactName());
                detail2.setParticularsId(contacts.getContactId());
                detail2.setCompanyId(billDTO.getCompanyId());
                detail2.setCreationDate(LocalDateTime.now());

                try {
                    transactionDetailsRepo.save(detail2);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry2 data saving failed");
                }
                return ResponseEntity.ok(savedBill);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
            }
        } else {
            throw new IdNotFoundException("contact is not found");
        }
    }

    @Override
    public ResponseEntity<Bill> updateBill(int billId, BillDTO billDTO) {

        Optional<Bill> existingBillOptional = billRepo.findById(billId);
        if (existingBillOptional.isEmpty()) {
            throw new IdNotFoundException("Bill id not found");
        }
        Bill bill = existingBillOptional.get();
        bill.setBillId(billId);
        bill.setCompanyId(billDTO.getCompanyId());
        bill.setBillDate(billDTO.getBillDate());
        bill.setInvoiceNo(billDTO.getInvoiceNo());
        bill.setReferenceNo(billDTO.getReferenceNo());
        bill.setDueDate(billDTO.getDueDate());
        bill.setContactId(billDTO.getContactId());
        bill.setCustomerName(billDTO.getCustomerName());
        bill.setCustomerAddress(billDTO.getCustomerAddress());
        bill.setCustomerEmail(billDTO.getCustomerEmail());
        bill.setCustomerContactNo(billDTO.getCustomerContactNo());
        bill.setPlaceOfSupply(billDTO.getPlaceOfSupply());
        bill.setSubTotal(billDTO.getSubTotal());
        bill.setDiscPer(billDTO.getDiscPer());
        bill.setDiscount(billDTO.getDiscount());
        bill.setOtherCharges(billDTO.getOtherCharges());
        bill.setTotalTaxes(billDTO.getTotalTaxes());
        bill.setRoundOff(billDTO.getRoundOff());
        bill.setGrandTotal(billDTO.getGrandTotal());
        bill.setNotes(billDTO.getNotes());
        bill.setSalesMan(billDTO.getSalesMan());
        bill.setSalesManId(billDTO.getSalesManId());
        bill.setStatus(billDTO.getStatus());
        bill.setBranchId(billDTO.getBranchId());
        bill.setCreatedBy(billDTO.getCreatedBy());
        Bill savedBill = null;
        try {
            savedBill = billRepo.save(bill);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill data updating failed");
        }

        try {
            billParticularsRepo.deleteByBillId(billId);
        } catch (Exception e) {
            throw new IdNotFoundException("bill Particulars data is not deleted because bill id is invalid");
        }

        List<BillParticularsDTO> billParticularsDTOS = billDTO.getBillParticularsDTO();
        for (int i = 0; i < billParticularsDTOS.size(); i++) {

            BillParticularsDTO particularsDTO = billParticularsDTOS.get(i);
            BillParticulars billParticulars = new BillParticulars();
            billParticulars.setBillId(savedBill.getBillId());
            billParticulars.setBillingDate(savedBill.getBillDate());
            billParticulars.setProductId(particularsDTO.getProductId());
            billParticulars.setHsnCode(particularsDTO.getHsnCode());
            billParticulars.setQuantity(particularsDTO.getQuantity());
            billParticulars.setUnit(particularsDTO.getUnit());
            billParticulars.setBilledQty(particularsDTO.getBilledQty());
            billParticulars.setFreeQty(particularsDTO.getFreeQty());
            billParticulars.setAlternateUnit(particularsDTO.getAlternateUnit());
            billParticulars.setConvFactor(particularsDTO.getConvFactor());
            billParticulars.setRate(particularsDTO.getRate());
            billParticulars.setDiscPer(particularsDTO.getDiscPer());
            billParticulars.setDiscount(particularsDTO.getDiscount());
            billParticulars.setAmount(particularsDTO.getAmount());
            billParticulars.setTaxType(particularsDTO.getTaxType());
            billParticulars.setTaxationType(particularsDTO.getTaxationType());
            billParticulars.setTaxPer(particularsDTO.getTaxPer());
            billParticulars.setTaxableValue(particularsDTO.getTaxableValue());
            billParticulars.setBranchId(savedBill.getBranchId());
            billParticulars.setCompanyId(savedBill.getCompanyId());
            try {
                billParticularsRepo.save(billParticulars);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill particular data updating failed");
            }
        }

        try {
            billTaxationDetailsRepo.deleteByBillId(billId);
        } catch (Exception e) {
            throw new IdNotFoundException("bill Particulars data is not deleted beacuse bill is invalid");
        }

        List<BillTaxationDetailsDTO> billTaxationDetailsDTOS = billDTO.getBillTaxationDetailsDTO();

        for (int i = 0; i < billTaxationDetailsDTOS.size(); i++) {
            BillTaxationDetailsDTO taxationDetailsDTO = billTaxationDetailsDTOS.get(i);
            BillTaxationDetails details = new BillTaxationDetails();
            details.setBillId(savedBill.getBillId());
            details.setBillingDate(savedBill.getBillDate());
            details.setTaxableValue(taxationDetailsDTO.getTaxableValue());
            details.setTaxType(taxationDetailsDTO.getTaxType());
            details.setTaxName(taxationDetailsDTO.getTaxName());
            details.setTaxPer(taxationDetailsDTO.getTaxPer());
            details.setTaxAmount(taxationDetailsDTO.getTaxAmount());

            try {
                billTaxationDetailsRepo.save(details);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill taxation details data updating failed");
            }
        }
        Optional<TransactionMain> transactionMainOptional = transactionMainRepo.findByTransactionNo(billDTO.getTransactionId());
        if (transactionMainOptional.isPresent()) {
            TransactionMain transactionMain = transactionMainOptional.get();
            try {
                transactionDetailsRepo.deleteByTransactionId(billDTO.getTransactionId());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction details data is not deleted");
            }
            Optional<Contacts> existingContacts = contactsRepo.findById(billDTO.getContactId());
            if (existingContacts.isPresent()) {
                Contacts contacts = existingContacts.get();

                // 1st entry for Credit
                TransactionDetails detail1 = new TransactionDetails();
                detail1.setTransactionId(transactionMain.getTransactionId());
                detail1.setTransactionNo(transactionMain.getTransactionNo());
                detail1.setLedgerId(ledgerId);
                detail1.setLedgerName(ledgerName);
                detail1.setTransactionDate(transactionMain.getTransactionDate());
                detail1.setVoucherType(transactionMain.getVoucherType());
                detail1.setVoucherNo(transactionMain.getVoucherNo());
                detail1.setPaymentMode("By credit");
                detail1.setDebit(0.0);
                detail1.setCredit(billDTO.getGrandTotal());
                detail1.setDBcR("Cr");
                detail1.setConfirm("1");
                detail1.setConfirmedBy(1);
                detail1.setConfirmationDate(LocalDate.now());
                detail1.setCreatedBy(1);
                detail1.setChequeDate(null);
                detail1.setChequeNo(null);
                detail1.setRefNo(billDTO.getReferenceNo());
                detail1.setFinancialPeriodId(transactionMain.getFinancialPeriodId());
                detail1.setBranchId(billDTO.getBranchId());
                detail1.setNarration("By sales invoice no. " + billDTO.getInvoiceNo());
                detail1.setCompanyId(billDTO.getCompanyId());
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
                detail2.setTransactionId(transactionMain.getTransactionId());
                detail2.setTransactionNo(transactionMain.getTransactionNo());
                detail2.setLedgerId(contacts.getLedgerId());  //customer ledgerId
                detail2.setLedgerName(contacts.getContactName()); //customer ledgerName
                detail2.setTransactionDate(transactionMain.getTransactionDate());
                detail2.setVoucherType(transactionMain.getVoucherType());
                detail2.setVoucherNo(transactionMain.getVoucherNo());
                detail2.setPaymentMode("By credit");
                detail2.setDebit(billDTO.getGrandTotal());
                detail2.setCredit(0.0);
                detail2.setDBcR("Dr");
                detail2.setConfirm("1");
                detail2.setConfirmedBy(1);
                detail2.setConfirmationDate(LocalDate.now());
                detail2.setCreatedBy(1);
                detail2.setChequeDate(null);
                detail2.setChequeNo(null);
                detail2.setRefNo(billDTO.getReferenceNo());
                detail2.setFinancialPeriodId(transactionMain.getFinancialPeriodId());
                detail2.setBranchId(billDTO.getBranchId());
                detail2.setNarration("To sales invoice no. " + billDTO.getInvoiceNo());
                detail2.setParticulars(contacts.getContactName());
                detail2.setParticularsId(contacts.getContactId());
                detail2.setCompanyId(billDTO.getCompanyId());
                detail2.setCreationDate(LocalDateTime.now());
                try {
                    transactionDetailsRepo.save(detail2);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction entry2 data saving failed");
                }
            }
        }
        return ResponseEntity.ok(savedBill);
    }

    @Override
    public ResponseEntity<Bill> getBillById(int billId) {
        return ResponseEntity.ok(billRepo.findById(billId).orElseThrow(() -> new IdNotFoundException("Bill id not found")));
    }

    @Override
    public ResponseEntity<List<Bill>> getBillByCompanyId(int companyId) {
        List<Bill> billList = billRepo.findByCompanyId(companyId);
        if (!billList.isEmpty()) {
            return ResponseEntity.ok(billList);
        } else {
            throw new IdNotFoundException("Company id doesn't exist");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBillById(int billId) {
        Optional<Bill> bill = billRepo.findById(billId);
        if (bill.isPresent()) {
            billRepo.deleteById(billId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Bill data is deleted with id " + billId);
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
