package com.technix.repository;

import com.technix.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByCompanyId(int companyId);

    Bill findByBranchId(int branchId);

    Bill findByInvoiceNo(String invoiceNo);

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING_INDEX(invoice_no, '/', -1) AS UNSIGNED)), 0) FROM tblbill WHERE company_id = :companyId", nativeQuery = true)
    int findMaxInvoiceNo(@Param("companyId") int companyId);


    Optional<Bill> findById(Integer integer);

    @Query(value = "SELECT * FROM tblbill WHERE contact_id = :contactId AND bill_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Bill> cashierWiseReport(@Param("contactId") int contactId,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);



}
