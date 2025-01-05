package com.technix.repository;

import com.technix.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query("SELECT s " +
            "FROM Subscription s " +
            "WHERE s.customerId = :customerId AND s.status = true AND s.isTrail = true")
    Optional<Subscription> findByCustomerIdAndStatusTrueAndIsTrailTrue(@Param("customerId") int customerId);
}
