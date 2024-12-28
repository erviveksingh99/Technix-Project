package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tblbill_charges")
public class BillAdditionalCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Integer rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id", insertable = false, updatable = false)
    private Bill bill;

    @Column(name = "bill_id", insertable = true, updatable = true)
    private int billId;

    private Integer ledgerId;

    private Integer chargesId;

    private Integer taxId;

    private String valueOfField;

    private Double percent;

    private Double amount;
}
