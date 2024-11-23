package com.technix.controller;

import com.technix.entity.BankDetails;
import com.technix.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bankDetails")
public class BankDetailsController {

    @Autowired
    private BankDetailsService bankDetailsService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createBankDetail(@RequestBody BankDetails bankDetails) {
        BankDetails details = bankDetailsService.createBankDetail(bankDetails).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", details);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateBankDetail(@RequestBody BankDetails bankDetails) {
        BankDetails details = bankDetailsService.updateBankDetail(bankDetails).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", details);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBankDetails/{bankDetailsId}")
    public ResponseEntity<Map<String, Object>> getBankDetailById(int bankDetailsId) {
        BankDetails details = bankDetailsService.getBankDetailById(bankDetailsId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", details);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBankDetailsByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getBankDetailByCompanyId(@RequestParam("companyId") int companyId) {
        List<BankDetails> detailsList = bankDetailsService.getBankDetailByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", detailsList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{bankDetailsId}")
    public ResponseEntity<Map<String, Object>> deleteBankDetailById(int bankDetailsId) {
        return bankDetailsService.deleteBankDetailById(bankDetailsId);
    }
}
