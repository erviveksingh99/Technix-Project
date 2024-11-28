package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Account;
import com.technix.entity.Ledger;
import com.technix.repository.AccountRepository;
import com.technix.repository.LedgerRepository;
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
public class LedgerServiceImpl implements LedgerService {

    @Autowired
    private LedgerRepository ledgerRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public ResponseEntity<Ledger> createLedger(Ledger ledger) {
        try {
            Optional<Account> account = accountRepo.findByAccountId(ledger.getAccountId());
            if (account.isPresent()) {
                Account account1 = account.get();
                ledger.setAccountNature(account1.getAccountNature());
                ledger.setAccount(account1.getAccount());
            } else {
                throw new IdNotFoundException("Account id is invalid");
            }

            ledger.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(ledgerRepo.save(ledger));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Ledger> updateLedger(Ledger ledger) {
        Optional<Ledger> details = ledgerRepo.findById(ledger.getLedgerId());
        if (details.isPresent()) {
            Ledger updateLedger = details.get();
            // BeanUtils.copyProperties() is optional if we don't want to set and get manually then we can use this method.
            // Copy non-null properties from the incoming bankDetails to the existing one
            BeanUtils.copyProperties(ledger, updateLedger, "createdAt", "accountNature", "account"); // Exclude fields like id, createdAt if necessary

            Optional<Account> account = accountRepo.findByAccountId(ledger.getAccountId());
            if (account.isPresent()) {
                Account account1 = account.get();
                updateLedger.setAccountNature(account1.getAccountNature());
                updateLedger.setAccount(account1.getAccount());
            } else {
                throw new IdNotFoundException("Account id is invalid");
            }
            updateLedger.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(ledgerRepo.save(updateLedger));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Ledger> getLedgerById(int ledgerId) {
        return ResponseEntity.ok(ledgerRepo.findById(ledgerId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<Ledger>> getLedgerByCompanyId(int companyId) {
        List<Ledger> ledgerList = ledgerRepo.findByCompanyId(companyId);
        if (!ledgerList.isEmpty()) {
            return ResponseEntity.ok(ledgerList);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteLedgerById(int ledgerId) {
        Optional<Ledger> ledger = ledgerRepo.findById(ledgerId);
        if (ledger.isPresent()) {
            ledgerRepo.deleteById(ledgerId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Ledger data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
