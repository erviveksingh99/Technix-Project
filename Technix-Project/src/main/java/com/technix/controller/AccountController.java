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
        Account accountResponse = accountService.updateAccount(account).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accountById/{accountId}/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable("accountId") int accountId, @PathVariable("companyId") int companyId) {
        Account accountResponse = accountService.getAccountById(accountId, companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountByCompanyId(@RequestParam("companyId") int companyId) {
        List<Account> accountResponse = accountService.getAccountByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    //  @CrossOrigin("/**")
    @GetMapping("/accountWithLedger/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountWithLedgerByCompanyId(@PathVariable int companyId) {
        List<Account> accountResponse = accountService.getAccountWithLedgerByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balancesheet/{companyId}")
    public ResponseEntity<Map<String, Object>> getAccountWithAssetAndLiabilities(@RequestParam("companyId") int companyId) {
        List<Account> accountResponse = accountService.getAccountWithAssetAndLiabilities(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("account", accountResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Map<String, Object>> deleteAccountById(@PathVariable int accountId) {
        return accountService.deleteAccountById(accountId);
    }
}
