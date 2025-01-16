package com.technix.repository;

import com.technix.entity.TransactionDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByTransactionNo(int transactionNo);

    @Modifying
    @Transactional
    @Query("DELETE FROM TransactionDetails td WHERE td.transactionId = :transactionId")
    void deleteByTransactionId(@Param("transactionId") int transactionId);

    // running
   /* @Query(value = "SELECT date_format(t.transaction_date, '%m-%Y') AS transactionDateFormatted, " +
            "SUM(t.debit) AS debit, SUM(t.credit) AS credit " +
            "FROM tbltransaction t " +
            "WHERE t.ledger_id = :ledgerId " +
            "GROUP BY date_format(t.transaction_date, '%m-%Y')", nativeQuery = true)
    List<Object[]> findMonthlyTransactionSummaryNative(@Param("ledgerId") int ledgerId);
*/

    @Query(value = "SELECT " +
            "DATE_FORMAT(t.transaction_date, '%m-%Y') AS transaction_date_formatted, " +
            "SUM(t.debit) AS Debit, " +
            "SUM(t.credit) AS Credit " +
            "FROM tbltransaction t " +
            "WHERE t.ledger_id = :ledgerId " +
            "AND t.transaction_date BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE_FORMAT(t.transaction_date, '%m-%Y')",
            nativeQuery = true)
    List<Object[]> findMonthlyTransactionSummaryNative(@Param("ledgerId") int ledgerId,
                                                       @Param("startDate") String startDate,
                                                       @Param("endDate") String endDate);

    @Query(value = "SELECT transaction_date, transaction_no, voucher_type, voucher_no, debit, credit, payment_mode, cheque_no, cheque_date, created_by, creation_date " +
            "FROM tbltransaction " +
            "WHERE voucher_type = :voucherType " +
            "AND transaction_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> findTransactionsByVoucherTypeAndDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("voucherType") String voucherType);


    @Query(value = "SELECT transaction_date, transaction_no, voucher_type, voucher_no, debit, credit, payment_mode, cheque_no, cheque_date, created_by, creation_date " +
            "FROM tbltransaction " +
            "WHERE transaction_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> findTransactionsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


}
