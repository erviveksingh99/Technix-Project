package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.BankDetails;
import com.technix.repository.BankDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

    @Autowired
    private BankDetailsRepository bankDetailsRepo;

    @Override
    public ResponseEntity<BankDetails> createBankDetail(BankDetails bankDetails) {
        try {
            bankDetails.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(bankDetailsRepo.save(bankDetails));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<BankDetails> updateBankDetail(BankDetails bankDetails) {
        Optional<BankDetails> details = bankDetailsRepo.findById(bankDetails.getId());
        if (details.isPresent()) {

            /*BankDetails bankDetails1 = details.get();
            bankDetails1.setId(bankDetails.getId());
            bankDetails1.setCompanyId(bankDetails.getCompanyId());
            bankDetails1.setBankAccountNature(bankDetails.getBankAccountNature());
            bankDetails1.setAccountNumber(bankDetails.getAccountNumber());
            bankDetails1.setAccountName(bankDetails.getAccountName());
            bankDetails1.setBankName(bankDetails.getBankName());
            bankDetails1.setBranchName(bankDetails.getBranchName());
            bankDetails1.setBranchAddress(bankDetails.getBranchAddress());
            bankDetails1.setIfscCode(bankDetails.getIfscCode());
            bankDetails1.setSigningAuthority(bankDetails.getSigningAuthority());
            bankDetails1.setAccountType(bankDetails.getAccountType());
            bankDetails1.setIbanNo(bankDetails.getIbanNo());
            bankDetails1.setSwiftCode(bankDetails.getSwiftCode());
            bankDetails1.setUpiNo(bankDetails.getUpiNo());
            bankDetails1.setCreatedBy(bankDetails.getCreatedBy());
            */

            BankDetails bankDetails1 = details.get();
            // BeanUtils.copyProperties() is optional if we don't want to set and get manually then we can use this method.
            // Copy non-null properties from the incoming bankDetails to the existing one
            BeanUtils.copyProperties(bankDetails, bankDetails1, "createdAt"); // Exclude fields like id, createdAt if necessary
            bankDetails1.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(bankDetailsRepo.save(bankDetails1));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<BankDetails> getBankDetailById(int bankDetailsId) {
        return ResponseEntity.ok(bankDetailsRepo.findById(bankDetailsId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<BankDetails>> getBankDetailByCompanyId(int companyId) {
        List<BankDetails> bankDetailsList = bankDetailsRepo.findByCompanyId(companyId);
        if (!bankDetailsList.isEmpty()) {
            return ResponseEntity.ok(bankDetailsList);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankDetailById(int bankDetailsId) {
        Optional<BankDetails> details = bankDetailsRepo.findById(bankDetailsId);
        if (details.isPresent()) {
            bankDetailsRepo.deleteById(bankDetailsId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Bank details data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
