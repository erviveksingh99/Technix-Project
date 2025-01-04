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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = true, updatable = true, nullable = true)
    private int customerId;  //FK

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id", insertable = false, updatable = false)
    private Plan plan;

    @Column(name = "plan_id", insertable = true, updatable = true)
    private int planId;   //FK

    private int planValidity;
    private LocalDate planStart;
    private LocalDate planEnd;
    private boolean status;
    private boolean isTrail;
    private int createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void startSubscriptionDate(int planValidity) {
        this.planStart = LocalDate.now();
        this.planEnd = planStart.plusDays(planValidity);
        this.isTrail = true;
        this.status = true;
    }
}

