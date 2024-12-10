package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technix.entity.enums.ContactsType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcontacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)

    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @Enumerated(EnumType.STRING)
    private ContactsType contactsType;

    private String contactCode;
    private String contactName;
    private String businessType;
    private String businessName;
    private String workPhone;
    private String workEmail;
    private String fax;
    private String website;
    private String title;
    private String contactPerson;
    private String designation;
    private String mobileNumber;
    private boolean isWhatsapp;
    private String emailId;
    private String address;
    private String city;
    private String pinCode;
    private String state;
    private String country;
    private String shippingAddress;
    private String shippingCity;
    private String shippingPinCode;
    private String shippingState;
    private String shippingCountry;
    private String PANNo;
    private String TexRegNo;
    private String AdhaarNo;
    private String taxationType;
    private String GSTIN;
    private String GSTINType;
    private String stateCode;
    private boolean TdsApplicable;
    private int pricingId;
    private int ledgerId;
    private int accountId;
    private String useAs;
    private double oppeningBalance;
    private String oppeningType;
    private String status;
    private String defaultPayment;
    private String paymentTerms;
    private String notification;
    private String currency;
    private double creditLimit;
    private double partyDiscPer;
    private String customerType;
    private String remarks;
    private boolean portalAccess;
    private  int createdBy;
    private String loginEmail;
    private String loginPassword;
    private String profilePicture;

    @Transient
    private String imageUrl;

    private LocalDateTime creationDate;
}
