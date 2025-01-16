package com.technix.service;

import com.technix.entity.Contacts;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface ContactsService {

    public ResponseEntity<Contacts> createContacts(Contacts contacts, MultipartFile profilePicture) throws Exception;

    public ResponseEntity<Contacts> updateContacts(int contactId, Contacts contacts, MultipartFile profilePic) throws Exception;

    public ResponseEntity<Contacts> getContactsById(int contactId);

    public ResponseEntity<List<Contacts>> getContactsByCompanyId(int companyId);

    public ResponseEntity<UrlResource> getContactProfilePic(int contactId) throws MalformedURLException, FileNotFoundException;

    public  List<Contacts> listOfRegisteredCustomer(String taxationType);

    public List<Map<String, Object>> listOfSalesRegisteredCustomer(String taxationType);

    public ResponseEntity<Map<String, Object>> deleteContacts(int contactId);
}
