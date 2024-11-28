package com.technix.controller;

import com.technix.entity.Account;
import com.technix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody Account account) {
        Account accountResponse = accountService.createAccount(account).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateAccount(@RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(account).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", updatedAccount);
        response.put("status", true);
        response.put("message", "Account data updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAccount/{accountId}")
    public ResponseEntity<Map<String, Object>> getAccountById(@RequestParam("accountId") int accountId) {
        Account accountResponse = accountService.getAccountById(accountId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAccountByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountByCompanyId(@RequestParam("companyId") int companyId) {
        List<Account> accountList = accountService.getAccountByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Map<String, Object>> deleteAccountById(@RequestParam("accountId") int accountId) {
        return accountService.deleteAccountById(accountId);
    }

    @GetMapping("/getAccountWithLedger/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountWithLedgerByCompanyId(@RequestParam("companyId") int companyId) {
        List<Account> accountWithLedgerList = accountService.getAccountWithLedgerByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountWithLedgerList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
