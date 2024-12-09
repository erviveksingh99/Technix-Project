package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbltransaction")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true) // Foreign key reference
    private int companyId;

    private int transactionId;
    private int transactionNo;
    private int ledgerId;
    private String ledgerName;
    private LocalDateTime transactionDate;
    private String voucherType;
    private int voucherNo;
    private String narration;
    private double debit;
    private double credit;
    private String dBcR;
    private int financialPeriodId;
    private String refNo;
    private int particularsId;
    private String particulars;
    private String confirm;
    private String confirmedBy;
    private LocalDate confirmationDate;
    private int createdBy;
    private LocalDateTime creationDate;
    private String paymentMode;
    private String chequeNo;
    private LocalDate chequeDate;
    private int isBankAccount;
    private int branchId;
}
