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

    private String full_name;
    private String email;
    private String phone_number;
    private String company_name;
    private String bussiness_type;
    private String company_size;
    private String country;
    private LocalDateTime created_at;

    // One Customer can have many Users
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users; // List of users associated with the customer


    // Optional: One customer can have many companies
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies;  // List of companies associated with this customer
}

