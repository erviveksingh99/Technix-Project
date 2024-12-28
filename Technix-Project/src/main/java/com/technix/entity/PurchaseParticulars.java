package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tblpurchase_particulars")
public class PurchaseParticulars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Integer rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_id", insertable = true, updatable = true)
    private int productId;

    private String hsnCode;

    private Double quantity;

    private String unit;

    private Double billedQty;

    private Double freeQty;

    private String alternateUnit;

    private Double convFactor;

    private Double rate;

    private Double discPer;

    private Double discount;

    private Double amount;

    private Integer taxId;

    private String taxType;

    private String taxationType;

    private Double taxPer;

    private Double taxableValue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id", insertable = false, updatable = false)
    private Purchase purchase;

    @Column(name = "purchase_id", insertable = true, updatable = true)
    private int purchaseId;

    // @Temporal(TemporalType.DATE)
    private LocalDate purchaseDate;

    private Integer branchId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;
}
