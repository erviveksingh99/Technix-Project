package com.technix.repository;

import com.technix.entity.TransactionMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMainRepository  extends JpaRepository<TransactionMain, Integer> {
}
