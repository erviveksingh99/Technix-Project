package com.technix.service;

import com.technix.dto.BillDTO;
import com.technix.entity.Bill;
import com.technix.entity.BillParticulars;
import com.technix.entity.BillTaxationDetails;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface BillService {

    public ResponseEntity<Bill> createBill(BillDTO bill);

   // public ResponseEntity<Bill> updateBill(BillDTO bill);

    public ResponseEntity<Bill> getBillById(int billId);

    public ResponseEntity<List<Bill>> getBillByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteBillById(int billId);
}
