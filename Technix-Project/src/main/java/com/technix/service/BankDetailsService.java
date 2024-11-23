package com.technix.service;

import com.technix.entity.BankDetails;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface BankDetailsService {

    public ResponseEntity<BankDetails> createBankDetail(BankDetails bankDetails);

    public ResponseEntity<BankDetails> updateBankDetail(BankDetails bankDetails);

    public ResponseEntity<BankDetails> getBankDetailById(int bankDetailsId);

    public ResponseEntity<List<BankDetails>> getBankDetailByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteBankDetailById(int bankDetailsId);
}
