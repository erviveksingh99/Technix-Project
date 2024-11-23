package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.Views;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblmanufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    // Many brands can be associated with one company
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading to improve performance
    @JsonView(Views.ParentView.class)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true)
    @JsonView(Views.ParentView.class)
    private  int companyId;

    @JsonView(Views.ParentView.class)
    private String manufacturer;

    @JsonView(Views.ParentView.class)
    private String description;

    @JsonView(Views.ParentView.class)
    private int createdBy;

    @JsonView(Views.ParentView.class)
    private LocalDateTime createdAt;
}
