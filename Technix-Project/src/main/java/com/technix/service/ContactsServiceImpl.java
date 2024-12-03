package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Account;
import com.technix.entity.Contacts;
import com.technix.entity.Ledger;
import com.technix.repository.AccountRepository;
import com.technix.repository.ContactsRepository;
import com.technix.repository.LedgerRepository;
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
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactsRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private LedgerRepository ledgerRepo;

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<Contacts> createContacts(Contacts contacts) {
        Optional<Account> optionalAccount = accountRepo.findById(contacts.getAccountId());
        Account account = null;
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        }
        Ledger newLedger = new Ledger();
        newLedger.setCompanyId(contacts.getCompanyId());
        newLedger.setLedgerName(contacts.getContactName());
        newLedger.setAccount(account.getAccounts());
        newLedger.setAccountNature(account.getAccountNature());
        newLedger.setAccount_id(contacts.getAccountId());
        newLedger.setOrderByNumber(0);
        newLedger.setTdsApplicable(contacts.isTdsApplicable() ? 1 : 0);
        newLedger.setActive(true);
        newLedger.setSystemDefault(false);
        newLedger.setCreatedBy(contacts.getCreatedBy());
        newLedger.setCreatedAt(LocalDateTime.now());
        Ledger l1 = null;
        try {
            l1 = ledgerRepo.save(newLedger);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ledger data is not saved from the contacts entity");
        }
        try {
            contacts.setCreatedAt(LocalDateTime.now());
            contacts.setLedgerId(l1.getLedgerId());
            return ResponseEntity.ok(contactsRepo.save(contacts));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Contacts> updateContacts(int contactId, Contacts contacts) {
        Optional<Contacts> contactsOptional = contactsRepo.findById(contactId);
        if (contactsOptional.isPresent()) {
            try {
                Contacts contacts1 = contactsOptional.get();
                contacts.setContactId(contactId);
                return ResponseEntity.ok(contactsRepo.save(contacts1));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Contacts> getContactsById(int contactId) {
        return ResponseEntity.ok(contactsRepo.findById(contactId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<Contacts>> getContactsByCompanyId(int companyId) {
        List<Contacts> contactLists = contactsRepo.findByCompanyId(companyId);
        if (!contactLists.isEmpty()) {
            return ResponseEntity.ok(contactLists);
        } else {
            throw new IdNotFoundException("Company id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteContacts(int contactId) {
        Optional<Contacts> optionalContacts = contactsRepo.findById(contactId);
        if (optionalContacts.isPresent()) {
            contactsRepo.deleteById(contactId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Contact deleted for Id number: " + contactId);
            response.put("status", true);
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
