package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tblbill_particulars")
public class BillParticulars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id", insertable = false, updatable = false)
    private Bill bill;

    @Column(name = "bill_id", insertable = true, updatable = true)
    private int billId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_id", insertable = true, updatable = true)
    private int productId;

    private String hsnCode;
    private  double quantity;
    private String unit;
    private double billedQty;
    private double freeQty ;
    private String alternateUnit;
    private double convFactor;
    private double rate;
    private double discPer;
    private double discount;
    private double amount;
    private String taxType;
    private String taxationType;
    private String taxPer;
    private double taxableValue;
    private LocalDate billingDate;
    private  int branchId;
}
