package com.technix.repository;

import com.technix.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING_INDEX(purchase_no, '/', -1) AS UNSIGNED)), 0) FROM tblpurchase WHERE company_id = :companyId", nativeQuery = true)
    int findMaxPurchaseNo(@Param("companyId") int companyId);

    Purchase findByTransactionId(int transactionId);

    @Query(value = "SELECT c.contact_name AS contactName, c.city AS city, p.purchase_no AS purchaseNo, p.invoice_date AS invoiceDate, p.grand_total AS billAmount\n" +
            "FROM tblcontacts c LEFT JOIN tblpurchase p ON c.contact_id = p.contact_id \n" +
            "WHERE p.taxation_type = :taxationType AND p.purchase_date BETWEEN :startDate AND :endDate ", nativeQuery = true)
    List<Object[]> findPurchaseDetailsByTaxationTypeAndDateRange(
            @Param("taxationType") String taxationType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


    @Query(value = "SELECT \n" +
            "    p.purchase_date AS Date,\n" +
            "    p.reference_no AS SupInvoiceNo,\n" +
            "    p.invoice_date AS SupInvoiceDate,\n" +
            "    c.contact_name AS SupplierName,\n" +
            "    prod.product_name AS ProductName,\n" +
            "    pp.quantity AS Qty,\n" +
            "    pp.unit AS Unit,\n" +
            "    pp.rate AS Rate,\n" +
            "    pp.taxable_value AS TxblAmt,\n" +
            "    ppt.tax_per AS TaxPercent,\n" +
            "    ppt.tax_amount AS TaxAmt,\n" +
            "    p.grand_total AS NetAmt\n" +
            "FROM \n" +
            "    tblpurchase p\n" +
            "JOIN \n" +
            "    tblcontacts c ON p.contact_id = c.contact_id\n" +
            "JOIN \n" +
            "    tblpurchase_particulars pp ON p.purchase_id = pp.purchase_id\n" +
            "JOIN \n" +
            "    tblproduct prod ON pp.product_id = prod.product_id\n" +
            "JOIN \n" +
            "    tblpurchase_product_taxes ppt ON p.purchase_id = ppt.row_id\n" +
            "WHERE \n" +
            "    c.contact_name = :partyName  \n" +
            "    AND prod.product_name = :productName  \n" +
            "    AND p.purchase_date BETWEEN :startDate AND :endDate ",
            nativeQuery = true)
    List<Object[]> findPurchaseDetailsByPartyNameProductNameAndDateRange(@Param("partyName") String partyName,
                                                                         @Param("productName") String productName,
                                                                         @Param("startDate") LocalDate startDate,
                                                                         @Param("endDate") LocalDate endDate);


    @Query(value = "SELECT " +
            "p.purchase_date AS Date, " +
            "p.reference_no AS SupInvoiceNo, " +
            "p.invoice_date AS SupInvoiceDate, " +
            "c.contact_name AS SupplierName, " +
            "prod.product_name AS ProductName, " +
            "pp.quantity AS Qty, " +
            "pp.unit AS Unit, " +
            "pp.rate AS Rate, " +
            "pp.taxable_value AS TxblAmt, " +
            "ppt.tax_per AS TaxPercent, " +
            "ppt.tax_amount AS TaxAmt, " +
            "p.grand_total AS NetAmt " +
            "FROM tblpurchase p " +
            "JOIN tblcontacts c ON p.contact_id = c.contact_id " +
            "JOIN tblpurchase_particulars pp ON p.purchase_id = pp.purchase_id " +
            "JOIN tblproduct prod ON pp.product_id = prod.product_id " +
            "JOIN tblpurchase_product_taxes ppt ON p.purchase_id = ppt.row_id " +
            "WHERE p.purchase_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> findAllPurchaseDetailsByPartyProductAndDateRange(@Param("startDate") LocalDate startDate,
                                                                    @Param("endDate") LocalDate endDate);


    @Query(value = "SELECT p.* FROM tblpurchase p " +
            "JOIN tblpurchase_particulars pp ON p.purchase_id = pp.purchase_id " +
            "JOIN tblproduct prod ON pp.product_id = prod.product_id " +
            "WHERE pp.product_id = :productId", nativeQuery = true)
    List<Purchase> findByProductId(@Param("productId") int productId);


    @Query(value = "SELECT p.* FROM tblpurchase p " +
            "JOIN tblpurchase_particulars pp ON p.purchase_id = pp.purchase_id " +
            "JOIN tblproduct prod ON pp.product_id = prod.product_id " +
            "WHERE prod.category_id = :categoryId", nativeQuery = true)
    List<Purchase> findByGroupWise(@Param("categoryId") int categoryId);

}
