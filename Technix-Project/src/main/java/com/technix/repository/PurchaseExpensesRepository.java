package com.technix.repository;

import com.technix.entity.PurchaseExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseExpensesRepository extends JpaRepository<PurchaseExpenses, Integer> {
}
