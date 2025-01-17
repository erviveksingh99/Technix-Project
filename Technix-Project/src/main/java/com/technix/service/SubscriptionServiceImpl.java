package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Customer;
import com.technix.entity.Plan;
import com.technix.entity.Subscription;
import com.technix.repository.CustomerRepository;
import com.technix.repository.PlanRepository;
import com.technix.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepo;

    private final CustomerRepository customerRepo;

    private final PlanRepository planRepo;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepo, CustomerRepository customerRepo, PlanRepository planRepo){
        this.subscriptionRepo = subscriptionRepo;
        this.customerRepo = customerRepo;
        this.planRepo = planRepo;
    }

    @Transactional
    @Override
    public Subscription createTrial(Subscription subscription) {
        Optional<Subscription> optionalSubscription = subscriptionRepo.findByCustomerIdAndStatusTrueAndIsTrailTrue(subscription.getCustomerId());
        if (optionalSubscription.isPresent()) {
            throw new ResponseStatusException(HttpStatus.OK, "You have already used free trial plan");
        }
        try {

            subscriptionRepo.inactiveAllPlans(subscription.getCustomerId());

            Subscription newSubscription = new Subscription();
            newSubscription.setCustomerId(subscription.getCustomerId());
            newSubscription.setPlanId(subscription.getPlanId());
            newSubscription.setCreatedBy(subscription.getCreatedBy());

            newSubscription.setPlanValidity(subscription.getPlanValidity());
            newSubscription.setTrial(true);
            newSubscription.setStatus(true);
            newSubscription.setUserCount(subscription.getUserCount());
            newSubscription.setInvoiceCount(subscription.getInvoiceCount());
            newSubscription.setCompanyCount(subscription.getCompanyCount());
            newSubscription.setBranchCount(subscription.getBranchCount());
            newSubscription.setEmailsCount(subscription.getEmailsCount());
            newSubscription.setSmsCount(subscription.getSmsCount());

            newSubscription.startSubscriptionDate(subscription.getPlanValidity());
            Subscription savedSubscription = subscriptionRepo.save(newSubscription);

            Optional<Plan> plan = planRepo.findById(savedSubscription.getPlanId());
            if (plan.isPresent()) {
                Plan newPlan = new Plan();
                newPlan.setTrialAvailable(savedSubscription.isTrial());
                planRepo.save(newPlan);
            }
            return savedSubscription;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription data saving failed reason: " + e.getMessage());
        }
    }


    @Override
    public Subscription getActiveSubscription(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            Subscription activeSubscription = subscriptionRepo.findByCustomerId(customerId);
            if (activeSubscription==null) {
                throw new IdNotFoundException("You don't have any subscription");
            }
          //  Subscription subscription = activeSubscription.get();
            return activeSubscription;
        } else {
            throw new IdNotFoundException("Customer doesn't exist");
        }
    }
}
