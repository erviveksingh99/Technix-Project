package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tblbill_product_taxes")
public class BillTaxationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id", insertable = false, updatable = false)
    private Bill bill;

    @Column(name = "bill_id", insertable = true, updatable = true)
    private int billId;

    private LocalDate billingDate;
    private double taxableValue;
    private String taxType;
    private String taxName;
    private double taxPer;
    private double taxAmount;
}
