package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Account;
import com.technix.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        if (account.getAccountSubId() == 0) {
            account.setAccountSubId(null);
        } else {
            account.setAccountId(account.getAccountId());
        }
        account.setCreatedAt(LocalDateTime.now());
        try {
            return ResponseEntity.ok(accountRepo.save(account));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account data is not saved in the database");
        }
    }

    @Override
    public ResponseEntity<Account> updateAccount(Account account) {
        Optional<Account> account1 = accountRepo.findById(account.getAccountId());
        if (account1.isPresent()) {
            Account account2 = account1.get();
            if (account2.getAccountSubId() == 0) {
                account2.setAccountSubId(null);
            } else {
                account2.setAccountId(account.getAccountId());
            }
            account2.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(accountRepo.save(account2));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account data is not saved in the database");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Account> getAccountById(int accountId) {
        return ResponseEntity.ok(accountRepo.findByAccountId(accountId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<Account>> getAccountByCompanyId(int companyId) {
        List<Account> accountList = accountRepo.findByCompanyId(companyId);
        if (!accountList.isEmpty()) {
            return ResponseEntity.ok(accountList);
        } else {
            return ResponseEntity.ok(accountList);
        }
    }

    //**************************************************************************************************************
    @Override
    public ResponseEntity<List<Account>> getAccountWithLedgerByCompanyId(int companyId) {
        boolean accountExist = accountRepo.existsByCompanyId(companyId);
        List<Account> accountList = new ArrayList<>();
        if (!accountExist) {
            return ResponseEntity.ok(accountList);
        }
        accountList = accountRepo.findByCompanyIdAndAccountSubId(companyId);

        // Filter ledgers and child accounts
        accountList.forEach(account -> {
            account.setLedger(account.getLedger().stream()
                    .filter(ledger -> ledger.getCompanyId() == 0 || ledger.getCompanyId() == companyId)
                    .collect(Collectors.toList()));

            account.setChildAccounts(filterChildAccounts(account.getChildAccounts(), companyId));
        });

        return ResponseEntity.ok(accountList);
    }

    private List<Account> filterChildAccounts(List<Account> childAccounts, int companyId) {
        if (childAccounts == null) {
            return Collections.emptyList();
        }

        return childAccounts.stream()
                .filter(childAccount -> childAccount.getCompanyId() == 0 || childAccount.getCompanyId() == companyId)
                .peek(childAccount -> {
                    // Filter child account's ledgers
                    childAccount.setLedger(childAccount.getLedger().stream()
                            .filter(ledger -> ledger.getCompanyId() == 0 || ledger.getCompanyId() == companyId)
                            .collect(Collectors.toList()));

                    childAccount.setChildAccounts(filterChildAccounts(childAccount.getChildAccounts(), companyId));
                })
                .collect(Collectors.toList());
    }

    //*********************************************************************************


    @Override
    public ResponseEntity<Map<String, Object>> deleteAccountById(int accountId) {
        Optional<Account> account = accountRepo.findById(accountId);
        if (account.isPresent()) {
            accountRepo.deleteById(accountId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Account data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
