package com.technix.controller;

import com.technix.entity.Plan;
import com.technix.entity.Subscription;
import com.technix.service.PlanService;
import com.technix.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    private final PlanService planService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, PlanService planService){
        this.subscriptionService = subscriptionService;
        this.planService = planService;
    }

    @GetMapping("/type/{planType}")
    public Map<String, Object> GetPlans(@PathVariable("planType") String planType) {
        List<Plan> plans = planService.getPlans(planType);
        Map<String, Object> response = new HashMap<>();
        response.put("data", plans);
        response.put("status", true);
        return response;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTrial(@RequestBody Subscription subscription) {
        Subscription subscription1 = subscriptionService.createTrial(subscription);
        Map<String, Object> response = new HashMap<>();
        response.put("data", subscription1);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkSubscription/{customerId}")
    public ResponseEntity<Map<String, Object>> getActiveSubscription(@PathVariable("customerId") int customerId) {
        Subscription subscription = subscriptionService.getActiveSubscription(customerId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", subscription);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

}
