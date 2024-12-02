package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tblaccount")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = true, insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", nullable = true, insertable = true, updatable = true)
    private Integer companyId;

    private String accounts;
    private String accountNature;
    private int orderByNumber;
    private boolean isActive;
    private boolean isBankAccount;
    private boolean systemDefault;
    private int createdBy;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_sub_id", referencedColumnName = "account_id", nullable = true, insertable = false, updatable = false)
    private Account parentAccount;

    @Column(name = "account_sub_id", nullable = true, insertable = true, updatable = true)
    private Integer accountSubId;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> childAccounts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ledger> ledger = new ArrayList<>();

    private LocalDateTime createdAt;
}
