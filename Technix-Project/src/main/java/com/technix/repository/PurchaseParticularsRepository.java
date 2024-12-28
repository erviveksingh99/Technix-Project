package com.technix.repository;

import com.technix.entity.PurchaseParticulars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseParticularsRepository extends JpaRepository<PurchaseParticulars, Integer> {

}