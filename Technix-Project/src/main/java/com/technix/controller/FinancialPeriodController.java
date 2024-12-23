package com.technix.controller;

import com.technix.entity.FinancialPeriod;
import com.technix.service.FinancialPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financialPeriod")
public class FinancialPeriodController {

    @Autowired
    private FinancialPeriodService financialPeriodService;

    @PostMapping("/create")
    public FinancialPeriod createFinancialPeriod(@RequestBody FinancialPeriod financialPeriod) {
        return financialPeriodService.createFinancialPeriod(financialPeriod);
    }
}
