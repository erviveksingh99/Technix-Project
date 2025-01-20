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
    private Integer id;

    @Column(length = 25)
    private String orderId;

    private LocalDate orderDate;
    private Integer planId;
    private Double planRate;
    private Integer planValidity;
    private Double planValue;
    private Integer userCount;
    private Double userRate;
    private Double userValue;
    private Integer invoiceCount;
    private Double invoiceRate;
    private Double invoiceValue;
    private Integer companyCount;
    private Double companyRate;
    private Double companyValue;
    private Integer branchCount;
    private Double branchRate;
    private Double branchValue;
    private Integer emailCount;
    private Double emailValue;
    private Integer smsCount;
    private Double smsRate;
    private Double smsValue;
    private Double subTotal;
    private Double discount;
    private Double totalAmount;
    private Double taxableValue;
    private Double taxAmount;
    private String orderCurrency;

    @Column(name = "status", columnDefinition = "tinyint default 1")
    private String status;
    private String transactionStatus;
    private String referenceNo;
    private String paymentMode;
    private String transactionMessage;
    private Integer createdBy;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = true, updatable = true, nullable = true)
    private Integer customerId;  //FK

    @CreationTimestamp
    private LocalDateTime createdAt;
}
