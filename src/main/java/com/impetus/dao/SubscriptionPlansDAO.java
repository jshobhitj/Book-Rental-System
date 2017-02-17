package com.impetus.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionPlans;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlansDAO.
 */
@Repository
@SuppressWarnings({ "unchecked" })
public class SubscriptionPlansDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(SubscriptionPlansDAO.class);

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Finds all active subscription plans in database.
     *
     * @return the list
     */
    @Transactional
    public List<SubscriptionPlans> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Finding all active subscription plans.");
            Criteria criteria = session.createCriteria(SubscriptionPlans.class)
                    .add(Restrictions.eq("status", Status.ACTIVE))
                    .addOrder(Order.asc("amount"));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n"+ e);
            throw new DAOException("Cannot get the list", e);
        }
    }
}