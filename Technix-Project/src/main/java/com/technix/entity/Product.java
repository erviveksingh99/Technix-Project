package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.Views;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblproduct")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "product_id")
    private int productId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @JsonView(Views.ParentView.class)
    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @JsonView(Views.ParentView.class)
    private String productType;

    @JsonView(Views.ParentView.class)
    private String productName;

    @JsonView(Views.ParentView.class)
    private String shortName;

    @JsonView(Views.ParentView.class)
    private String description;

    @JsonView(Views.ParentView.class)
    private String image;

    @JsonView(Views.ParentView.class)
    @Transient
    private String imageUrl;

    @JsonView(Views.ParentView.class)
    private String materialType;

    // Mapping to Category entity
    @JsonView(Views.ParentView.class)
    @Schema(hidden = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category category;


    @JsonView(Views.ParentView.class)
    @Column(name = "categoryId", insertable = true, updatable = true)
    private int categoryId;

    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", insertable = false, updatable = false)
    private Department department;

    // Add @JsonView for fields you want to expose
    @JsonView(Views.ParentView.class)
    @Column(name = "department_id", insertable = true, updatable = true)
    private int departmentId;

    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", insertable = false, updatable = false)
    private Brand brand;

    @JsonView(Views.ParentView.class)
    @Column(name = "brand_id", insertable = true, updatable = true)
    private int brandId;

    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id", insertable = false, updatable = false)
    private Manufacturer manufacturer;

    @JsonView(Views.ParentView.class)
    @Column(name = "manufacturer_id", insertable = true, updatable = true)
    private int manufacturerId;

    @JsonView(Views.ParentView.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @JsonView(Views.ParentView.class)
    @Column(name = "supplier_id", insertable = true, updatable = true)
    private int supplierId;

    @JsonView(Views.ParentView.class)
    private String unit;

    @JsonView(Views.ParentView.class)
    private String uqcCode;

    @JsonView(Views.ParentView.class)
    private boolean altUnitRequired;

    @JsonView(Views.ParentView.class)
    private String secondaryUnitUQC;

    @JsonView(Views.ParentView.class)
    private String defaultSalesUnit;

    @JsonView(Views.ParentView.class)
    private double conversionFactor;

    @JsonView(Views.ParentView.class)
    private boolean manageItemBy;

    @JsonView(Views.ParentView.class)
    private boolean managementMethod;

    @JsonView(Views.ParentView.class)
    private int warrantyPeriod;

    @JsonView(Views.ParentView.class)
    private String barcode;

    @JsonView(Views.ParentView.class)
    private String sku;

    @JsonView(Views.ParentView.class)
    private String modelNo;

    @JsonView(Views.ParentView.class)
    private String color;

    @JsonView(Views.ParentView.class)
    private String size;

    @JsonView(Views.ParentView.class)
    private boolean idTaxable;

    @JsonView(Views.ParentView.class)
    private String taxId;

    @JsonView(Views.ParentView.class)
    private String taxationType;

    @JsonView(Views.ParentView.class)
    private double taxPer;

    @JsonView(Views.ParentView.class)
    private String HsnCode;

    @JsonView(Views.ParentView.class)
    private int recorderPoint;

    @JsonView(Views.ParentView.class)
    private double mrp;

    @JsonView(Views.ParentView.class)
    private double discountPer;

    @JsonView(Views.ParentView.class)
    private double purchaseRate;

    @JsonView(Views.ParentView.class)
    private double distributorPrice;

    @JsonView(Views.ParentView.class)
    private double dealerPrice;

    @JsonView(Views.ParentView.class)
    private double wholesalePrice;

    @JsonView(Views.ParentView.class)
    private double sellingRate;

    @JsonView(Views.ParentView.class)
    private double openingStock;

    @JsonView(Views.ParentView.class)
    private double unitRate;

    @JsonView(Views.ParentView.class)
    private double openingValue;

    @JsonView(Views.ParentView.class)
    private String bin;

    @JsonView(Views.ParentView.class)
    private String status;

    @JsonView(Views.ParentView.class)
    private String createdBy;

    @JsonView(Views.ParentView.class)
    private LocalDateTime creationDate;

}

