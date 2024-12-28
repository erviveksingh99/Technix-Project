package com.technix.dto;

import lombok.Data;

@Data
public class PurchaseExpensesDTO {

    private int purchaseId;

    private Integer ledgerId;

    private Integer chargesId;

    private Integer taxId;

    private String valueOfField;

    private Double percent;

    private Double amount;
}
