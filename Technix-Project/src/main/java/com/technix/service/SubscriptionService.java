package com.technix.service;

import com.technix.entity.Subscription;

public interface SubscriptionService {

    public Subscription createSubscription(Subscription subscription);

    public Subscription getActiveSubscription(int customerId);
}
