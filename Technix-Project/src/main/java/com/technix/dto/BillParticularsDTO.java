package com.technix.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BillParticularsDTO {

    private int companyId;
    private int billId;
    private int productId;

    private String hsnCode;
    private  double quantity;
    private double freeQty ;
    private String unit;
    private double billedQty;
    private String alternateUnit;
    private double convFactor;
    private double rate;
    private double discPer;
    private double discount;
    private double amount;
    private String taxType;
    private String taxationType;
    private String taxPer;
    private double taxableValue;
    private LocalDate billingDate;
    private  int branchId;
}
