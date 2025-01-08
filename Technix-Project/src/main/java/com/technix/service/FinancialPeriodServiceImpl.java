package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.FinancialPeriod;
import com.technix.repository.FinancialPeriodRepository;
import com.technix.repository.OpeningBalanceRepository;
import com.technix.repository.TransactionMainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FinancialPeriodServiceImpl implements FinancialPeriodService {

    @Autowired
    private FinancialPeriodRepository financialPeriodRepo;

    @Autowired
    private OpeningBalanceRepository openingBalanceRepo;

    @Autowired
    private TransactionMainRepository transactionMainRepo;

    @Override
    public FinancialPeriod createFinancialPeriod(FinancialPeriod financialPeriod) {
        try {
            return financialPeriodRepo.save(financialPeriod);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FinancialPeriod creation failed");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Map<String, Object> deleteFinancialPeriod(int financialPeriodId) {
        Optional<FinancialPeriod> financialPeriod = financialPeriodRepo.findById(financialPeriodId);
        if (financialPeriod.isEmpty()) {
            throw new IdNotFoundException("Financial period id is not found");
        }

        boolean existingOpeningBalance;
        boolean existingTransaction;
        try {
            existingOpeningBalance = openingBalanceRepo.existsByFinancialPeriodId(financialPeriodId);
            existingTransaction = transactionMainRepo.existsByFinancialPeriodId(financialPeriodId);
        } catch (Exception e) {
            throw new IdNotFoundException("financial period id not found in opening balance & transaction reason: " + e.getMessage());
        }

        Map<String, Object> response = new HashMap<>();
        if (existingOpeningBalance && existingTransaction) {
            response.put("message", "You can't delete financial period because your opening balance & transaction is already exist");
            response.put("status", false);
            return response;
        } else {
            try {
                financialPeriodRepo.deleteById(financialPeriodId);
            } catch (Exception e) {
                throw new IdNotFoundException("financial period id not found in financial period reason: " + e.getMessage());
            }
            response.put("message", "Your financial period is deleted successfully");
            response.put("status", true);
            return response;
        }
    }
}
