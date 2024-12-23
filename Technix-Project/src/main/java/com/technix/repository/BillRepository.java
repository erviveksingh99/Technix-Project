package com.technix.repository;

import com.technix.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByCompanyId(int companyId);

    Bill findByBranchId(int branchId);

    Bill findByInvoiceNo(String invoiceNo);

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING_INDEX(invoice_no, '/', -1) AS UNSIGNED)), 0) FROM tblbill WHERE company_id = :companyId", nativeQuery = true)
    int findMaxInvoiceNo(@Param("companyId") int companyId);

}
