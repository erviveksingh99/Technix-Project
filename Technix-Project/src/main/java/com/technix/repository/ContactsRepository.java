package com.technix.repository;

import com.technix.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
    List<Contacts> findByCompanyId(int companyId);

    Contacts findByLedgerId(int ledgerId);

    @Query(value = "SELECT * from tblcontacts WHERE taxation_type = :taxationType", nativeQuery = true)
    List<Contacts> getRegisteredDealer(@Param("taxationType") String taxationType);

    @Query(value = "SELECT c.contact_name AS customerName, c.city AS city, " +
            "b.invoice_no AS invoiceNo, b.bill_date AS invoiceDate, " +
            "b.grand_total AS billAmount " +
            "FROM tblcontacts c " +
            "LEFT JOIN tblbill b ON c.contact_id = b.contact_id " +
            "WHERE c.taxation_type = :taxationType", nativeQuery = true)
    List<Object[]> findTaxableContactsWithBillDetails(@Param("taxationType") String taxationType);

}
