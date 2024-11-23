package com.technix.repository;

import com.technix.entity.Directors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DirectorsRepository extends JpaRepository<Directors, Integer> {
    List<Directors> findByCompanyId(int companyId);
}
