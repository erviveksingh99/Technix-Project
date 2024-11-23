package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Contacts;
import com.technix.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsRepository contactsRepo;

    @Override
    public ResponseEntity<Contacts> createContacts(Contacts contacts) {
        try {
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
                Contacts contacts1=contactsOptional.get();
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
        return ResponseEntity.ok(contactsRepo.findById(contactId)
                .orElseThrow(() -> new IdNotFoundException("Id not found")));
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
