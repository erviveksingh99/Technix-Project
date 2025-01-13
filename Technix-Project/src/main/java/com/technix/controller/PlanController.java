package com.technix.controller;

import com.technix.entity.Plan;
import com.technix.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/type/{planType}")
    public Map<String, Object> GetPlans(@PathVariable("planType") String planType) {
        List<Plan> plans = planService.getPlans(planType);
        Map<String, Object> response = new HashMap<>();
        response.put("data", plans);
        response.put("status", true);
        return response;
    }
}
