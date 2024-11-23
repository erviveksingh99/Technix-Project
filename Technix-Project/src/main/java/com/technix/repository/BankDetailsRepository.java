package com.technix.repository;

import com.technix.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Integer> {
    List<BankDetails> findByCompanyId(int companyId);
}
