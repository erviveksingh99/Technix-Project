package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Tblusers_Logs")
public class UserLogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_id;

    // Foreign key relationship with the User entity
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column for user_id in the logs table
    private User user;

    private String browser;
    private String  platform;
    private String  ip_address;
    private String  city;
    private String  region;
    private String  country;
    private String  postal;
    private String  location;
    private String isp;
    private LocalDateTime login_at;
}
