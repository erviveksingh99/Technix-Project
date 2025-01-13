package com.technix.repository;

import com.technix.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    // Dynamic query to fetch plans by planType (monthly or yearly)
    @Query("SELECT p FROM Plan p WHERE p.planType = :planType")
    List<Plan> findPlansByType(@Param("planType") String planType);
}
