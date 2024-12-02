package com.technix.repository;

import com.technix.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Integer> {
    List<Ledger> findByCompanyId(int companyId);

//    List<Ledger> findByAccountId(int accountId);
}
