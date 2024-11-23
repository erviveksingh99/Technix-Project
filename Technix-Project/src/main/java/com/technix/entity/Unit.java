package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Data
@Entity
@Table(name = "tblunit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    private String unit;
    private String uqc;
    private String uqcCode;
}
