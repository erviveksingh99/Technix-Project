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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> createContacts(@Parameter @ModelAttribute Contacts contacts,
                                                              @RequestParam("file") MultipartFile profilePicture) throws Exception {
        Contacts contactsResponse = contactsService.createContacts(contacts, profilePicture).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", contactsResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> updateContacts(@RequestParam("contactId") int contactId,
                                                              @Parameter @ModelAttribute Contacts contacts,
                                                              @RequestParam("file") MultipartFile profilePicture) throws Exception {
        Contacts updatedContact = contactsService.updateContacts(contactId, contacts, profilePicture).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", updatedContact);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getContact/{contactId}")
    public ResponseEntity<Map<String, Object>> getContactsById(@RequestParam("contactId") int contactId) {
        Contacts contacts = contactsService.getContactsById(contactId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", contacts);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getContactByCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getContactsByCompanyId(@RequestParam("companyId") int companyId) {
        List<Contacts> contactsList = contactsService.getContactsByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("data", contactsList);
        response.put("status", true);
        return ResponseEntity.ok(response);
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
