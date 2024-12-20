package com.technix.controller;

import com.technix.dto.BillDTO;
import com.technix.entity.Bill;
import com.technix.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createBill(@RequestBody BillDTO billDTO) {
        Bill billResponse = billService.createBill(billDTO).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", billResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{billId}")
    public ResponseEntity<Map<String, Object>> updateBill(@PathVariable("billId") int billId,@RequestBody BillDTO billDTO) {
        Bill billResponse = billService.updateBill(billId,billDTO).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", billResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBillById/{billId}")
    public ResponseEntity<Map<String, Object>> getBillById(@PathVariable("billId") int billId) {
        Bill billResponse = billService.getBillById(billId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", billResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBillByCompanyId/{companyId}")
    public ResponseEntity<Map<String, Object>> getBillByCompanyId(@PathVariable("companyId") int companyId) {
        List<Bill> billList = billService.getBillByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", billList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{billId}")
    public ResponseEntity<Map<String, Object>> deleteBillById(@PathVariable("billId") int billId) {
        return billService.deleteBillById(billId);
    }
}
