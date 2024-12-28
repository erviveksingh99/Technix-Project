package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tblpurchase_product_taxes")
public class PurchaseProductTaxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Integer rowId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id", insertable = false, updatable = false)
    private Purchase purchase;

    @Column(name = "purchase_id", insertable = true, updatable = true)
    private int purchaseId;

    private LocalDate purchaseDate;

    private Double taxableValue;

    private Integer taxId;

    private String taxType;

    private String taxName;

    private Double taxPer;

    private Double taxAmount;
}
