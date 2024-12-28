package com.technix.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseDTO {

    private LocalDate purchaseDate;

    private String purchaseNo;

    private String referenceNo;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private String poNo;

    private LocalDate poDate;

    private String taxationType;

    private int contactId;

    private double subTotal;

    private double discPer;

    private double discount;

    private double otherCharges;

    private double globalDisc;

    private double globalDiscPer;

    private double totalTaxes;

    private double roundOff;

    private double grandTotal;

    private String notes;

    private int branchId;

    private String status;

    private int companyId;

    private int transactionId;

    private int createdBy;

    private List<PurchaseParticularsDTO> purchaseParticulars;

    private List<PurchaseProductTaxesDTO> purchaseProductTaxesDTO;

    private List<PurchaseExpensesDTO> purchaseExpensesDTO;
}
