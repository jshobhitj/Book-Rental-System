package com.impetus.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
import com.impetus.model.BookRentalLog;
import com.impetus.model.BookStatus;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminDAO. This method contains all DAO layer methods for 
 * various admin functionalities for eg: adding new books, updating books etc. 
 */
@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminDAO {
    
    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(AdminDAO.class);
    private static final String STRING1="bookRentalLog";
    private static final String STRING2="Cannot get the list";
    private static final String STRING3="bookRentalLog.issueDate";
    private static final String STRING4=" to: ";
    private static final String STRING5="book";
    private static final String STRING6="Listing all the book requests from: ";
    private static final String STRING7="bookRentalLog.bookCatalogue";
    private static final String STRING8="'))";
    private static final String STRING9="DAOException: ";
    private static final String STRING10="select count(*) from BookRentalLog where (isbn_code='";
    private static final String STRING11="') and (issueDate>='";
    private static final String STRING12="\nCaused By:\n";
    private static final String STRING13="' and issueDate<='";
    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // list all books
    /**	
     * This DAO method gets all the list of books.
     *
     * @return the list
     */
    @Transactional
    public List<BookCatalogue> listBooksDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the books from database.");
            Criteria criteria = session.createCriteria(BookCatalogue.class);
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Cannot get the list.", e);
        }
    }

    // list all title
    /**
     * Lists all the title of books in the database.
     *
     * @return the list
     */
    @Transactional
    public List listTitleDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the titles of books in database.");
            Criteria criteria = session.createCriteria(BookCatalogue.class);
            criteria.setProjection(
                    Projections.distinct(Projections.property("title")))
                    .addOrder(Order.asc("title"));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // list all author
    /**
     * Lists all the authors of books in the database.
     *
     * @return the list
     */
    @Transactional
    public List listAuthorDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the authors of books from the database.");
            Criteria criteria = session.createCriteria(BookCatalogue.class);
            criteria.setProjection(
                    Projections.distinct(Projections.property("author")))
                    .addOrder(Order.asc("author"));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // list all category
    /**
     * Lists all the categories of books in the database.
     *
     * @return the list
     */
    @Transactional
    public List listCategoryDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the categories of books from the database");
            Criteria criteria = session.createCriteria(BookCatalogue.class);
            criteria.setProjection(
                    Projections.distinct(Projections.property("category")))
                    .addOrder(Order.asc("category"));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // update book
    /**
     * Update book dao method.
     *
     * @param book the book
     */
    @Transactional
    public void updateBookDAO(BookCatalogue book) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Updating book with ISBN Code: " + book.getIsbnCode());
            session.update(book);
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Book can't be updated", e);
        }
    }

    // search book with ISBN Code
    /**
     * Search book dao method.
     *
     * @param isbnCode the isbn code
     * @return the book catalogue
     */
    @Transactional
    public BookCatalogue searchBookDAO(long isbnCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Searching for book with ISBN Code: " + isbnCode);
            Criteria criteria = session.createCriteria(BookCatalogue.class)
                    .add(Restrictions.eq("isbnCode", isbnCode));
            return (BookCatalogue) criteria.uniqueResult();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Searching can't be done", e);
        }
    }

    // search if a book is issued
    /**
     * This method searches if a book is issued book or not.
     *
     * @param isbnCode the isbn code
     * @return the integer
     */
    @Transactional
    public Integer searchIssuedBookDAO(long isbnCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Searching if book with ISBN Code: " + isbnCode
                    + " is issued or not.");
            List<BookRentalLog> books = session.createQuery(
                    "from BookRentalLog where isbn_code='" + isbnCode
                            + "' AND bookshelf=true").list();
            return books.size();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Can't search if book issued or not", e);
        }
    }

    // delete book
    /**
     * Delete book dao.
     *
     * @param isbnCode the isbn code
     */
    @Transactional
    public void deleteBookDAO(long isbnCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Deleting book with ISBN Code:" + isbnCode
                    + "from the database.");
            BookCatalogue book = (BookCatalogue) session.get(
                    BookCatalogue.class, isbnCode);
            book.setAvailibility(false);
            session.update(book);
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Book can't be deleted", e);
        }
    }

    // add Book
    /**
     * Adds the book dao.
     *
     * @param book the book
     */
    @Transactional
    public void addBookDAO(BookCatalogue book) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Adding a new book in database with ISBN Code: "
                    + book.getIsbnCode());
            session.persist(book);
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Book can't be added", e);
        }
    }

    // users with active subscriptions
    /**
     * This method lists all the current active subscribers from the database.
     *
     * @return the list
     */
    @Transactional
    public List<SubscriptionLog> activeSubscriptionsDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the current active subscribers");
            return session.createQuery(
                    "from SubscriptionLog where subscriptionPlanStatus='"
                            + Status.ACTIVE + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // Subscription Plans list
    /**
     * This method lists all the subscription plans from the database.
     *
     * @return the list
     */
    @Transactional
    public List<SubscriptionPlans> subscriptionPlansDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the subscription plans from the database.");
            return session.createQuery("from SubscriptionPlans").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // pending delivery list
    /**
     * This method lists all the requests with pending delivery status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> pendingDeliveryDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with pending delivery status");
            return session.createQuery(
                    "from BookRentalLog where deliveryStatus='"
                            + BookStatus.PENDING + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // pending return list
    /**
     * This method lists all the requests with pending return status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> pendingReturnDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with pending return status");
            return session.createQuery(
                    "from BookRentalLog where returnStatus='"
                            + BookStatus.PENDING + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12 + e);
            throw new DAOException(STRING2, e);
        }
    }

    // cancelled delivery list
    /**
     * This method lists all the requests with canceled delivery status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> cancelledDeliveryDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with canceled delivery status");
            return session.createQuery(
                    "from BookRentalLog where deliveryStatus='"
                            + BookStatus.CANCELLED + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // cancelled return list
    /**
     * This method lists all the requests with canceled return status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> cancelledReturnDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with canceled return status");
            return session.createQuery(
                    "from BookRentalLog where returnStatus='"
                            + BookStatus.CANCELLED + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // closed delivery list
    /**
     * This method lists all the requests with closed delivery status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> closedDeliveryDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with closed delivery status");
            return session.createQuery(
                    "from BookRentalLog where (deliveryStatus='"
                            + BookStatus.CLOSED + "' and (not returnStatus='"
                            + BookStatus.CLOSED + STRING8).list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // closed return list
    /**
     * This method lists all the requests with closed return status.
     *
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> closedReturnDAO() {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the requests with closed return status");
            return session.createQuery(
                    "from BookRentalLog where returnStatus='"
                            + BookStatus.CLOSED + "'").list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // book delivered
    /**
     * This method changes delivery status pending to closed for a book request.
     *
     * @param transactionId the transaction id
     */
    @Transactional
    public void bookDeliveredDAO(long transactionId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Changing delivery status to closed from pending for request with transaction id: "
                    + transactionId);
            BookRentalLog transaction = (BookRentalLog) session.get(
                    BookRentalLog.class, transactionId);
            transaction.setDeliveryStatus(BookStatus.CLOSED);
            session.update(transaction);
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Status can't be changed", e);
        }
    }

    // book returned
    /**
     * This method changes return status pending to closed for a book request.
     * 
     * @param transactionId the transaction id
     */
    @Transactional
    public void bookReturnedDAO(long transactionId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Changing return status to closed from pending for request with transaction id: "
                    + transactionId);
            BookRentalLog transaction = (BookRentalLog) session.get(
                    BookRentalLog.class, transactionId);
            transaction.setReturnStatus(BookStatus.CLOSED);
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Timestamp returnDate = new Timestamp(stamp.getTime());
            transaction.setReturnDate(returnDate);
            transaction.setBookshelf(false);
            session.update(transaction);
            long isbnCode1 = transaction.getBookCatalogue().getIsbnCode();
            BookCatalogue book = (BookCatalogue) session.get(
                    BookCatalogue.class, isbnCode1);
            book.setNoOfCopiesAvailable(book.getNoOfCopiesAvailable() + 1);
            session.update(book);
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Can't change status", e);
        }
    }

    // Edit subscription plans xml file
    /**
     * Edits the subscription plans in the database.
     *
     * @param myPath the my path
     */
    @Transactional
    public void editPlansDAO(String myPath) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Updating and Adding scbscription plans through a XML file.");
            Query editQuery = session
                    .createSQLQuery("LOAD XML LOCAL INFILE '"
                            + myPath
                            + "' REPLACE INTO TABLE subscription_plans ROWS IDENTIFIED BY '<subscription_plan>'");
            editQuery.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException("Plans can't be changed", e);
        }
    }

    // For PDF from,to
    /**
     * This method lists all the book requests in a certain time period.
     *
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> listBooks1(Timestamp from, Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info(STRING6 + from + STRING4
                    + to);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1);
            criteria.add(Restrictions.between(STRING3, from,
                    to));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // for PDF title,from,to
    /**
     * This method lists all the requests for books with a certain title in a certain time period.
     *
     * @param title the title
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> listBooks2(String title, Timestamp from,
            Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info(STRING6 + from + STRING4
                    + to + " of title: " + title);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1);
            criteria.createCriteria(STRING7, STRING5);
            criteria.add(Restrictions.and(
                    Restrictions.between(STRING3, from, to),
                    Restrictions.eq("book.title", title)));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // for PDF author,from,to
    /**
     * This method lists all the requests for books of a certain author in a certain time period.
     *
     * @param author the author
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> listBooks3(String author, Timestamp from,
            Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info(STRING6 + from + STRING4
                    + to + " of author: " + author);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1);
            criteria.createCriteria(STRING7, STRING5);
            criteria.add(Restrictions.and(
                    Restrictions.between(STRING3, from, to),
                    Restrictions.eq("book.author", author)));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // for PDF category,from,to
    /**
     * This method lists all the requests for books of a certain category in a certain time period.
     *
     * @param category the category
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<BookRentalLog> listBooks4(String category, Timestamp from,
            Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info(STRING6 + from + STRING4
                    + to + " of category: " + category);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1);
            criteria.createCriteria(STRING7, STRING5);
            criteria.add(Restrictions.and(
                    Restrictions.between(STRING3, from, to),
                    Restrictions.eq("book.category", category)));
            return criteria.list();
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // for summary book details from,to
    /**
     * This method lists all the book requests in a certain time period.
     *
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<BookCatalogue> listBooks5(Timestamp from, Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info(STRING6 + from + STRING4
                    + to);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1).add(
                    Restrictions.between(STRING3, from, to));
            criteria.createCriteria(STRING7, STRING5);
            criteria.setProjection(Projections.distinct(Projections
                    .property("book.isbnCode")));
            List list1 = criteria.list();
            List<BookCatalogue> list = new ArrayList();
            ListIterator itr = list1.listIterator();
            while (itr.hasNext()) {
                Criteria criteria1 = session
                        .createCriteria(BookCatalogue.class).add(
                                Restrictions.eq("isbnCode", itr.next()));
                list.add((BookCatalogue) criteria1.uniqueResult());
            }
            return list;
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // for summary count from,to
    /**
     * This method lists all the requests for books of a certain category in a certain time period with count.
     *
     * @param from the from
     * @param to the to
     * @return the list
     */
    @Transactional
    public List<Long> listBooks6(Timestamp from, Timestamp to) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Listing all the book requests with count from: "
                    + from + STRING4 + to);
            Criteria criteria = session.createCriteria(BookRentalLog.class,
                    STRING1).add(
                    Restrictions.between(STRING3, from, to));
            criteria.createCriteria(STRING7, STRING5);
            criteria.setProjection(Projections.distinct(Projections
                    .property("book.isbnCode")));
            List list1 = criteria.list();
            long k = 0;
            List list = new ArrayList();
            ListIterator itr1 = list1.listIterator();
            while (itr1.hasNext()) {
                k = (Long) itr1.next();
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (deliveryStatus='"
                                + BookStatus.PENDING + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (deliveryStatus='"
                                + BookStatus.CANCELLED + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (deliveryStatus='"
                                + BookStatus.CLOSED + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (returnStatus='"
                                + BookStatus.PENDING + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (returnStatus='"
                                + BookStatus.CANCELLED + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
                list.add(session.createQuery(
                        STRING10
                                + k + "' and (returnStatus='"
                                + BookStatus.CLOSED + STRING11
                                + from + STRING13 + to + STRING8)
                        .uniqueResult());
            }
            return list;
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12+ e);
            throw new DAOException(STRING2, e);
        }
    }

    // add/update/delete books csv
    /**
     * This method update and add books in the database through CSV file upload.
     *
     * @param books the books
     */
    @Transactional
    public void bookCSVDAO(List<String> books) {
        try {
            Session session = sessionFactory.getCurrentSession();
            LOGGER.info("Adding and updating books with CSV file upload");
            ListIterator<String> itr = books.listIterator();
            while (itr.hasNext()) {
                String book = itr.next();
                Query change = session.createSQLQuery(
                        "CALL addBookCSV (:stringBook)").setParameter(
                        "stringBook", book);
                change.list();
            }
        } catch (Exception e) {
            LOGGER.error(STRING9 + e.getMessage() + STRING12 + e);
        }
    }

}