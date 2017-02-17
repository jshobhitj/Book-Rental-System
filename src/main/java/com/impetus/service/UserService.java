package com.impetus.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.UserDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {
    
    /** The user dao. */
    @Autowired
    private UserDAO userDAO;

    /**
     * Sets the user dao.
     *
     * @param userDAO the new user dao
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // bookshelf
    /**
     * Bookshelf.
     *
     * @param userId the user id
     * @return the list
     */
    public List<BookRentalLog> bookshelf(String userId) {
        try {
            return userDAO.findAll(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // return book
    /**
     * Return book service.
     *
     * @param transactionId the transaction id
     * @param requestedAddress the requested address
     */
    public void returnBookService(long transactionId, String requestedAddress) {
        try {
            userDAO.returnBookDAO(transactionId, requestedAddress);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Cancel Return Request
    /**
     * Cancel return requests service.
     *
     * @param transactionId the transaction id
     * @param task the task
     * @param userId the user id
     * @return the string
     */
    public String cancelReturnRequestsService(long transactionId, boolean task,
            String userId) {
        try {
            SubscriptionLog subscription = userDAO.subscriptionPlanDAO(userId);
            String message = "";
            Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
            Timestamp ts = new Timestamp(stamp1.getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(ts);
            cal1.add(Calendar.DAY_OF_WEEK, 15);
            ts = new Timestamp(cal1.getTime().getTime());
            if (ts.after(subscription.getEndDate())) {
                message = "Can't cancel return/Re-Issue request as your subscription will end in 15 Days..";
            } else {
                userDAO.cancelReturnRequestsDAO(transactionId, task);
                message = "";
            }
            return message;
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Cancel Delivery Request
    /**
     * Cancel delivery requests service.
     *
     * @param transactionId the transaction id
     */
    public void cancelDeliveryRequestsService(long transactionId) {
        try {
            userDAO.cancelDeliveryRequestsDAO(transactionId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // user subscription plan details
    /**
     * Subscription plan service.
     *
     * @param userId the user id
     * @return the subscription log
     */
    public SubscriptionLog subscriptionPlanService(String userId) {
        try {
            return userDAO.subscriptionPlanDAO(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // user book history
    /**
     * Book history service.
     *
     * @param user the user
     * @return the list
     */
    public List<BookRentalLog> bookHistoryService(String user) {
        try {
            return userDAO.bookHistoryDAO(user);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // user subscription history
    /**
     * Subscription history service.
     *
     * @param user the user
     * @return the list
     */
    public List<SubscriptionLog> subscriptionHistoryService(String user) {
        try {
            return userDAO.subscriptionHistoryDAO(user);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // add to Wishlist
    /**
     * Adds the to wishlist service.
     *
     * @param isbnCode the isbn code
     * @param userId the user id
     * @return the string
     */
    public String addToWishlistService(long isbnCode, String userId) {
        try {
            String message = "";
            if (userDAO.statusWishlistDAO(isbnCode, userId)) {
                message = "Already in Wishlist";
            } else {
                Wishlist wish = new Wishlist();
                BookCatalogue book = new BookCatalogue();
                UserDetails user = new UserDetails();
                user.setUserId(userId);
                book.setIsbnCode(isbnCode);
                wish.setUserDetails(user);
                wish.setBookCatalogue(book);
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                Timestamp wishDate = new Timestamp(stamp.getTime());
                wish.setWishDate(wishDate);
                userDAO.addToWishlistDAO(wish);
                message = "Added to Wishlist";
            }
            return message;
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // show wishlist
    /**
     * Show wishlist service.
     *
     * @param userId the user id
     * @return the list
     */
    public List<Wishlist> showWishlistService(String userId) {
        try {
            return userDAO.showWishlistDAO(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Remove from Wishlist
    /**
     * Removes the from wishlist service.
     *
     * @param sno the sno
     */
    public void removeFromWishlistService(long sno) {
        try {
            userDAO.removeFromWishlistDAO(sno);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // change subscription log status
    /**
     * Change subscription status service.
     *
     * @param userId the user id
     */
    public void changeSubscriptionStatusService(String userId) {
        try {
            SubscriptionLog subscription = userDAO.subscriptionPlanDAO(userId);
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Timestamp todayDate = new Timestamp(stamp.getTime());
            if (todayDate.after(subscription.getEndDate())) {
                subscription.setSubscriptionPlanStatus(Status.EXPIRED);
            }
            userDAO.changeSubscriptionStatusDAO(subscription);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // getting user history for recommendation
    /**
     * User history service.
     *
     * @param userId the user id
     * @return the list
     */
    public List<BookRentalLog> userHistoryService(String userId) {
        try {
            return userDAO.gettingUserHistory(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // getting category books for recommendation
    /**
     * Gets the ting category books service.
     *
     * @param category the category
     * @return the ting category books service
     */
    public List<BookCatalogue> gettingCategoryBooksService(String category) {
        try {
            return userDAO.gettingCategoryBooks(category);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // getting author books for recommendation
    /**
     * Gets the ting author books service.
     *
     * @param author the author
     * @return the ting author books service
     */
    public List<BookCatalogue> gettingAuthorBooksService(String author) {
        try {
            return userDAO.gettingAuthorBooks(author);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // update user recommendation
    /**
     * Update user recom service.
     *
     * @param user the user
     */
    public void updateUserRecomService(UserDetails user) {
        try {
            userDAO.updateUserRecomDAO(user);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // Recommended books list show
    /**
     * Recommended books.
     *
     * @param recom the recom
     * @return the list
     */
    public List<BookCatalogue> recommendedBooks(String recom) {
        try {
            List<String> books = Arrays.asList(recom.split("\\s*,\\s*"));
            List<BookCatalogue> bookList = new ArrayList<BookCatalogue>();
            ListIterator<String> itrBooks = books.listIterator();
            int i = 1;
            while (i <= books.size()) {
                bookList.add(userDAO.getTheBook(Long.valueOf(itrBooks.next())));
                i++;
            }
            return bookList;
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}
