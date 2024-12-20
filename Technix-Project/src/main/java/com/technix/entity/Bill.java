package com.technix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblbill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id", unique = true)
    private int billId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", insertable = true, updatable = true)
    private int companyId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", insertable = false, updatable = false)
    private Contacts contacts;

    @Column(name = "contact_id", insertable = true, updatable = true)
    private int contactId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", insertable = false,updatable = false)
    private TransactionMain transactionMain;

    @Column(name = "transaction_id", insertable = true, updatable = true)
    private int transactionId;

    private LocalDate billDate;
    private String invoiceNo;
    private String referenceNo;
    private LocalDate dueDate;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerContactNo;

    private String placeOfSupply;
    private double subTotal;
    private double discPer;
    private double discount;
    private double otherCharges;
    private double totalTaxes;
    private double roundOff;
    private double grandTotal;
    private String notes;
    private int branchId;
    private int salesManId;
    private String salesMan;
    private String status;
    private int createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

/*

 //Response:

 {
    "data": {
        "billId": 343,
        "billingDate": "11/12/2024",
        "invoiceNo": "TAX/24-25/9",
        "referenceNo": "236523",
        "dueDate": "2024-12-11",
        "contactId": 862,
        "customerName": "M/S ZABOLO",
        "customerAddress": "Near Thana Golamber, Nagina Colony, Phulwari Sharif, Patna - 801505",
        "customerEmail": "zabolo.com@gmail.com",
        "customerContactNo": "7250433565",
        "placeOfSupply": "10",
        "subTotal": 2997.63,
        "discount": 48.94,
        "otherCharges": 0.0,
        "totalTaxes": 530.76,
        "roundOff": -0.45,
        "grandTotal": 3479.0,
        "notes": "Invoice",
        "branchId": 0,
        "salesmanId": 0,
        "salesman": "",
        "status": "Unpaid",
        "createdBy": 72,
        "place": "Patna",
        "createdAt": "2024-12-11",
        "companyId": 253,
        "contacts": null,
        "billParticulars": [],
        "billTaxationDetails": [],
        "outStandingAmount": null
    },
    "message": "Invoice created successfully",
    "status": true
}


 Payload:


 companyId: 253
billDate: 2024-12-11
invoiceNo:
referenceNo: 236523
duDate: 2024-12-11
contactId: 862
customerName: M/S ZABOLO
place: Patna
customerAddress: Near Thana Golamber, Nagina Colony, Phulwari Sharif, Patna - 801505
customerEmail: zabolo.com@gmail.com
customerContactNo: 7250433565
placeOfSupply: 10
subTotal: 2997.63
discount: 48.94
otherCharges: 0
totalTaxes: 530.76
roundOff: -0.45
grandTotal: 3479
notes: Invoice
branchId: 0
salesmanId: 0
salesman:
status: Unpaid
createdBy: 72
billId: 0
billDetails: [{"productId":218,"hsnCode":"","quantity":16,"unit":"Pcs","billedQty":16,"freeQty":0,"alternateUnit":"box","convFactor":1,"rate":65,"discPer":0,"discount":1.1,"amount":1227.2,"taxType":"","taxationType":"Exclusive","taxPer":18,"taxableValue":1040,"branchId":1,"taxId":114},{"productId":219,"hsnCode":"","quantity":14,"unit":"Pcs","billedQty":14,"freeQty":0,"alternateUnit":"box","convFactor":1,"rate":165,"discPer":2.5,"discount":1.1,"amount":2252.25,"taxType":"","taxationType":"Inclusive","taxPer":18,"taxableValue":1908.69,"branchId":1,"taxId":114}]
taxDetails: [{"taxType":"CGST","taxPer":9,"taxAmount":265.38,"taxableValue":2948.69,"taxName":"CGST 9%"},{"taxType":"SGST","taxPer":9,"taxAmount":265.38,"taxableValue":2948.69,"taxName":"SGST 9%"}]





*/


