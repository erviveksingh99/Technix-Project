package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblcustomer_order")
public class CustomerOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = true, updatable = true, nullable = true)
    private int customerId;  //FK

    private String orderId;
    private LocalDate orderDate;
    private int planId;
    private double planRate;
    private int planValidity;
    private double planValue;
    private int userCount;
    private double userRate;
    private double userValue;
    private int invoiceCount;
    private double invoiceRate;
    private double invoiceValue;
    private int companyCount;
    private double companyRate;
    private double companyValue;
    private int branchCount;
    private double branchRate;
    private double branchValue;
    private int emailCount;
    private double emailValue;
    private  int smsCount;
    private double smsRate;
    private double smsValue;
    private double subTotal;
    private  double discount;
    private  double totalAmount;
    private double taxableValue;
    private double taxAmount;
    private String orderCurrency;

//    @Column(name = "status", columnDefinition = "tinyint default 1")
//    private boolean status;

    private String status;
    private String transactionStatus;
    private String referenceNo;
    private String paymentMode;
    private String transactionMessage;
    private int createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
