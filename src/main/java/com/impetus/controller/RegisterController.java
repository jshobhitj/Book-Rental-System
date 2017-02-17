package com.impetus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.SubscriptionPlans;
import com.impetus.service.RegisterService;
import com.impetus.service.SubscriptionPlansService;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterController. This class contains all methods and handlers related to user registration.
 */
@Controller
public class RegisterController {

    /** The register service. */
    @Autowired
    private RegisterService registerService;
    
    /** The subscription plans service. */
    @Autowired
    private SubscriptionPlansService subscriptionPlansService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(RegisterController.class);

    /**
     * Register. This method contains handler for redirecting user to registration form page and loading active subscription 
     * plans for user to subscribe at the time of registration. 
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/register")
    public String register(Model model) {
        try {
			LOGGER.info("Loading register page.");
			List<SubscriptionPlans> subscriptionPlans = subscriptionPlansService
					.list();
			model.addAttribute("subscriptionPlans", subscriptionPlans);
			return "register";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    /**
     * Registration. This method registers user with various details such as name, phone no., address etc.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param permanentAddress the permanent address
     * @param phoneNo the phone no
     * @param userEmailId the user email id
     * @param userPassword the user password
     * @param language the language
     * @param subscriptionPlan the subscription plan
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/register_form_submission", method = RequestMethod.POST)
    public String registration(@RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("permanent_address") String permanentAddress,
            @RequestParam("phone_no") long phoneNo,
            @RequestParam("user_email_id") String userEmailId,
            @RequestParam("user_password") String userPassword,
            @RequestParam("language") String language,
            @RequestParam("subscription_plan") Integer subscriptionPlan,
            Model model) {
        try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String userPassword1 = passwordEncoder.encode(userPassword);
			LOGGER.info("Registering user with user Id: " + userEmailId);
			registerService.registerService(firstName, lastName,
					permanentAddress, phoneNo, userEmailId, userPassword1,
					language);
			registerService.registerPlanService(userEmailId, subscriptionPlan);
			return "redirect:";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // user id availability
    /**
     * Available. This method checks whether a userId is available or not.
     *
     * @param request the request
     * @param response the response
     * @param userEmailId the user email id
     * @return the string
     */
    @RequestMapping(value = "/available", method = RequestMethod.POST)
    public @ResponseBody
    String available(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("user_email_id") String userEmailId) {
        LOGGER.info("Checking for availibility for user ID: " + userEmailId);
        boolean status = registerService.availableService(userEmailId);
        if (status) {
            return "Not";
        } else {
            return "";
        }
    }
}
