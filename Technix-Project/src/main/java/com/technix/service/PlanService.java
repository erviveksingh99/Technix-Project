package com.technix.service;

import com.technix.entity.Plan;

import java.util.List;

public interface PlanService {
    public List<Plan> getPlans(String planType);
}
