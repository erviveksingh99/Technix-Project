package com.technix.repository;

import com.technix.entity.BillAdditionalCharges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillAdditionalChargesRepository extends JpaRepository<BillAdditionalCharges, Integer> {

}
