package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.Views;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblbrands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "brand_id")
    private int brandId;

    // Many brands can be associated with one company
    @JsonIgnore
    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading to improve performance
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    // Foreign key reference
    private Company company;  // Reference to Company entity

    @Column(name = "company_id", insertable = true, updatable = true)
    @JsonView(Views.ParentView.class)
    private int companyId;

    @Column(name = "brand_name")
    @JsonView(Views.ParentView.class)
    private String brandName;

    @Column(name = "description")
    @JsonView(Views.ParentView.class)
    private String description;

    @Column(name = "created_by")
    @JsonView(Views.ParentView.class)
    private String createdBy;

    @Column(name = "created_at")
    @JsonView(Views.ParentView.class)
    private LocalDateTime createdAt;
}
