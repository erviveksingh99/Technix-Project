package com.technix.controller;

import com.technix.entity.Contacts;
import com.technix.service.ContactsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Contacts> createContacts(@Parameter @ModelAttribute Contacts contacts, @RequestParam("file") MultipartFile profilePicture) throws Exception {
        return contactsService.createContacts(contacts, profilePicture);
    }

    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<Contacts> updateContacts(@RequestParam("contactId") int contactId, @Parameter @ModelAttribute Contacts contacts, @RequestParam("file") MultipartFile profilePicture) {
        return contactsService.updateContacts(contactId, contacts, profilePicture);
    }

    @GetMapping("/getContact/{contactId}")
    public ResponseEntity<Contacts> getContactsById(@RequestParam("contactId") int contactId) {
        return contactsService.getContactsById(contactId);
    }

    @GetMapping("/getContactByCompany/{companyId}")
    public ResponseEntity<List<Contacts>> getContactsByCompanyId(@RequestParam("companyId") int companyId) {
        return contactsService.getContactsByCompanyId(companyId);
    }

    @GetMapping("/profilePic/{contactId}")
    public ResponseEntity<UrlResource> getContactProfilePic(@RequestParam("contactId") int contactId) throws MalformedURLException, FileNotFoundException {
        return contactsService.getContactProfilePic(contactId);
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<Map<String, Object>> deleteContacts(@RequestParam("contactId") int contactId) {
        return contactsService.deleteContacts(contactId);
    }
}
