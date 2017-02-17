package com.impetus.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginDAO.
 */
@Repository
public class UserLoginDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
   static final Logger LOGGER = Logger.getLogger(UserLoginDAO.class);

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // For getting user details
    /**
     * Get user details.
     *
     * @param userId the user id
     * @return the user details
     */
    @Transactional
    public UserDetails userDetailsDAO(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting user details for user id: " + userId);
            Criteria criteria = session.createCriteria(UserDetails.class).add(
                    Restrictions.eq("userId", userId));
            return (UserDetails) criteria.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n"+ e);
            throw new DAOException("Can't get user details", e);
        }
    }
}
