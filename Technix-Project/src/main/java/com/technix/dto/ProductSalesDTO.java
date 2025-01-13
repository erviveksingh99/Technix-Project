package com.technix.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSalesDTO {
    private int productId;
    private String productName;
    private String brandName;
    private String categoryName;
    private double totalQuantity;  // Changed to double

    public ProductSalesDTO(int productId,
                           String productName,
                           String brandName,
                           String categoryName,
                           double totalQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.totalQuantity = totalQuantity;
    }
}

