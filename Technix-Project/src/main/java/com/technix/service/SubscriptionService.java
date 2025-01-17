package com.technix.service;

import com.technix.entity.Subscription;

public interface SubscriptionService {

    Subscription createTrial(Subscription subscription);

    Subscription getActiveSubscription(int customerId);
}
