package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Account;
import com.technix.entity.Ledger;
import com.technix.repository.AccountRepository;
import com.technix.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private LedgerRepository ledgerRepo;

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        if (account.getAccountSubId() == 0) {
            account.setAccountSubId(null); // No parent account
        } else {
            account.setAccountSubId(account.getAccountSubId()); // Set valid parent account
        }
        try {
            account.setCompanyId(0);
            account.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(accountRepo.save(account));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Account> updateAccount(Account account) {
        Optional<Account> accountDetails = accountRepo.findById(account.getAccountId());
        if (accountDetails.isPresent()) {
            Account updateAccount = accountDetails.get();
            // BeanUtils.copyProperties() is optional if we don't want to set and get manually then we can use this method.
            // Copy non-null properties from the incoming bankDetails to the existing one
            BeanUtils.copyProperties(account, updateAccount, "createdAt"); // Exclude fields like id, createdAt if necessary
            updateAccount.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(accountRepo.save(updateAccount));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Account> getAccountById(int accountId) {
        return ResponseEntity.ok(accountRepo.findById(accountId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    //*************************GetAccountByCompanyId*********************************
    @Override
    public ResponseEntity<List<Account>> getAccountByCompanyId(int companyId) {
        // Check if the companyId exists in the database
        boolean companyExists = accountRepo.existsByCompanyId(companyId);

        // If the companyId doesn't exist and it's not the default 0, throw an exception
        if (!companyExists && companyId != 0) {
            throw new IdNotFoundException("CompanyId: " + companyId + " does not exist.");
        }

        // Fetch accounts where companyId is either the given one or 0 (default)
        List<Account> accountList = accountRepo.findByCompanyIdAndParentId(companyId, null);

        return ResponseEntity.ok(filterAccounts(accountList, companyId));
    }

    //Method to filter and set child data in the response:
    public List<Account> filterAccounts(List<Account> accounts, int companyId) {
        return accounts.stream()
                .filter(account -> account.getCompanyId() == 0 || account.getCompanyId() == companyId)
                .peek(account -> account.setChildAccounts(filterAccounts(account.getChildAccounts(), companyId)))
                .collect(Collectors.toList());
    }


    //*************************************************************************************************************

    @Override
    public ResponseEntity<Map<String, Object>> deleteAccountById(int accountId) {
        Optional<Account> ledger = accountRepo.findById(accountId);
        if (ledger.isPresent()) {
            accountRepo.deleteById(accountId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Account data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }



   /* @Override
    public ResponseEntity<List<AccountLedgerResponseDTO>> getAccountWithLedgerByCompanyId(int companyId) {
        List<Account> accounts = accountRepo.findByCompanyId(companyId);
        if (accounts.isEmpty()) {
            throw new IdNotFoundException("No accounts found for CompanyId: " + companyId);
        }
        List<AccountLedgerResponseDTO> accountLedgerResponseDTOs = new ArrayList<>();

        for (Account account : accounts) {
            List<Ledger> ledgers = ledgerRepo.findByAccountId(account.getAccountId());
            if (ledgers.isEmpty()) {
                continue;
            }
            AccountLedgerResponseDTO responseDTO = new AccountLedgerResponseDTO();
            responseDTO.setAccount(account);
            responseDTO.setLedgers(ledgers);
            accountLedgerResponseDTOs.add(responseDTO);
        }
        return ResponseEntity.ok(accountLedgerResponseDTOs);
    }*/

    @Override
    public ResponseEntity<List<Account>> getAccountWithLedgerByCompanyId(int companyId) {
        // Check if the companyId exists in the database
        boolean companyExists = accountRepo.existsByCompanyId(companyId);

        // If the companyId doesn't exist and it's not the default 0, throw an exception
        if (!companyExists && companyId != 0) {
            throw new IdNotFoundException("CompanyId: " + companyId + " does not exist.");
        }

        // Fetch accounts where companyId is either the given one or 0 (default)
        List<Account> accountList = accountRepo.findByCompanyIdAndParentId(companyId, null);

        // Filter accounts, set child accounts and fetch associated ledger details
        return ResponseEntity.ok(filterAccountsData(accountList, companyId));
    }

    public List<Account> filterAccountsData(List<Account> accounts, int companyId) {
        return accounts.stream()
                // Filter based on companyId or 0 (global)
                .filter(account -> account.getCompanyId() == 0 || account.getCompanyId() == companyId)
                .peek(account -> {
                    // Fetch child accounts recursively
                    account.setChildAccounts(filterAccountsData(account.getChildAccounts(), companyId));

                    // Fetch ledgers associated with the account
                    List<Ledger> ledgers = ledgerRepo.findByAccountId(account.getAccountId());
                    account.setLedgers(ledgers); // Attach the ledgers to the account
                })
                .collect(Collectors.toList());
    }
}






