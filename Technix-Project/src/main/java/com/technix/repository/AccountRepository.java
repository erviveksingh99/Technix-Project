package com.technix.repository;

import com.technix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountId(int accountId);

    List<Account> findByCompanyId(int companyId);

    @Query(value = "SELECT * FROM tblaccount WHERE (company_id = :companyId OR company_id = 0) AND account_sub_id IS NULL", nativeQuery = true)
    List<Account> findByCompanyIdAndAccountSubId(@Param("companyId") int companyId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Account c WHERE c.companyId = :companyId")
    boolean existsByCompanyId(int companyId);

//    @Query(value = "SELECT * FROM tblaccount WHERE account_id = :accountId AND company_id = :companyId", nativeQuery = true)
//    Account findByAccountIdAndCompanyId(@Param("accountId") int accountId, @Param("companyId") int companyId);


//    @Query(value = "SELECT a.*, l.* FROM tblaccount a " +
//            "LEFT JOIN tblledger l ON a.account_id = l.account_id " +
//            "WHERE a.account_id = :accountId " +
//            "AND (l.company_id = :companyId OR l.company_id = 0) " +
//            "AND a.company_id = :companyId", nativeQuery = true)
//    Account findByAccountIdAndCompanyId(@Param("accountId") int accountId, @Param("companyId") int companyId);

//    @Query(value = "SELECT * FROM tblaccount a " +
//            "LEFT JOIN tblledger l ON a.account_id = l.account_id " +
//            "WHERE a.account_id = :accountId " +
//            "AND (l.company_id = :companyId OR (l.company_id = 0 AND NOT EXISTS " +
//            "(SELECT 1 FROM tblledger WHERE account_id = a.account_id AND company_id = :companyId)))",
//            nativeQuery = true)
//    Account findByAccountIdAndCompanyIdWithLedgers(@Param("accountId") int accountId, @Param("companyId") int companyId);


    @Query("SELECT a FROM Account a WHERE a.companyId = :companyId " +
            "AND a.accountSubId IS NULL " +
            "AND (a.accounts = 'Assets' OR a.accounts = 'Liabilities')")
    List<Account> findAssetAndLiabilitiesAccounts(@Param("companyId") int companyId);


}
