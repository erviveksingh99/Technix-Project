package com.technix.service;

import com.technix.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AccountService {

    public ResponseEntity<Account> createAccount(Account account);

    public ResponseEntity<Account> updateAccount(Account accountDTO);

    public ResponseEntity<Account> getAccountById(int accountId);

    public ResponseEntity<List<Account>> getAccountByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteAccountById(int accountId);

    public ResponseEntity<List<Account>> getAccountWithLedgerByCompanyId(int companyId);
}
