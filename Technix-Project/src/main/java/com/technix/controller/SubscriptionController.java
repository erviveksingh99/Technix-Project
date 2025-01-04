package com.technix.controller;

import com.technix.entity.Subscription;
import com.technix.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createSubscription(@RequestBody Subscription subscription) {
        Subscription subscription1 = subscriptionService.createSubscription(subscription);
        Map<String, Object> response = new HashMap<>();
        response.put("data", subscription1);
        response.put("satus", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkCustomer/{customerId}")
    public ResponseEntity<Map<String, Object>> getActiveSubscription(@PathVariable("customerId") int customerId) {
        Subscription subscription = subscriptionService.getActiveSubscription(customerId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", subscription);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
