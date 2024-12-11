package com.technix.utility;


import com.technix.repository.TransactionMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateNumbers {

    private TransactionMainRepository transactionMainRepo;

    @Autowired
    public GenerateNumbers(TransactionMainRepository transactionMainRepo) {
        this.transactionMainRepo = transactionMainRepo;
    }

    public GenerateNumbers() {
    }

    public int findMaxTransactionNumber(int companyId) {
        return transactionMainRepo.findMaxTransactionNo(companyId);
    }

    public int findMaxVoucherNo(String voucherType, int companyId) {
        return transactionMainRepo.findMaxVoucherNo(voucherType, companyId);
    }
}
