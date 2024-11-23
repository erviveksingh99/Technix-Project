package com.technix.service;

import com.technix.entity.Contacts;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ContactsService {

    public ResponseEntity<Contacts> createContacts(Contacts contacts);

    public ResponseEntity<Contacts> updateContacts(int contactId, Contacts contacts);

    public ResponseEntity<Contacts> getContactsById(int contactId);

    public ResponseEntity<List<Contacts>> getContactsByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteContacts(int contactId);
}
