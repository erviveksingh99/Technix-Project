package com.technix.service;

import com.technix.entity.FinancialPeriod;

import java.util.Map;

public interface FinancialPeriodService {

    public FinancialPeriod createFinancialPeriod(FinancialPeriod financialPeriod);

    public Map<String, Object> deleteFinancialPeriod(int financialPeriodId);
}
