package com.impetus.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.BookCatalogue;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchBookDAO.
 */
@Repository
@SuppressWarnings({ "unchecked" })
public class SearchBookDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(SearchBookDAO.class);

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // to search books
    /**
     * Finds all books who satisfies the criteria.
     *
     * @param searchBook the search book
     * @return the list
     */
    @Transactional
    public List<BookCatalogue> findAllDAO(String searchBook) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("SDearching books with: " + searchBook);
            Criteria criteria = session.createCriteria(BookCatalogue.class)
                    .add(Restrictions.eq("availibility", true));
            criteria.add(Restrictions.or(
                    Restrictions.like("title", "%" + searchBook + "%"),
                    Restrictions.like("author", "%" + searchBook + "%"),
                    Restrictions.like("category", "%" + searchBook + "%")));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n" + e);
            throw new DAOException("Can't search books", e);
        }
    }

}