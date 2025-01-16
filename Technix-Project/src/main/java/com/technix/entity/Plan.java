package com.technix.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tblplan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private int planId;

    private String planName;
    private String planType;  // Monthly/Yearly

    @Lob
    private String features;

    private String description;
    private Double price;
    private String currency;
    private int validity;
    private int userCount;
    private boolean isActive;
    private boolean trialAvailable;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String planSupport;

    private int invoiceCount;  // new column added here
    private int companyCount;
    private int branchCount;
    private int emailsCount;
    private int smsCount;
    private double userRate;
    private double invoiceRate;
    private double companyRate;
    private double branchRate;
    private double emailsRate;
    private double smsRate;
}
