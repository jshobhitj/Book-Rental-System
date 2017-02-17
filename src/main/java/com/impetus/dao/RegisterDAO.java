package com.impetus.dao;

import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;
import com.impetus.model.UserRoles;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterDAO.
 */
@Repository
@SuppressWarnings("unchecked")
public class RegisterDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(RegisterDAO.class);
     private static final String STRING1="\nCaused By:\n";
     private static final String STRING2="DAOException: ";
    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // registration User Details
    /**
     * Registers users.
     *
     * @param userDetails the user details
     */
    @Transactional
    public void registerDAO(UserDetails userDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Registering new user in database.");
            session.persist(userDetails);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING1 + e);
            throw new DAOException("User details can't be added", e);
        }
    }

    // subscription plan details
    /**
     * This method searches for a subscription plan in the database.
     *
     * @param subscriptionPlan the subscription plan
     * @return the subscription plans
     */
    @Transactional
    public SubscriptionPlans subscriptionPlan(Integer subscriptionPlan) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Searching for subs. plan with no: " + subscriptionPlan);
            SubscriptionPlans subscriptionPlanDetails = new SubscriptionPlans();
            Criteria cr = session.createCriteria(SubscriptionPlans.class);
            cr.add(Restrictions.eq("subscriptionPlan", subscriptionPlan));
            subscriptionPlanDetails = (SubscriptionPlans) cr.uniqueResult();
            return subscriptionPlanDetails;
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING1 + e);
            throw new DAOException("Can't get subscription plan details", e);
        }
    }

    // register Role
    /**
     * Register role.
     *
     * @param user the user
     */
    @Transactional
    public void registerRole(UserRoles user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Registering new user's role");
            session.persist(user);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING1 + e);
            throw new DAOException("Can't enter user role", e);
        }
    }

    // registration user's subscription plan
    /**
     * Register plan dao.
     *
     * @param userSubcriptionPlan the user subcription plan
     */
    @Transactional
    public void registerPlanDAO(SubscriptionLog userSubcriptionPlan) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Registering new user's subs. plan");
            session.persist(userSubcriptionPlan);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING1 + e);
            throw new DAOException("Can't enter user subscription", e);
        }
    }

    // user id availability
    /**
     * Checks user id availability.
     *
     * @param userId the user id
     * @return true, if successful
     */
    @Transactional
    public boolean availableDAO(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Checking whether user Id: " + userId
                    + " available or not");
            Criteria criteria = session.createCriteria(UserDetails.class).add(
                    Restrictions.eq("userId", userId));
            List<UserDetails> userCredentials = criteria.list();
            ListIterator<UserDetails> litr = userCredentials.listIterator();
            return litr.hasNext();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING1+ e);
            throw new DAOException("Can't check user id", e);
        }

    }
}