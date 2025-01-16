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

    /*@Query(value = "SELECT c.contact_name AS contactName, " +
            "c.city AS city, " +
            "p.purchase_no AS purchaseNo, " +
            "p.invoice_date AS invoiceDate, " +
            "p.grand_total AS billAmount " +
            "FROM tblcontacts c " +
            "LEFT JOIN tblpurchase p ON c.contact_id = p.contact_id " +
            "WHERE p.taxation_type = :taxationType " ,nativeQuery = true)
    List<Object[]> findPurchaseDetailsByTaxationTypeAndDateRange(@Param("taxationType") String taxationType);
*/
    /*@Query(value = "SELECT c.contact_name AS contactName, " +
            "c.city AS city, " +
            "p.purchase_no AS purchaseNo, " +
            "p.invoice_date AS invoiceDate, " +
            "p.grand_total AS billAmount " +
            "FROM tblcontacts c " +
            "LEFT JOIN tblpurchase p ON c.contact_id = p.contact_id " +
            "WHERE p.taxation_type = :taxationType " +
            "AND p.purchase_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> findPurchaseDetailsByTaxationTypeAndDateRange(
            @Param("taxationType") String taxationType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );*/

    @Query(value = "SELECT c.contact_name AS contactName, c.city AS city, p.purchase_no AS purchaseNo, p.invoice_date AS invoiceDate, p.grand_total AS billAmount\n" +
            "FROM tblcontacts c LEFT JOIN tblpurchase p ON c.contact_id = p.contact_id \n" +
            "WHERE p.taxation_type = :taxationType AND p.purchase_date BETWEEN :startDate AND :endDate ", nativeQuery = true)
    List<Object[]> findPurchaseDetailsByTaxationTypeAndDateRange(
            @Param("taxationType") String taxationType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
