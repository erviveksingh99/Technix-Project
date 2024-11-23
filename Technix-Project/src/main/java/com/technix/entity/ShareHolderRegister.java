package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcompany_shareholder")
public class ShareHolderRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    private String shareholderName;
    private String designation;
    private String numberOfShares;
    private double shareValue;
    private LocalDateTime dateOfAllotment;
    private String address;
    private String city;
    private String pin;
    private String state;
    private String country;
    private String pan;
    private String aadhar;
    private String mobileNumber;
    private String email;
    private String nominee;
    private int createdBy;
    private LocalDateTime createdAt;
}
