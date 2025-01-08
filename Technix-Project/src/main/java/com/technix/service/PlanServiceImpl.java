package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Plan;
import com.technix.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {


    @Override
    public List<Plan> getPlans(String planType) {
        return List.of();
    }
}
