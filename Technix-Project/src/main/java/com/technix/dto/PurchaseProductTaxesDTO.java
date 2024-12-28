package com.technix.dto;

import lombok.Data;

import java.util.Date;
@Data
public class PurchaseProductTaxesDTO {

    private int purchaseId;

    private Date purchaseDate;

    private Double taxableValue;

    private Integer taxId;

    private String taxType;

    private String taxName;

    private Double taxPer;

    private Double taxAmount;
}
