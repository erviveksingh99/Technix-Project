package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user", hidden = true)
    @Column(name = "user_id")
    private int userId; // primary key

    // Reference to Customer, the foreign key relationship
    // Foreign key column in the User table
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    @Schema(hidden = true) // Hide this field in the Swagger UI
    private Customer customer; // Many users can belong to one customer

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "organisation_name")
    private String organisationName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profiler_picture")
    private String profilePicture;

    @Transient
    private String imageUrl;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "role_id")
    private int roleId;

    private int google;

    @JsonIgnore
    private String password;

    private int status;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // A user can have multiple logs
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserLogRecord> logs;
}

