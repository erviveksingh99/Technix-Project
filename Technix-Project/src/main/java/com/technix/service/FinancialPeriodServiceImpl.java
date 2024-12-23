package com.technix.service;

import com.technix.entity.FinancialPeriod;
import com.technix.repository.FinancialPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FinancialPeriodServiceImpl implements FinancialPeriodService {

    @Autowired
    private FinancialPeriodRepository financialPeriodRepo;

    @Override
    public FinancialPeriod createFinancialPeriod(FinancialPeriod financialPeriod) {
        try {
            return financialPeriodRepo.save(financialPeriod);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FinancialPeriod creation failed");
        }
    }
}
