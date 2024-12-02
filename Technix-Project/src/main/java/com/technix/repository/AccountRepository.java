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


    // New method to find child accounts by parent accountId
    List<Account> findByAccountSubId(int accountId);

//    @Query(value = "SELECT * FROM tblaccount WHERE company_id IN (0, :companyId) AND (:parentAccountId IS NULL OR account_sub_id = :parentAccountId)", nativeQuery = true)
//    List<Account> findByCompanyIdAndParentId(int companyId, Integer parentAccountId);

//    @Query(value = "SELECT * FROM tblaccount WHERE company_id IN (0, :companyId) AND account_sub_id is null", nativeQuery = true)
//    List<Account> findByCompanyIdAndAccountSubId(@Param("companyId") int companyId);


//    @Query(value = "SELECT * FROM tblaccount WHERE company_id IN (0, :companyId) AND account_sub_id IS NULL", nativeQuery = true)
//    List<Account> findByCompanyIdAndAccountSubId(@Param("companyId") int companyId);


    @Query(value = "SELECT * FROM tblaccount WHERE (company_id = :companyId OR company_id = 0) AND account_sub_id IS NULL", nativeQuery = true)
    List<Account> findByCompanyIdAndAccountSubId(@Param("companyId") int companyId);



    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Account c WHERE c.companyId = :companyId")
    boolean existsByCompanyId(int companyId);
}
