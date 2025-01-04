package com.technix.repository;

import com.technix.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    /*// Get all monthly plans
    List<Plan> findByPlanType(String planType);

    // Get monthly plans using a custom query
    @Query("SELECT p FROM Plan p WHERE p.planType = 'Monthly'")
    List<Plan> findMonthlyPlans();

    // Get yearly plans using a custom query
    @Query("SELECT p FROM Plan p WHERE p.planType = 'Yearly'")
    List<Plan> findYearlyPlans();*/

    // Dynamic query to fetch plans by planType (monthly or yearly)
    @Query("SELECT p FROM Plan p WHERE p.planType = :planType")
    List<Plan> findPlansByType(@Param("planType") String planType);
}
