package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblledger")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ledgerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true) // Foreign key reference
    private int companyId;

    private String ledgerName;
    private int account_id;
    private String account;
    private String accountNature;
    private int orderByNumber;
    private int TdsApplicable;
    private boolean isActive;
    private boolean systemDefault;
    private int createdBy;

    /*@JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = true, insertable = false, updatable = false)
    private Account accounts;*/

   // @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = true, insertable = false, updatable = false)
    private Account accounts;

    private LocalDateTime createdAt;
}
