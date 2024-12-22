package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblpayment")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", insertable = false,updatable = false)
    private TransactionMain transactionMain;

    @Column(name = "transaction_id", insertable = true, updatable = true)
    private int transactionId;

    private String receiptNo;
    private String voucherType;
    private LocalDate transactionDate;
    private int contactId;
    private String contactName;
    private int ledgerId;
    private double totalPayment;
    private String paymentMethod;
    private String chequeNo;
    private String chequeDate;
    private String referenceNo;  //
    private String remarks;
    private int bankLedgerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    private int branchId;
    private int financialPeriodId;
    private String invoiceNo;
    private String refNo;  // billId
    private String dueDate;
    private String invoiceDate;
    private double billAmount;
    private double amountDue;
    private double payment;
    private int createdBy;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
