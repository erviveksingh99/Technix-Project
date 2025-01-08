package com.technix.controller;

import com.technix.entity.FinancialPeriod;
import com.technix.service.FinancialPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/financialPeriod")
public class FinancialPeriodController {

    @Autowired
    private FinancialPeriodService financialPeriodService;

    @PostMapping("/create")
    public Map<String, Object> createFinancialPeriod(@RequestBody FinancialPeriod financialPeriod) {
        FinancialPeriod period = financialPeriodService.createFinancialPeriod(financialPeriod);
        Map<String, Object> response = new HashMap<>();
        response.put("data", period);
        response.put("status", true);
        return response;
    }

    @DeleteMapping("/delete/{financialPeriodId}")
    public Map<String, Object> deleteFinancialPeriod(@PathVariable int financialPeriodId) {
        return financialPeriodService.deleteFinancialPeriod(financialPeriodId);
    }
}
