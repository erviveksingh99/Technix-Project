package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcustomer_subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;

    @Column(name = "plan_id", insertable = true, updatable = true)
    private int planId;   //FK

    private int planValidity;
    private LocalDate planStart;
    private LocalDate planEnd;

    @Column(columnDefinition = "tinyint default 0")
    private boolean status;

    @Column(columnDefinition = "tinyint default 0")
    private boolean isTrial;

    private int userCount;
    private int invoiceCount;
    private int companyCount;
    private int branchCount;
    private int emailsCount;
    private int smsCount;

    private int createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = true, updatable = true, nullable = true)
    private int customerId;  //FK

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id", insertable = false, updatable = false)
    private Plan plan;

    public void startSubscriptionDate(int validity) {
        this.planStart = LocalDate.now();
        this.planEnd = planStart.plusDays(validity);
    }
}

