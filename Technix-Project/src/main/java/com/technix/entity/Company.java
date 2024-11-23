package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcompanies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;

    // Many companies can be associated with one customer
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading to improve performance
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", nullable = false, insertable = false, updatable = false)
    // Refer to the same "customerId" column
    private Customer customer;  // Reference to Customer entity

    @Column(name = "customer_id", insertable = true, updatable = true)
    private int customerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "bussines_type")
    private String bussinesType;

    @Column(name = "cin_number")
    private String cinNumber;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "website")
    private String website;

    @Column(name = "logo")
    private String logo;

    @Transient
    private String imageUrl;

    @Column(name = "logo_position")
    private String logoPosition;

    @Column(name = "logo_on_invoice")
    private boolean logoOnInvoice;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Optional: Uncomment if needed
    // @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Brand> brands;  // List of brands associated with this company

    // Optional: Uncomment if needed
    // @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Department> departments;  // List of departments associated with this company

}
