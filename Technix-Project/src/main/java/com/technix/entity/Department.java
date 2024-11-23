package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.Views;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/*
@Data
@Entity
@Table(name = "tbldepartments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "department_id")
    private int departmentId;

    // Many brands can be associated with one company
    @JsonIgnore
    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading to improve performance
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @JsonView(Views.ParentView.class)
    @Column(name = "company_id", insertable = true, updatable = true)
    private  int companyId;

    @JsonView(Views.ParentView.class)
    @Column(name = "department")
    private String department;

    @JsonView(Views.ParentView.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.ParentView.class)
    @Column(name = "created_by")
    private int createdBy;

    @JsonView(Views.ParentView.class)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
*/


@Data
@Entity
@Table(name = "tbldepartments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "department_id")
    private int departmentId;

    // Many departments can be associated with one company
    @JsonIgnore
    @JsonView(Views.ParentView.class)  // Add view to include it in JSON response
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)  // Foreign key reference
    private Company company;  // Reference to Company entity

    @JsonView(Views.ParentView.class)
    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @JsonView(Views.ParentView.class)
    @Column(name = "department")
    private String department;

    @JsonView(Views.ParentView.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.ParentView.class)
    @Column(name = "created_by")
    private int createdBy;

    @JsonView(Views.ParentView.class)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

