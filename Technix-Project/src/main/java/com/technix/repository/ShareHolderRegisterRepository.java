package com.technix.repository;

import com.technix.entity.ShareHolderRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareHolderRegisterRepository extends JpaRepository<ShareHolderRegister, Integer> {
    List<ShareHolderRegister> findByCompanyId(int companyId);
}
