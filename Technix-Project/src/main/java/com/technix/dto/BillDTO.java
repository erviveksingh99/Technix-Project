package com.technix.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BillDTO {
    private int companyId;
    private int contactId;
    private LocalDate billDate;
    private String invoiceNo;
    private String referenceNo;
    private LocalDate dueDate;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerContactNo;
    private int transactionId;
    private String placeOfSupply;
    private double subTotal;
    private double discPer;
    private double discount;
    private double otherCharges;
    private double totalTaxes;
    private double roundOff;
    private double grandTotal;
    private int branchId;
    private String notes;
    private int salesManId;
    private String salesMan;
    private String status;
    private int createdBy;

    private List<BillParticularsDTO> billParticularsDTO;
    private List<BillTaxationDetailsDTO> billTaxationDetailsDTO;
}
