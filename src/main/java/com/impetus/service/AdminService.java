package com.impetus.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.AdminDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminService.
 */
@Service
@SuppressWarnings({ "rawtypes" })
public class AdminService {
    
    /** The admin dao. */
    @Autowired
    private AdminDAO adminDAO;
    
    /**
     * Sets the admin dao.
     *
     * @param adminDAO the new admin dao
     */
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    // list all books
    /**
     * List books service.
     *
     * @return the list
     */
    public List<BookCatalogue> listBooksService() {
        try {
            return adminDAO.listBooksDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // list all title
    /**
     * List title service.
     *
     * @return the list
     */
    public List listTitleService() {
        try {
            return adminDAO.listTitleDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // list all author
    /**
     * List author service.
     *
     * @return the list
     */
    public List listAuthorService() {
        try {
            return adminDAO.listAuthorDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // list all category
    /**
     * List category service.
     *
     * @return the list
     */
    public List listCategoryService() {
        try {
            return adminDAO.listCategoryDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // update book
    /**
     * Update book service.
     *
     * @param isbnCode the isbn code
     * @param title the title
     * @param author the author
     * @param category the category
     * @param image the image
     * @param bookDescription the book description
     * @param noOfCopiesAvailable the no of copies available
     * @param availibility the availibility
     */
    public void updateBookService(long isbnCode, String title, String author,
            String category, String image, String bookDescription,
            Integer noOfCopiesAvailable, boolean availibility) {
        try {
            BookCatalogue book = adminDAO.searchBookDAO(isbnCode);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setImage(image);
            book.setBookDescription(bookDescription);
            book.setNoOfCopiesAvailable(noOfCopiesAvailable);
            book.setAvailibility(availibility);
            adminDAO.updateBookDAO(book);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // search if a book is issued
    /**
     * Search issued book service.
     *
     * @param isbnCode the isbn code
     * @return the integer
     */
    public Integer searchIssuedBookService(long isbnCode) {
        try {
            return adminDAO.searchIssuedBookDAO(isbnCode);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // delete book
    /**
     * Delete book service.
     *
     * @param isbnCode the isbn code
     */
    public void deleteBookService(long isbnCode) {
        try {
            adminDAO.deleteBookDAO(isbnCode);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // add Book
    /**
     * Adds the book service.
     *
     * @param isbnCode the isbn code
     * @param title the title
     * @param author the author
     * @param category the category
     * @param image the image
     * @param bookDescription the book description
     * @param publisher the publisher
     * @param publishedDate the published date
     * @param noOfPages the no of pages
     * @param noOfCopiesAvailable the no of copies available
     */
    public void addBookService(long isbnCode, String title, String author,
            String category, String image, String bookDescription,
            String publisher, Date publishedDate, Integer noOfPages,
            Integer noOfCopiesAvailable) {
        try {
            BookCatalogue book = new BookCatalogue();
            book.setIsbnCode(isbnCode);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setImage(image);
            book.setBookDescription(bookDescription);
            book.setPublisher(publisher);
            book.setPublishedDate(publishedDate);
            book.setNoOfPages(noOfPages);
            book.setNoOfCopiesAvailable(noOfCopiesAvailable);
            book.setAvailibility(true);
            book.setRecommentCount(1);
            adminDAO.addBookDAO(book);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // users with active subscriptions
    /**
     * Active subscriptions service.
     *
     * @return the list
     */
    public List<SubscriptionLog> activeSubscriptionsService() {
        try {
            return adminDAO.activeSubscriptionsDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Subscription Plans List
    /**
     * Subscription plans service.
     *
     * @return the list
     */
    public List<SubscriptionPlans> subscriptionPlansService() {
        try {
            return adminDAO.subscriptionPlansDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // pending delivery list
    /**
     * Pending delivery service.
     *
     * @return the list
     */
    public List<BookRentalLog> pendingDeliveryService() {
        try {
            return adminDAO.pendingDeliveryDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // pending return list
    /**
     * Pending return service.
     *
     * @return the list
     */
    public List<BookRentalLog> pendingReturnService() {
        try {
            return adminDAO.pendingReturnDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // cancelled delivery list
    /**
     * Cancelled delivery service.
     *
     * @return the list
     */
    public List<BookRentalLog> cancelledDeliveryService() {
        try {
            return adminDAO.cancelledDeliveryDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // cancelled return list
    /**
     * Cancelled return service.
     *
     * @return the list
     */
    public List<BookRentalLog> cancelledReturnService() {
        try {
            return adminDAO.cancelledReturnDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // closed delivery list
    /**
     * Closed delivery service.
     *
     * @return the list
     */
    public List<BookRentalLog> closedDeliveryService() {
        try {
            return adminDAO.closedDeliveryDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // closed return list
    /**
     * Closed return service.
     *
     * @return the list
     */
    public List<BookRentalLog> closedReturnService() {
        try {
            return adminDAO.closedReturnDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // book delivered
    /**
     * Book delivered service.
     *
     * @param transactionId the transaction id
     */
    public void bookDeliveredService(long transactionId) {
        try {
            adminDAO.bookDeliveredDAO(transactionId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // book returned
    /**
     * Book returned service.
     *
     * @param transactionId the transaction id
     */
    public void bookReturnedService(long transactionId) {
        try {
            adminDAO.bookReturnedDAO(transactionId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // check if book already exist
    /**
     * Available service.
     *
     * @param isbnCode the isbn code
     * @return true, if successful
     */
    public boolean availableService(long isbnCode) {
        try {
            BookCatalogue book = adminDAO.searchBookDAO(isbnCode);
            return (!(book == null));
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // get book details
    /**
     * Gets the book.
     *
     * @param isbnCode the isbn code
     * @return the book
     */
    public BookCatalogue getBook(long isbnCode) {
        try {
            return adminDAO.searchBookDAO(isbnCode);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Edit subscription plans xml file
    /**
     * Edits the plans service.
     *
     * @param myPath the my path
     */
    public void editPlansService(String myPath) {
        try {
            adminDAO.editPlansDAO(myPath);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // For PDF
    /**
     * List books.
     *
     * @param parameter the parameter
     * @param value the value
     * @param from the from
     * @param to the to
     * @return the list
     */
    public List<BookRentalLog> listBooks(String parameter, String value,
            Timestamp from, Timestamp to) {
        try {
            List<BookRentalLog> list = new ArrayList<BookRentalLog>();
            if (parameter.equals("all")) {
                list.addAll(adminDAO.listBooks1(from, to));
            }
            if (parameter.equals("title")) {
                list.addAll(adminDAO.listBooks2(value, from, to));
            }
            if (parameter.equals("author")) {
                list.addAll(adminDAO.listBooks3(value, from, to));
            }
            if (parameter.equals("category")) {
                list.addAll(adminDAO.listBooks4(value, from, to));
            }
            return list;
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // for summary
    /**
     * List books1.
     *
     * @param from the from
     * @param to the to
     * @return the list
     */
    public List<BookCatalogue> listBooks1(Timestamp from, Timestamp to) {
        try {
            return adminDAO.listBooks5(from, to);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // for summary
    /**
     * List books count.
     *
     * @param from the from
     * @param to the to
     * @return the list
     */
    public List listBooksCount(Timestamp from, Timestamp to) {
        try {
            return adminDAO.listBooks6(from, to);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // for add/update/delete books csv
    /**
     * Book csv service.
     *
     * @param books the books
     */
    public void bookCSVService(List<String> books) {
        try {
            adminDAO.bookCSVDAO(books);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}