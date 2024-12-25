package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.custome.PictureNotFoundException;
import com.technix.entity.Account;
import com.technix.entity.Contacts;
import com.technix.entity.Ledger;
import com.technix.entity.OpeningBalance;
import com.technix.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepo;
    private final AccountRepository accountRepo;
    private final LedgerRepository ledgerRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ContactsServiceImpl(AccountRepository accountRepo,
                               ContactsRepository contactsRepo,
                               LedgerRepository ledgerRepo,
                               PasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.contactsRepo = contactsRepo;
        this.ledgerRepo = ledgerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private OpeningBalanceRepository openingBalanceRepo;

    @Autowired
    private FinancialPeriodRepository financialPeriodRepo;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${contactImage.url}")
    private String imageUrl;

    @Override
    public ResponseEntity<Contacts> createContacts(Contacts contacts, MultipartFile profilePic) throws Exception {
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
        int contactId = 0;
        try {
            contacts.setLoginPassword(passwordEncoder.encode(contacts.getLoginPassword()));
            contacts.setCreationDate(LocalDateTime.now());
            contacts.setLedgerId(l1.getLedgerId());
            Contacts savedContact = contactsRepo.save(contacts);
            contactId = savedContact.getContactId();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }

        // Creating opening balance
        OpeningBalance openingBalance = new OpeningBalance();
        openingBalance.setCompanyId(contacts.getCompanyId());
        openingBalance.setOpeningBalance(contacts.getOppeningBalance());
        openingBalance.setLedgerId(l1.getLedgerId());
       // financialPeriodRepo
        openingBalance.setFinancialPeriodId(1);
        openingBalance.setCrDr(contacts.getOppeningType());
        openingBalance.setOpeningBalanceDate(LocalDate.now());
        try {
            openingBalanceRepo.save(openingBalance);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Opening balance saving failed reason: " + e.getMessage());
        }

        //****************************************************
        // Handle profile picture upload
        if (profilePic != null && !profilePic.isEmpty()) {
            try {
                String companyDirectoryWithId = "/contacts/" + contactId;
                File directory = new File(uploadDir + File.separator + "contacts" + File.separator + contactId);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String exte = profilePic.getOriginalFilename().substring(profilePic.getOriginalFilename().lastIndexOf("."));

                String filePath = directory.getAbsolutePath() + File.separator + contactId + exte;

                profilePic.transferTo(new File(filePath));

                contacts.setProfilePicture(companyDirectoryWithId + "/" + contactId + exte);

            } catch (IOException e) {
                throw new Exception("Failed to save company logo: " + e.getMessage());
            }
        }
        //****************************************************
        try {
            contacts.setImageUrl(imageUrl + contactId);
            return ResponseEntity.ok(contactsRepo.save(contacts));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Contacts> updateContacts(int contactId, Contacts contacts, MultipartFile profilePic) throws Exception {
        Optional<Contacts> contactsOptional = contactsRepo.findById(contactId);
        if (contactsOptional.isPresent()) {
            try {
                Contacts contacts1 = contactsOptional.get();

                contacts1.setContactId(contactId);
                contacts1.setCompanyId(contacts.getCompanyId());
                contacts1.setContactsType(contacts.getContactsType());
                contacts1.setContactCode(contacts.getContactCode());
                contacts1.setContactName(contacts.getContactName());
                contacts1.setBusinessType(contacts.getBusinessType());
                contacts1.setBusinessName(contacts.getBusinessName());
                contacts1.setWorkPhone(contacts.getWorkPhone());
                contacts1.setWorkEmail(contacts.getWorkEmail());
                contacts1.setFax(contacts.getFax());
                contacts1.setWebsite(contacts.getWebsite());
                contacts1.setTitle(contacts.getTitle());
                contacts1.setContactPerson(contacts.getContactPerson());
                contacts1.setDesignation(contacts.getDesignation());
                contacts1.setMobileNumber(contacts.getMobileNumber());
                contacts1.setWhatsapp(contacts.isWhatsapp());
                contacts1.setEmailId(contacts.getEmailId());
                contacts1.setAddress(contacts.getAddress());
                contacts1.setCity(contacts.getCity());
                contacts1.setState(contacts.getState());
                contacts1.setCountry(contacts.getCountry());
                contacts1.setPinCode(contacts.getPinCode());
                contacts1.setShippingAddress(contacts.getShippingAddress());
                contacts1.setShippingCity(contacts.getShippingCity());
                contacts1.setShippingPinCode(contacts.getShippingPinCode());
                contacts1.setShippingState(contacts.getShippingState());
                contacts1.setShippingCountry(contacts.getShippingCountry());
                contacts1.setPanNo(contacts.getPanNo());
                contacts1.setTaxRegNo(contacts.getTaxRegNo());
                contacts1.setAdhaarNo(contacts.getAdhaarNo());
                contacts1.setTaxationType(contacts.getTaxationType());
                contacts1.setGstIN(contacts.getGstIN());
                contacts1.setGstInType(contacts.getGstInType());
                contacts1.setStateCode(contacts.getStateCode());
                contacts1.setTdsApplicable(contacts.isTdsApplicable() ? true : false);
                contacts1.setPricingId(contacts.getPricingId());
                contacts1.setUseAs(contacts.getUseAs());
                contacts1.setOppeningBalance(contacts.getOppeningBalance());
                contacts1.setOppeningType(contacts.getOppeningType());
                contacts1.setStatus(contacts.getStatus());
                contacts1.setDefaultPayment(contacts.getDefaultPayment());
                contacts1.setPaymentTerms(contacts.getPaymentTerms());
                contacts1.setNotification(contacts.getNotification());
                contacts1.setCurrency(contacts.getContactName());
                contacts1.setCreditLimit(contacts.getCreditLimit());
                contacts1.setPartyDiscPer(contacts.getPartyDiscPer());
                contacts1.setCustomerType(contacts.getCustomerType());
                contacts1.setRemarks(contacts.getRemarks());
                contacts1.setPortalAccess(contacts.isPortalAccess());
                contacts1.setLoginEmail(contacts.getLoginEmail());
                contacts1.setLoginPassword(passwordEncoder.encode(contacts.getLoginPassword()));

                //****************************************************
                // Handle profile picture upload
                if (profilePic != null && !profilePic.isEmpty()) {
                    try {
                        String companyDirectoryWithId = "/contacts/" + contactId;
                        File directory = new File(uploadDir + File.separator + "contacts" + File.separator + contactId);

                        if (!directory.exists()) {
                            directory.mkdirs();
                        }

                        String exte = profilePic.getOriginalFilename().substring(profilePic.getOriginalFilename().lastIndexOf("."));

                        String filePath = directory.getAbsolutePath() + File.separator + contactId + exte;

                        profilePic.transferTo(new File(filePath));

                        contacts1.setProfilePicture(companyDirectoryWithId + "/" + contactId + exte);

                    } catch (IOException e) {
                        throw new Exception("Failed to save company logo: " + e.getMessage());
                    }
                }
                contacts1.setImageUrl(imageUrl + contactId);
                contacts1.setCreatedBy(contacts.getCreatedBy());
                contacts1.setCreationDate(LocalDateTime.now());

                Ledger newLedger = null;
                Optional<Ledger> ledgerOptional = ledgerRepo.findById(contacts.getLedgerId());

                if (ledgerOptional.isPresent()) {
                    newLedger = ledgerOptional.get();

                    //   log.info("Ledger: {}", newLedger.getLedgerId());
                    // System.out.println("Ledger id is : " + newLedger.getLedgerId());

                    Optional<Account> optionalAccount = accountRepo.findById(contacts.getAccountId());
                    Account account = null;
                    if (optionalAccount.isPresent()) {
                        account = optionalAccount.get();
                    } else {
                        throw new IdNotFoundException("Account id not found");
                    }
                    newLedger.setLedgerId(contacts.getLedgerId());
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
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ledger data is not updated");
                    }
                    contacts1.setLedgerId(l1.getLedgerId());

                    try {
                        openingBalanceRepo.deleteByLedgerId(contacts.getLedgerId());
                    } catch (Exception e) {
                        throw new IdNotFoundException("Ledger id is invalid");
                    }

                    if (contacts.getOppeningBalance() > 0) {
                        // Creating opening balance
                        OpeningBalance openingBalance = new OpeningBalance();
                        openingBalance.setCompanyId(contacts.getCompanyId());
                        openingBalance.setOpeningBalance(contacts.getOppeningBalance());
                        openingBalance.setLedgerId(contacts.getLedgerId());
                        openingBalance.setFinancialPeriodId(1);
                        openingBalance.setCrDr(contacts.getOppeningType());
                        openingBalance.setOpeningBalanceDate(LocalDate.now());
                        try {
                            openingBalanceRepo.save(openingBalance);
                        } catch (Exception e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Opening balance saving failed reason: " + e.getMessage());
                        }
                    }

                } else {
                    throw new IdNotFoundException("Ledger id not found");
                }
                return ResponseEntity.ok(contactsRepo.save(contacts1));
            } catch (Exception e) {
                //  log.error("Failed to update contact: {}" + e.getMessage(), e); // Use a logger here
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed in contacts reason: " + e.getMessage());
            }
        } else {
            throw new IdNotFoundException("Contact Id not found");
        }
    }

    @Override
    public ResponseEntity<Contacts> getContactsById(int contactId) {

        Optional<Contacts> optionalContacts = contactsRepo.findById(contactId);
        if (optionalContacts.isPresent()) {
            Contacts contacts = optionalContacts.get();
            contacts.setImageUrl(imageUrl + contactId);
            return ResponseEntity.ok(contacts);
        } else {
            throw new IdNotFoundException("Contact id not found");
        }
    }

    @Override
    public ResponseEntity<List<Contacts>> getContactsByCompanyId(int companyId) {
        List<Contacts> contactLists = contactsRepo.findByCompanyId(companyId);
        if (!contactLists.isEmpty()) {
            for (int i = 0; i < contactLists.size(); i++) {
                contactLists.get(i).setImageUrl(imageUrl + contactLists.get(i).getContactId());
            }
            return ResponseEntity.ok(contactLists);
        } else {
            throw new IdNotFoundException("Company id not found");
        }
    }

    @Override
    public ResponseEntity<UrlResource> getContactProfilePic(int contactId) throws MalformedURLException, FileNotFoundException {
        Optional<Contacts> contactImage = contactsRepo.findById(contactId);
        if (contactImage.isPresent()) {
            String filePath = uploadDir + contactImage.get().getProfilePicture(); // Get the path to the profile picture
            File file = new File(filePath);

            // Load the file as a resource
            UrlResource resource = new UrlResource(file.toURI());

            // Check if the resource exists and is readable
            if (resource.exists() && resource.isReadable()) {
                // Set headers for the response
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on the actual file type
                headers.setContentDisposition(ContentDisposition.builder("inline").filename(file.getName()).build()); // Use "inline" for displaying in the browser

                // Return the file as a resource
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                throw new FileNotFoundException("File not found in contacts" + filePath);
            }
        } else {
            throw new PictureNotFoundException("Image not found in contacts");
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
