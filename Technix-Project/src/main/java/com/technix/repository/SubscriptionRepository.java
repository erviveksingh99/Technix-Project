package com.technix.repository;

import com.technix.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query("SELECT s " +
            "FROM Subscription s " +
            "WHERE s.customerId = :customerId AND s.isTrial = true ")
        //pending: AND s.isTrial = true
    Optional<Subscription> findByCustomerIdAndStatusTrueAndIsTrailTrue(@Param("customerId") int customerId);

    @Query(value = "SELECT * FROM tblcustomer_subscription WHERE customer_id = :customerId AND status=true", nativeQuery = true)
    Subscription findByCustomerId(@Param("customerId") int customerId);

    @Transactional
    @Modifying
    @Query(value = "Update tblcustomer_subscription set status = false WHERE customer_id = :customerId and status = true", nativeQuery = true)
    void inactiveAllPlans(@Param("customerId") int customerId);

}
