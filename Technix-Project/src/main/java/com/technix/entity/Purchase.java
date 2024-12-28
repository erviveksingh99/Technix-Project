package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblpurchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId;

    private LocalDate purchaseDate;

    private String purchaseNo;

    private String referenceNo;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private String poNo;

    private LocalDate poDate;

    private String taxationType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", insertable = false, updatable = false)
    private Contacts contacts;

    @Column(name = "contact_id", insertable = true, updatable = true)
    private int contactId;

    private double subTotal;

    private double discPer;

    private double discount;

    private double otherCharges;

    private double globalDisc;

    private double globalDiscPer;

    private double totalTaxes;

    private double roundOff;

    private double grandTotal;

    private String notes;

    private int branchId;

    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    private int transactionId;

    private int createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
