package com.technix.repository;

import com.technix.entity.PurchaseProductTaxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductTaxesRepository extends JpaRepository<PurchaseProductTaxes, Integer> {

}