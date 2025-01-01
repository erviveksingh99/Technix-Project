package com.technix.repository;

import com.technix.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING_INDEX(purchase_no, '/', -1) AS UNSIGNED)), 0) FROM tblpurchase WHERE company_id = :companyId", nativeQuery = true)
    int findMaxPurchaseNo(@Param("companyId") int companyId);

    Purchase findByTransactionId(int transactionId);

}
