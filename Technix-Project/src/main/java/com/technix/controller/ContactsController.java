package com.technix.controller;

import com.technix.entity.Contacts;
import com.technix.repository.ContactsRepository;
import com.technix.service.ContactsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private ContactsRepository contactsRepo;

    @PostMapping("/create")
    public ResponseEntity<Contacts> createContacts(@Parameter @ModelAttribute Contacts contacts) {
        return contactsService.createContacts(contacts);
    }

    @PutMapping("/update")
    public ResponseEntity<Contacts> updateContacts(@RequestParam("contactId") int contactId, @Parameter @ModelAttribute Contacts contacts) {
        return contactsService.updateContacts(contactId, contacts);
    }

    @GetMapping("/getContact/{contactId}")
    public ResponseEntity<Contacts> getContactsById(@RequestParam("contactId") int contactId) {
        return contactsService.getContactsById(contactId);
    }

    @GetMapping("/getContactByCompany/{companyId}")
    public ResponseEntity<List<Contacts>> getContactsByCompanyId(@RequestParam("companyId") int companyId) {
        return contactsService.getContactsByCompanyId(companyId);
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<Map<String, Object>> deleteContacts(@RequestParam("contactId") int contactId) {
        return contactsService.deleteContacts(contactId);
    }
}
