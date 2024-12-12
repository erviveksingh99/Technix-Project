package com.technix.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BillTaxationDetailsDTO {
    private int billId;
    private LocalDate billingDate;
    private double taxableValue;
    private String taxType;
    private String taxName;
    private double taxPer;
    private double taxAmount;
}
