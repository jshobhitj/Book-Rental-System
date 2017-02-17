package com.impetus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.SubscriptionPlans;
import com.impetus.service.SubscriptionPlansService;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlansController. This class contains methods and handlers for subscription plans page(i.e. method for
 * listing active subscription plans with details. 
 */
@Controller
public class SubscriptionPlansController {

    /** The subscription plans service. */
    @Autowired
    private SubscriptionPlansService subscriptionPlansService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(SubscriptionPlans.class);

    /**
     * List. This method lists all active subscription plans with details.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/subscriptionPlans")
    public String list(Model model) {
        try {
			LOGGER.info("Loading subscription plan page.");
			List<SubscriptionPlans> subscriptionPlans = subscriptionPlansService
					.list();
			model.addAttribute("subscriptionPlans", subscriptionPlans);
			return "subscriptionPlans";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }
}