package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Subscription;
import com.technix.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        try {
            Subscription newSubscription = new Subscription();

            // Set customerId, planId, etc. from request
            newSubscription.setCustomerId(subscription.getCustomerId());
            newSubscription.setPlanId(subscription.getPlanId());
            newSubscription.setCreatedBy(subscription.getCreatedBy());

            newSubscription.startSubscriptionDate(subscription.getPlanValidity());
            return subscriptionRepo.save(newSubscription);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription data saving failed reason: " + e.getMessage());
        }
    }

    @Override
    public Subscription getActiveSubscription(int customerId) {
        Optional<Subscription> activeSubscription = subscriptionRepo.existsByCustomerIdAndStatusTrueAndIsTrailTrue(customerId);
        if (activeSubscription.isEmpty()) {
            throw new IdNotFoundException("Customer is inactive");
        }
        Subscription subscription = activeSubscription.get();
        return subscription;
    }
}
