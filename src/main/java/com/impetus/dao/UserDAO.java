package com.impetus.dao;

import java.sql.Timestamp;
import java.util.Calendar;
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
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.BookStatus;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO. This class contains methods for all user related functionalities such as
 * listing currently issued books, adding a delivery or return request etc. 
 */
@Repository
@SuppressWarnings({ "unchecked" })
public class UserDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
    static final  Logger LOGGER = Logger.getLogger(UserDAO.class);
    private static final String STRING1="from BookRentalLog where user_id='";
    private static final String STRING2="DAOException: ";
    private static final String STRING3="\nCaused By:\n";
    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // bookshelf
    /**
     * Finds all issued books.
     *
     * @param userId the user id
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> findAll(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Finding all issued books for user: " + userId);
            return session.createQuery(
                    STRING1+ userId
                            + "' and bookshelf=true").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot find issued books", e);
        }
    }

    // return book
    /**
     * Return book dao method.
     *
     * @param transactionId the transaction id
     * @param requestedAddress the requested address
     */
    @Transactional
    public void returnBookDAO(long transactionId, String requestedAddress) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Returning book with transaction id: " + transactionId);
            BookRentalLog request = (BookRentalLog) session.get(
                    BookRentalLog.class, transactionId);
            request.setReturnStatus(BookStatus.PENDING);
            request.setRequestedAddress(requestedAddress);
            session.update(request);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3 + e);
            throw new DAOException("", e);
        }
    }

    // Cancel Return Request
    /**
     * Cancel return requests dao method.
     *
     * @param transactionId the transaction id
     * @param task the task
     */
    @Transactional
    public void cancelReturnRequestsDAO(long transactionId, boolean task) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Cancel return request with transaction id: "
                    + transactionId);
            if (task) {
                BookRentalLog request = (BookRentalLog) session.get(
                        BookRentalLog.class, transactionId);
                request.setReturnStatus(BookStatus.NONE);

                Timestamp r = new Timestamp(System.currentTimeMillis());
                int month = 1;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(r.getTime());
                cal.add(Calendar.MONTH, month);
                Timestamp returnDate = new Timestamp(cal.getTime().getTime());

                request.setReturnDate(returnDate);
                session.update(request);
            } else {
                BookRentalLog request = (BookRentalLog) session.get(
                        BookRentalLog.class, transactionId);
                request.setReturnStatus(BookStatus.CANCELLED);
                session.update(request);
            }
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Return request can't be cancelled", e);
        }
    }

    // Cancel Delivery Request
    /**
     * Cancel delivery requests dao method.
     *
     * @param transactionId the transaction id
     */
    @Transactional
    public void cancelDeliveryRequestsDAO(long transactionId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Cancel delivery request with transaction id: "
                    + transactionId);
            BookRentalLog request = (BookRentalLog) session.get(
                    BookRentalLog.class, transactionId);
            request.setDeliveryStatus(BookStatus.CANCELLED);
            long isbnCode = request.getBookCatalogue().getIsbnCode();
            BookCatalogue book = (BookCatalogue) session.get(
                    BookCatalogue.class, isbnCode);
            book.setNoOfCopiesAvailable(book.getNoOfCopiesAvailable() + 1);
            session.update(book);
            Timestamp returnDate = new Timestamp(System.currentTimeMillis());
            request.setReturnDate(returnDate);
            request.setBookshelf(false);
            session.update(request);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Delivery request can't be cancelled", e);
        }
    }

    // user subscription plan details
    /**
     * Getting user's subscription plan details dao method.
     *
     * @param user the user
     * @return the subscription log
     */
    @Transactional
    public SubscriptionLog subscriptionPlanDAO(String user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting user: " + user + " subscription plan details");
            return (SubscriptionLog) session.createQuery(
                    "from SubscriptionLog where user_id='" + user
                            + "' and subscriptionPlanStatus='" + Status.ACTIVE
                            + "'").uniqueResult();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot get subs. plan details", e);
        }
    }

    // user book history
    /**
     * User's book issued history dao method.
     *
     * @param user the user
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> bookHistoryDAO(String user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing user's book issued history");
            return session.createQuery(
                    STRING1+ user
                            + "' and bookshelf='" + false + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot get user book history", e);
        }
    }

    // user subscription history
    /**
     * User's subscription history dao method.
     *
     * @param user the user
     * @return the list
     */
    @Transactional
    public List<SubscriptionLog> subscriptionHistoryDAO(String user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing user's previous subscription plans");
            return session.createQuery(
                    "from SubscriptionLog where user_id='" + user
                            + "' and subscriptionPlanStatus='" + Status.EXPIRED
                            + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot get user subs. history", e);
        }
    }

    // add to Wishlist
    /**
     * Adds book to wishlist dao method.
     *
     * @param wish the wish
     */
    @Transactional
    public void addToWishlistDAO(Wishlist wish) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Adding book: " + wish.getBookCatalogue().getIsbnCode()
                    + " in user wishlist");
            long isbnCode = wish.getBookCatalogue().getIsbnCode();
            BookCatalogue book = (BookCatalogue) session.get(
                    BookCatalogue.class, isbnCode);
            book.setRecommentCount(book.getRecommentCount() + 1);
            session.update(book);
            session.persist(wish);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot add to wish list", e);
        }
    }

    // Show Wishlist
    /**
     * Show wishlist dao method.
     *
     * @param userId the user id
     * @return the list
     */
    @Transactional
    public List<Wishlist> showWishlistDAO(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing wishlist for user: " + userId);
            return session.createQuery(
                    "from Wishlist where user_id='" + userId
                            + "' order by wishDate desc").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot get wishlist", e);
        }
    }

    // Remove from Wishlist
    /**
     * Removes book from wishlist dao method.
     *
     * @param sno the sno
     */
    @Transactional
    public void removeFromWishlistDAO(long sno) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Removing book from wishlist.");
            Wishlist wish = (Wishlist) session.get(Wishlist.class, sno);
            session.delete(wish);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot remove book from wishlist", e);
        }
    }

    // get books details
    /**
     * Get book details.
     *
     * @param isbnCode the isbn code
     * @return the book catalogue
     */
    @Transactional
    public BookCatalogue bookDetails(long isbnCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting book's details with ISBN Code: " + isbnCode);
            return (BookCatalogue) session.createQuery(
                    "from BookCatalogue where isbnCode='" + isbnCode + "'")
                    .uniqueResult();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cann't get book details", e);
        }
    }

    // get books issued no.
    /**
     * No of books issued by user.
     *
     * @param userId the user id
     * @return the integer
     */
    @Transactional
    public Integer noOfBooksIssued(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting no. of books issued by user: " + userId);
            List<BookRentalLog> books = session.createQuery(
                    STRING1+ userId
                            + "' and bookshelf=true").list();
            return books.size();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Cannot get no. of books issued", e);
        }
    }

    // check if book is already issued
    /**
     * Checks if book is already issued.
     *
     * @param isbnCode the isbn code
     * @param userId the user id
     * @return true, if successful
     */
    @Transactional
    public boolean statusIssued(long isbnCode, String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Checking if book: " + isbnCode
                    + " is already issued for user: " + userId);
            List<BookRentalLog> books = session.createQuery(
                    STRING1+ userId
                            + "' and isbn_code='" + isbnCode
                            + "' and bookshelf=true").list();
            ListIterator<BookRentalLog> listBooks = books.listIterator();
            return listBooks.hasNext();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't get status if book is issued or not", e);
        }
    }

    // check already in wishlist
    /**
     * Checks whether a book is already in wish list or not.
     *
     * @param isbnCode the isbn code
     * @param userId the user id
     * @return true, if successful
     */
    @Transactional
    public boolean statusWishlistDAO(long isbnCode, String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Checking if book: " + isbnCode
                    + " is already in wish list or not.");
            List<Wishlist> booksWishlist = session.createQuery(
                    "from Wishlist where user_id='" + userId
                            + "' and isbn_code='" + isbnCode + "'").list();
            ListIterator<Wishlist> listBooks = booksWishlist.listIterator();
            return listBooks.hasNext();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't check", e);
        }
    }

    // change subscription log status
    /**
     * Change subscription log status dao method.
     *
     * @param subscription the subscription
     */
    @Transactional
    public void changeSubscriptionStatusDAO(SubscriptionLog subscription) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Changing subs. plan status on expiration of user's subscription.");
            session.update(subscription);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't change status", e);
        }
    }

    // getting user history for recommendation
    /**
     * Gets the user book issued history.
     *
     * @param userId the user id
     * @return the user history
     */
    @Transactional
    public List<BookRentalLog> gettingUserHistory(String userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting user book issued history.");
            return session.createQuery(
                    STRING1+ userId
                            + "' order by issueDate desc").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't get user books history.", e);
        }
    }

    // getting books by category for recommendation
    /**
     * Gets a particular category books.
     *
     * @param category the category
     * @return the category books
     */
    @Transactional
    public List<BookCatalogue> gettingCategoryBooks(String category) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting books with category: " + category);
            return session.createQuery(
                    "from BookCatalogue where category='" + category + "'")
                    .list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't get list", e);
        }
    }

    // getting books by author for recommendation
    /**
     * Gets a particular author books.
     *
     * @param author the author
     * @return the author books
     */
    @Transactional
    public List<BookCatalogue> gettingAuthorBooks(String author) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting the particular author: " + author + " books.");
            return session.createQuery(
                    "from BookCatalogue where author='" + author + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't get author's list", e);
        }
    }

    // update user recommendations
    /**
     * Updates user recommendation dao method.
     *
     * @param user the user
     */
    @Transactional
    public void updateUserRecomDAO(UserDetails user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Updating user: " + user.getUserId()
                    + " recommendations.");
            session.update(user);
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't update recommendations", e);
        }
    }

    // get Books details
    /**
     * Gets the book's details.
     *
     * @param isbnCode the isbn code
     * @return the the book
     */
    @Transactional
    public BookCatalogue getTheBook(long isbnCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Getting books deatilswith ISBN Code: " + isbnCode);
            Criteria criteria = session.createCriteria(BookCatalogue.class)
                    .add(Restrictions.eq("availibility", true));
            criteria.add(Restrictions.eq("isbnCode", isbnCode));
            return (BookCatalogue) criteria.uniqueResult();
        } catch (Exception e) {
            LOGGER.error(STRING2 + e.getMessage() + STRING3+ e);
            throw new DAOException("Can't get book deatils.", e);
        }
    }
}
