package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcompany_bank_details")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    private String bankAccountNature;
    private String accountNumber;
    private String accountName;
    private String bankName;
    private String branchName;
    private String branchAddress;
    private String ifscCode;
    private String signingAuthority;
    private String accountType;
    private String ibanNo;
    private String swiftCode;
    private String upiNo;
    private int createdBy;
    private LocalDateTime createdAt;
}
