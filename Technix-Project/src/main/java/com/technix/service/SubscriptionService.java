package com.technix.service;

import com.technix.entity.Subscription;

public interface SubscriptionService {

    public Subscription createTrial(Subscription subscription);

    public Subscription getActiveSubscription(int customerId);
}
