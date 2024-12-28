package com.technix.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PurchaseParticularsDTO {

    private int productId;

    private String hsnCode;

    private Double quantity;

    private String unit;

    private Double billedQty;

    private Double freeQty;

    private String alternateUnit;

    private Double convFactor;

    private Double rate;

    private Double discPer;

    private Double discount;

    private Double amount;

    private Integer taxId;

    private String taxType;

    private String taxationType;

    private Double taxPer;

    private Double taxableValue;

    private int purchaseId;

    private LocalDate purchaseDate;

    private Integer branchId;

    private int companyId;
}
