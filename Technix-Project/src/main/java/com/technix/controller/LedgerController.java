package com.technix.controller;

import com.technix.entity.Ledger;
import com.technix.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createLedger(@RequestBody Ledger ledger) {
        Ledger ledgerResponse = ledgerService.createLedger(ledger).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("ledger", ledgerResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateLedger(Ledger ledger) {
        Ledger updatedLedger = ledgerService.updateLedger(ledger).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("ledger", updatedLedger);
        response.put("status", true);
        response.put("message", "Ledger data updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLedger/{ledgerId}")
    public ResponseEntity<Map<String, Object>> getLedgerById(@RequestParam("ledgerId") int ledgerId) {
        Ledger ledgerResponse = ledgerService.getLedgerById(ledgerId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("ledger", ledgerResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLedgerByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getLedgerByCompanyId(@RequestParam("companyId") int companyId) {
        List<Ledger> ledgerList = ledgerService.getLedgerByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("ledger", ledgerList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{ledgerId}")
    public ResponseEntity<Map<String, Object>> deleteLedgerById(@RequestParam("ledgerId") int ledgerId) {
        return ledgerService.deleteLedgerById(ledgerId);
    }
}
