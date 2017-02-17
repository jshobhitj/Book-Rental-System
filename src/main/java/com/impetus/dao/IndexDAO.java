package com.impetus.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.BookCatalogue;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexDAO. This class contains DAO calls for listing available categories of books,
 * new released books and most popular books etc.
 */
@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IndexDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(IndexDAO.class);

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // list all category
    /**
     * Lists all categories of books.
     *
     * @return the list
     */
    @Transactional
    public List listCategoryDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all available categories");
            Criteria criteria = session.createCriteria(BookCatalogue.class)
                    .add(Restrictions.eq("availibility", true));
            criteria.setProjection(
                    Projections.distinct(Projections.property("category")))
                    .addOrder(Order.asc("category"));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n" + e);
            throw new DAOException("Cannot get the list", e);
        }
    }

    // list new release
    /**
     * List new release books.
     *
     * @return the list
     */
    @Transactional
    public List<BookCatalogue> listNewReleaseDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing new released books.");
            Criteria criteria = session.createCriteria(BookCatalogue.class);
            criteria.add(Restrictions.eq("availibility", true));
            criteria.addOrder(Order.desc("publishedDate")).setMaxResults(6);
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n" + e);
            throw new DAOException("Cannot get the list", e);
        }
    }

    // list Most Popular
    /**
     * List most popular books.
     *
     * @return the list
     */
    @Transactional
    public List<BookCatalogue> listMostPopularDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing most popular books.");
            Criteria criteria = session.createCriteria(BookCatalogue.class)
                    .add(Restrictions.eq("availibility", true));
            criteria.addOrder(Order.desc("recommentCount")).setMaxResults(6);
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n" + e);
            throw new DAOException("Cannot get the list", e);
        }
    }
}
