package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.Views;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tblcategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ParentView.class)
    @Column(name = "categoryId")
    private int categoryId;

    // Expose company details using JsonView instead of @JsonIgnore
    @JsonIgnore
    @JsonView(Views.ChildView.class)  // Adjust the view as per your requirement
    @ManyToOne(fetch = FetchType.EAGER)  // Change to EAGER if you need company details in the response
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @JsonView(Views.ParentView.class)
    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @JsonView(Views.ParentView.class)
    private String categoryName;

    @JsonView(Views.ParentView.class)
    private String description;

    @JsonView(Views.ParentView.class)
    private String image;

    // Add image URL calculation if needed in response
    @Transient
    @JsonView(Views.ParentView.class)
    private String imageUrl;

    // Self-referencing parent category (Parent -> Child)
    @JsonView(Views.ParentView.class)
    @JsonBackReference  // Prevents infinite recursion for parent
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category parentCategory;

    @JsonView(Views.ParentView.class)
    @Column(name = "parent_id", insertable = true, updatable = true, nullable = true)
    private Integer parentCategoryId;

    // Child categories (Child -> Parent)
    @JsonView(Views.ChildView.class)
    @JsonManagedReference  // Ensures child categories are serialized
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> childCategories = new ArrayList<>();

    @JsonView(Views.ParentView.class)
    private int createdBy;

    @JsonView(Views.ParentView.class)
    private LocalDateTime createdAt;

    public String getImageUrl() {
        return "http://localhost:9090/category/image/" + this.categoryId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
