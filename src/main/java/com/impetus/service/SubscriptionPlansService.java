package com.impetus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.SubscriptionPlansDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.SubscriptionPlans;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlansService.
 */
@Service
public class SubscriptionPlansService {
    
    /** The subscription plans dao. */
    @Autowired
    private SubscriptionPlansDAO subscriptionPlansDAO;

    /**
     * Sets the subscription plans dao.
     *
     * @param subscriptionPlansDAO the new subscription plans dao
     */
    public void setSubscriptionPlansDAO(
            SubscriptionPlansDAO subscriptionPlansDAO) {
        this.subscriptionPlansDAO = subscriptionPlansDAO;
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<SubscriptionPlans> list() {
        try {
            return subscriptionPlansDAO.findAll();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}