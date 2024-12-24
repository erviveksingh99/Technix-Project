package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tblopeningbalance")
public class OpeningBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true) // Foreign key reference
    private int companyId;

    private int ledgerId;
    private double openingBalance;
    private String crDr;
    private int financialPeriodId;
    private LocalDate openingBalanceDate;
}


/*

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXZla0BnbWFpbC5jb20iLCJpYXQiOjE3MzUwMTUxOTQsImV4cCI6MTczNTA1MTE5NH0.bMpaXzNdHVOKBUYkvhGcxaXVJnqSetnMEFSgyZ9PM2g


 */