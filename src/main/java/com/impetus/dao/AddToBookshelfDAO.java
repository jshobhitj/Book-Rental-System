package com.impetus.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.exceptions.DAOException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;

// TODO: Auto-generated Javadoc
/**
 * The Class AddToBookshelfDAO. This class contains method for adding book delivery request in
 * book_rental_log table.
 */
@Repository
public class AddToBookshelfDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(AddToBookshelfDAO.class);
    
    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Adds book to bookshelf. This method adds book delivery request to the 
     * book_rental_log table.
     *
     * @param bookRequest the book request
     */
    @Transactional
    public void addToBookshelfDAO(BookRentalLog bookRequest) {
    	try {
            LOGGER.info("Adding book with isbn code: "
                    + bookRequest.getBookCatalogue().getIsbnCode()
                    + " to book_rental_log table for user with user id:"
                    + bookRequest.getUserDetails().getUserId());
            Session session = sessionFactory.getCurrentSession();
            long isbnCode = bookRequest.getBookCatalogue().getIsbnCode();
            BookCatalogue book = (BookCatalogue) session.get(
                    BookCatalogue.class, isbnCode);
            book.setRecommentCount(book.getRecommentCount() + 1);
            book.setNoOfCopiesAvailable(book.getNoOfCopiesAvailable() - 1);
            session.update(book);
            session.persist(bookRequest);
        } catch (Exception e) {
            LOGGER.error("DAOException: " + e.getMessage() + "\nCaused By:\n"+ e);
            throw new DAOException("Book can't be issued", e);
        }
    }
}