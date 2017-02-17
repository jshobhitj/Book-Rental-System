package com.impetus.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.RegisterDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.Roles;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;
import com.impetus.model.UserRoles;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterService.
 */
@Service
public class RegisterService {
    
    /** The register dao. */
    @Autowired
    private RegisterDAO registerDAO;
    
    /** The index service. */
    @Autowired
    private IndexService indexService;

    /**
     * Sets the register dao.
     *
     * @param registerDAO the new register dao
     */
    public void setRegisterDAO(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    /**
     * Sets the index service.
     *
     * @param indexService the new index service
     */
    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }

    // registration User Details
    /**
     * Register service.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param permanentAddress the permanent address
     * @param phoneNo the phone no
     * @param userEmailId the user email id
     * @param userPassword the user password
     * @param language the language
     */
    public void registerService(String firstName, String lastName,
            String permanentAddress, long phoneNo, String userEmailId,
            String userPassword, String language) {
        try {
            UserDetails userDetails = new UserDetails();
            List<BookCatalogue> bookMostPopular = indexService
                    .listMostPopularService();
            ListIterator<BookCatalogue> itrbookMostPopular = bookMostPopular
                    .listIterator();
            StringBuilder bookString = new StringBuilder();
            for (int i = 0; i <= 5; i++) {
                bookString.append(itrbookMostPopular.next().getIsbnCode());
                if (i != 5) {
                    bookString.append(",");
                }
            }
            userDetails.setUserId(userEmailId);
            userDetails.setFirstName(firstName);
            userDetails.setLastName(lastName);
            userDetails.setUserPassword(userPassword);
            userDetails.setLanguage(language);
            userDetails.setPermanentAddress(permanentAddress);
            userDetails.setPhoneNo(phoneNo);
            userDetails.setRecommended(bookString.toString());
            userDetails.setUserEmailId(userEmailId);
            UserRoles user = new UserRoles();
            user.setUserId(userEmailId);
            user.setRole(Roles.ROLE_USER);
            registerDAO.registerDAO(userDetails);
            registerDAO.registerRole(user);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // registration user's subscription plan
    /**
     * Register plan service.
     *
     * @param userEmailId the user email id
     * @param subscriptionPlan the subscription plan
     */
    public void registerPlanService(String userEmailId, Integer subscriptionPlan) {
        try {
            SubscriptionLog userSubcriptionPlan = new SubscriptionLog();
            UserDetails userDetails = new UserDetails();
            SubscriptionPlans subscrptionPlan = new SubscriptionPlans();
            subscrptionPlan = registerDAO.subscriptionPlan(subscriptionPlan);
            Integer year = subscrptionPlan.getPeriod();
            userDetails.setUserId(userEmailId);
            userSubcriptionPlan.setUserDetails(userDetails);
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Timestamp startDate = new Timestamp(stamp.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(startDate.getTime());
            cal.add(Calendar.YEAR, year);
            Timestamp endDate = new Timestamp(cal.getTime().getTime());
            userSubcriptionPlan.setStartDate(startDate);
            userSubcriptionPlan.setEndDate(endDate);
            userSubcriptionPlan.setSubscriptionPlanStatus(Status.ACTIVE);
            userSubcriptionPlan.setSubscriptionPlan(subscrptionPlan
                    .getSubscriptionPlan());
            userSubcriptionPlan.setPeriod(subscrptionPlan.getPeriod());
            userSubcriptionPlan.setAmount(subscrptionPlan.getAmount());
            userSubcriptionPlan.setNoOfBooks(subscrptionPlan.getNoOfBooks());
            registerDAO.registerPlanDAO(userSubcriptionPlan);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // user id availability
    /**
     * Available service.
     *
     * @param userId the user id
     * @return true, if successful
     */
    public boolean availableService(String userId) {
        try {
            return registerDAO.availableDAO(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}