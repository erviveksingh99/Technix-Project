package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tblpurchase_expenses")
public class PurchaseExpenses {

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

    private Integer ledgerId;

    private Integer chargesId;

    private Integer taxId;

    private String valueOfField;

    private Double percent;

    private Double amount;
}
