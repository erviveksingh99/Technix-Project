package com.technix.controller;

import com.technix.entity.OpeningBalance;
import com.technix.service.OpeningBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/openingBalance")
public class OpeningBalanceController {

    @Autowired
    private OpeningBalanceService openingBalanceService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOpeningBalance(OpeningBalance openingBalance) {
        OpeningBalance balance = openingBalanceService.createOpeningBalance(openingBalance);
        Map<String, Object> response = new HashMap<>();
        response.put("data", balance);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/openingBalanceById/{openingBalanceId}")
    public ResponseEntity<Map<String, Object>> getOpeningBalanceById(@PathVariable("openingBalanceId") int openingBalanceId) {
        OpeningBalance balance = openingBalanceService.getOpeningBalanceById(openingBalanceId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", balance);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
