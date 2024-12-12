package com.technix.repository;

import com.technix.entity.BillParticulars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillParticularsRepository extends JpaRepository<BillParticulars, Integer> {

    void deleteByBillId(int billId);
}
