package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tblcustomer")
public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId; // primary key

    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String businessType;
    private String companySize;
    private String country;
    private LocalDateTime createdAt;

    // One Customer can have many Users
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users; // List of users associated with the customer


    // Optional: One customer can have many companies
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies;  // List of companies associated with this customer
}

