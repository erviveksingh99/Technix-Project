package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Plan;
import com.technix.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepo;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepo){
        this.planRepo = planRepo;
    }

    @Override
    public List<Plan> getPlans(String planType) {
        try {
            return planRepo.findPlansByType(planType);
        } catch (Exception e) {
            throw new IdNotFoundException("Plan type is invalid reason: " + e.getMessage());
        }
    }
}
