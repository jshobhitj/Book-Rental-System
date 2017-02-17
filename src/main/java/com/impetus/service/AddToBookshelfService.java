package com.impetus.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.AddToBookshelfDAO;
import com.impetus.dao.UserDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.BookStatus;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class AddToBookshelfService.
 */
@Service
public class AddToBookshelfService {
    
    /** The add to bookshelf dao. */
    @Autowired
    private AddToBookshelfDAO addToBookshelfDAO;
    
    /** The user dao. */
    @Autowired
    private UserDAO userDAO;

    /**
     * Sets the adds the to bookshelf dao.
     *
     * @param addToBookshelfDAO the new adds the to bookshelf dao
     */
    public void setAddToBookshelfDAO(AddToBookshelfDAO addToBookshelfDAO) {
        this.addToBookshelfDAO = addToBookshelfDAO;
    }

    /**
     * Sets the user dao.
     *
     * @param userDAO the new user dao
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Adds the to bookshelf.
     *
     * @param isbnCode the isbn code
     * @param userId the user id
     * @param requestedAddress the requested address
     * @return the string
     */
    public String addToBookshelf(long isbnCode, String userId,String requestedAddress) {
        try {
            SubscriptionLog subscription = userDAO.subscriptionPlanDAO(userId);
            Integer size = userDAO.noOfBooksIssued(userId);
            BookCatalogue bookDetails = userDAO.bookDetails(isbnCode);
            Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
            Timestamp ts = new Timestamp(stamp1.getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(ts);
            cal1.add(Calendar.DAY_OF_WEEK, 10);
            ts = new Timestamp(cal1.getTime().getTime());
            // subscription active check
            String message = "";
            if (subscription == null) {
                message = "Your subscription plan is not Active";
            } else {
                // subscription will expire in 10 days
                if (ts.after(subscription.getEndDate())) {
                    message = "Book Can't be Issued!! Your subscription will expire in 10 Days.";
                } else {
                    // book availability check
                    if (bookDetails.getNoOfCopiesAvailable() == 0) {
                        message = "Not Available";
                    } else {
                        // no of books limit exceeded check
                        if (size >= subscription.getNoOfBooks()) {
                            message = "Book Issue Limit is Exceeded!! Check Your Subscription Plan.";
                        } else {
                            // book already issued check
                            if (userDAO.statusIssued(isbnCode, userId)) {
                                message = "Already Issued!!";
                            } else {
                                BookRentalLog bookRequest = new BookRentalLog();
                                BookCatalogue book = new BookCatalogue();
                                book.setIsbnCode(isbnCode);
                                bookRequest.setBookCatalogue(book);
                                UserDetails user = new UserDetails();
                                user.setUserId(userId);
                                bookRequest.setUserDetails(user);

                                Timestamp stamp = new Timestamp(
                                        System.currentTimeMillis());
                                Timestamp issueDate = new Timestamp(
                                        stamp.getTime());

                                int month = 1;

                                bookRequest.setIssueDate(issueDate);

                                Calendar cal = Calendar.getInstance();
                                cal.setTimeInMillis(issueDate.getTime());
                                cal.add(Calendar.MONTH, month);
                                Timestamp returnDate = new Timestamp(cal
                                        .getTime().getTime());

                                bookRequest.setReturnDate(returnDate);

                                bookRequest
                                        .setDeliveryStatus(BookStatus.PENDING);
                                bookRequest.setReturnStatus(BookStatus.NONE);
                                bookRequest
                                        .setRequestedAddress(requestedAddress);
                                bookRequest.setBookshelf(true);

                                addToBookshelfDAO
                                        .addToBookshelfDAO(bookRequest);
                                message = "Added to Bookshelf";
                            }
                        }
                    }
                }
            }return message;
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}