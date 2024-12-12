package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.dto.BillDTO;
import com.technix.dto.BillParticularsDTO;
import com.technix.dto.BillTaxationDetailsDTO;
import com.technix.entity.Bill;
import com.technix.entity.BillParticulars;
import com.technix.entity.BillTaxationDetails;
import com.technix.repository.BillParticularsRepository;
import com.technix.repository.BillRepository;
import com.technix.repository.BillTaxationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepo;

    @Autowired
    private BillParticularsRepository billParticularsRepo;

    @Autowired
    private BillTaxationDetailsRepository billTaxationDetailsRepo;

    @Override
    public ResponseEntity<Bill> createBill(BillDTO billDTO) {
//        bill.setCreatedAt(LocalDateTime.now());
        try {
            Bill bill = new Bill();
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
            bill.setBranchId(bill.getBranchId());
            bill.setSalesMan(billDTO.getSalesMan());
            bill.setSalesManId(billDTO.getSalesManId());
            bill.setStatus(billDTO.getStatus());
            bill.setCompanyId(billDTO.getBranchId());
            bill.setCreatedBy(billDTO.getCreatedBy());

            Bill savedBill = billRepo.save(bill);

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

                billParticularsRepo.save(billParticulars);
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

                billTaxationDetailsRepo.save(details);
            }
            return ResponseEntity.ok(savedBill);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
        }
    }

   /* @Override
    public ResponseEntity<Bill> updateBill(BillDTO billDTO) {
        try {
            billParticularsRepo.deleteByBillId(bill.getBillId());
            billTaxationDetailsRepo.deleteByBillId(bill.getBillId());
        } catch (Exception e) {
            throw new IdNotFoundException("Billing id doesn't exist");
        }

        Optional<Bill> bill1 = billRepo.findById(bill.getBillId());
        if (bill1.isPresent()) {
            Bill bill2 = bill1.get();
            bill2.setBillId(bill.getBillId());
            bill2.setCompanyId(bill.getCompanyId());
            bill2.setContactId(bill.getContactId());
            bill2.setBillDate(bill.getBillDate());
            bill2.setInvoiceNo(bill.getInvoiceNo());
            bill2.setReferenceNo(bill.getReferenceNo());
            bill2.setDueDate(bill.getDueDate());
            bill2.setCustomerName(bill.getCustomerName());
            bill2.setCustomerAddress(bill.getCustomerAddress());
            bill2.setCustomerEmail(bill.getCustomerEmail());
            bill2.setCustomerContactNo(bill.getCustomerContactNo());
            bill2.setPlaceOfSupply(bill.getPlaceOfSupply());
            bill2.setSubTotal(bill.getSubTotal());
            bill2.setDiscPer(bill.getDiscPer());
            bill2.setDiscount(bill.getDiscount());
            bill2.setOtherCharges(bill.getOtherCharges());
            bill2.setTotalTaxes(bill.getTotalTaxes());
            bill2.setRoundOff(bill.getRoundOff());
            bill2.setGrandTotal(bill.getGrandTotal());
            bill2.setBranchId(bill.getBranchId());
            bill2.setSalesManId(bill.getSalesManId());
            bill2.setSalesMan(bill.getSalesMan());
            bill2.setStatus(bill.getStatus());
            bill2.setCreatedBy(bill.getCreatedBy());
            bill2.setCreatedAt(LocalDateTime.now());
            try {
                Bill savedBill = billRepo.save(bill2);

                Optional<BillParticulars> billParticular = billParticularsRepo.findById(billParticulars.getRowId());
                if (billParticular.isPresent()) {
                    BillParticulars billParticulars1 = billParticular.get();
                    billParticulars1.setRowId(billParticulars.getRowId());
                    billParticulars1.setBillId(savedBill.getBillId());
                    billParticulars1.setBillingDate(savedBill.getBillDate());
                    billParticulars1.setCompanyId(savedBill.getCompanyId());
                    try {
                        billParticularsRepo.save(billParticulars1);
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill particulars data saving failed");
                    }
                }

                Optional<BillTaxationDetails> taxationDetails = billTaxationDetailsRepo.findById(billTaxationDetails.getRowId());
                if (taxationDetails.isPresent()) {
                    BillTaxationDetails details = taxationDetails.get();
                    details.setRowId(billTaxationDetails.getRowId());
                    details.setBillId(savedBill.getBillId());
                    details.setBillingDate(savedBill.getBillDate());
                    try {
                        billTaxationDetailsRepo.save(details);
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill taxation details data saving failed");
                    }
                }
                return ResponseEntity.ok(savedBill);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
            }
        } else {
            throw new IdNotFoundException("Bill Id not found");
        }
    }*/

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
