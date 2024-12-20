package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table( name = "tblfinancialperiodtransaction")
public class TransactionMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private  int transactionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true) // Foreign key reference
    private int companyId;

    private int transactionNo;
    private LocalDate transactionDate;
    private int financialPeriodId;
    private String voucherType;
    private int voucherNo;
}
