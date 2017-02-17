package com.impetus.utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;
import com.impetus.service.AdminService;
import com.impetus.service.CSVReadService;
import com.impetus.service.IndexService;
import com.impetus.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class ScheduledTasks.
 */
public class ScheduledTasks {
    
    /** The csv read service. */
    @Autowired
    private CSVReadService csvReadService;
    
    /** The admin service. */
    @Autowired
    private AdminService adminService;
    
    /** The user service. */
    @Autowired
    private UserService userService;
    
    /** The index service. */
    @Autowired
    private IndexService indexService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(ScheduledTasks.class);

    // add/update/delete books CSV
    /**
     * Adds and updates book using CSV service method.
     */
    @Scheduled(cron = "0 0 23 ? * *")
    public void bookAddCSVServiceMethod() {
        LOGGER.info("CSV feed file service: " + new java.util.Date());
        List<String> books = csvReadService.convertCsvToJava();
        adminService.bookCSVService(books);
    }

    // Sending mails for end of subscription plans
    /**
     * Subscription end email.
     *
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    @Scheduled(cron = "0 15 10 ? * *")
    public void subscriptionEndEmail() {
        LOGGER.info("sending mails for ending of subscription plans:"
                + new java.util.Date());
        List<SubscriptionLog> subscriptions = adminService
                .activeSubscriptionsService();
        ListIterator<SubscriptionLog> itr = subscriptions.listIterator();
        SubscriptionLog sub = null;

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Timestamp todayTimestamp = new Timestamp(stamp.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);

        // one day
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Timestamp oneDayTimestamp = new Timestamp(System.currentTimeMillis());
        oneDayTimestamp = new Timestamp(cal.getTime().getTime());
        String oneDayDate = new SimpleDateFormat("yyyy-MM-dd")
                .format(oneDayTimestamp.getTime());

        // one week
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Timestamp oneWeekTimestamp = new Timestamp(System.currentTimeMillis());
        oneWeekTimestamp = new Timestamp(cal.getTime().getTime());
        String oneWeekDate = new SimpleDateFormat("yyyy-MM-dd")
                .format(oneWeekTimestamp.getTime());

        // one month
        cal.add(Calendar.DAY_OF_WEEK, 24);
        Timestamp oneMonthTimestamp = new Timestamp(System.currentTimeMillis());
        oneMonthTimestamp = new Timestamp(cal.getTime().getTime());
        String oneMonthDate = new SimpleDateFormat("yyyy-MM-dd")
                .format(oneMonthTimestamp.getTime());

        List<String> oneDay = new ArrayList<String>();
        List<String> oneWeek = new ArrayList<String>();
        List<String> oneMonth = new ArrayList<String>();

        while (itr.hasNext()) {
            sub = itr.next();
            Date endDate = new Date(sub.getEndDate().getTime());
            if (endDate.equals(oneDayDate)) {
                // one day mail
                oneDay.add(sub.getUserDetails().getUserId());
            }
            if (endDate.equals(oneWeekDate)) {
                // one week mail
                oneWeek.add(sub.getUserDetails().getUserId());
            }
            if (endDate.equals(oneMonthDate)) {
                // one month mail
                oneMonth.add(sub.getUserDetails().getUserId());
            }
        }
        
        try {
            JavaEmail.main1(oneDay, "One Day");
        } catch (Exception e) {
            LOGGER.error("Exception in Scheduler: " + e.getMessage()
                    + "\nCaused By:\n" + e);
        } 
        try {
            JavaEmail.main1(oneWeek, "One Week");
        } catch (Exception e) {
            LOGGER.error("Exception in Scheduler: " + e.getMessage()
                    + "\nCaused By:\n" + e);
        } 
        try {
            JavaEmail.main1(oneMonth, "One Month");
        } catch (Exception e) {
            LOGGER.error("Exception in Scheduler: " + e.getMessage()
                    + "\nCaused By:\n" + e);
        } 
    }

    // Recommendations
    /**
     * Building recommendations for user based on their book issued history and wishlist.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Scheduled(cron = "0 45 23 ? * *")
    public void recommendations() {
        LOGGER.info("Generating recommendations: " + new java.util.Date());
        List<SubscriptionLog> users = adminService.activeSubscriptionsService();
        ListIterator<SubscriptionLog> itrRecom = users.listIterator();
        while (itrRecom.hasNext()) {
            Set<String> uniqueCategory = new HashSet();
            Set<String> uniqueAuthor = new HashSet();
            SubscriptionLog sub = itrRecom.next();
            UserDetails user = sub.getUserDetails();
            LOGGER.info("Generating recommendations: " + new java.util.Date()
                    + " for user: " + user.getUserId());
            List<BookRentalLog> bookHistory = new ArrayList<BookRentalLog>();
            List<BookCatalogue> userBooks = new ArrayList<BookCatalogue>();
            List<Wishlist> userWishes = new ArrayList<Wishlist>();
            List<BookCatalogue> userRecomBooks = new ArrayList<BookCatalogue>();
            bookHistory
                    .addAll(userService.userHistoryService(user.getUserId()));
            userWishes
                    .addAll(userService.showWishlistService(user.getUserId()));
            ListIterator<BookRentalLog> itruserRecom = bookHistory
                    .listIterator();
            ListIterator<Wishlist> itruserWishRecom = userWishes.listIterator();
            while (itruserRecom.hasNext()) {
                BookRentalLog book = itruserRecom.next();
                uniqueCategory.add(book.getBookCatalogue().getCategory());
                uniqueAuthor.add(book.getBookCatalogue().getAuthor());
                userBooks.add(book.getBookCatalogue());
            }
            while (itruserWishRecom.hasNext()) {
                Wishlist bookWish = itruserWishRecom.next();
                uniqueCategory.add(bookWish.getBookCatalogue().getCategory());
                uniqueAuthor.add(bookWish.getBookCatalogue().getAuthor());
            }
            Iterator<String> itrUniqueCategory = uniqueCategory.iterator();
            Iterator<String> itrUniqueAuthor = uniqueAuthor.iterator();
            while (itrUniqueCategory.hasNext()) {
                userRecomBooks.addAll(userService
                        .gettingCategoryBooksService(itrUniqueCategory.next()));
            }
            while (itrUniqueAuthor.hasNext()) {
                userRecomBooks.addAll(userService
                        .gettingAuthorBooksService(itrUniqueAuthor.next()));
            }
            Collections.sort(userRecomBooks, new MyRecomComp());
            Iterator<BookCatalogue> itrUserRecomBooks = userRecomBooks
                    .iterator();
            Set<Long> uniqueRecomBooks = new HashSet<Long>();
            while (itrUserRecomBooks.hasNext()) {
                uniqueRecomBooks.add(itrUserRecomBooks.next().getIsbnCode());
            }
            Iterator<BookCatalogue> itrUserBooks = userBooks.iterator();
            while (itrUserBooks.hasNext()) {
                uniqueRecomBooks.remove(itrUserBooks.next().getIsbnCode());
            }
            if (uniqueRecomBooks.size() < 6) {
                List<BookCatalogue> bookRecent = indexService
                        .listNewReleaseService();
                ListIterator<BookCatalogue> itrbookRecent = bookRecent
                        .listIterator();
                Set<Long> uniqueNewBooks = new HashSet<Long>();
                while (itrbookRecent.hasNext()) {
                    uniqueNewBooks.add(itrbookRecent.next().getIsbnCode());
                }
                Iterator<BookCatalogue> itrUserBooks1 = userBooks.iterator();
                while (itrUserBooks1.hasNext()) {
                    uniqueNewBooks.remove(itrUserBooks1.next().getIsbnCode());
                }
                Set<Long> result = new HashSet<Long>();
                for (Long el : uniqueNewBooks) {
                    if (!uniqueRecomBooks.contains(el)) {
                        result.add(el);
                    }
                }
                int j = 6 - uniqueRecomBooks.size();
                Iterator<Long> itrResult = result.iterator();
                if (result.size() >= j) {
                    for (int l = 0; l < j; l++) {
                        uniqueRecomBooks.add(itrResult.next());
                    }
                } else {
                    for (int l = 0; l < result.size(); l++) {
                        uniqueRecomBooks.add(itrResult.next());
                    }
                }
            }
            Iterator<Long> itrUniqueRecomBooks = uniqueRecomBooks.iterator();
            StringBuilder bookString = new StringBuilder();
            if (uniqueRecomBooks.size() >= 6) {
                for (int i = 0; i <= 5; i++) {
                    bookString.append(itrUniqueRecomBooks.next());
                    if (i != 5) {
                        bookString.append(",");
                    }
                }
            }
            if (uniqueRecomBooks.size() < 6 && itrUniqueRecomBooks.hasNext()) {
                for (int i = 0; i <= uniqueRecomBooks.size(); i++) {
                    bookString.append(itrUniqueRecomBooks.next());
                    if (i != uniqueRecomBooks.size() - 1) {
                        bookString.append(",");
                    }
                }
            }
            user.setRecommended(bookString.toString());
            LOGGER.info("Recommendations: " + bookString + " for user: "
                    + user.getUserId());
            userService.updateUserRecomService(user);
        }
    }
}

class MyRecomComp implements Comparator<BookCatalogue> {

    @Override
    public int compare(BookCatalogue e1, BookCatalogue e2) {
        if (e1.getRecommentCount() < e2.getRecommentCount()) {
            return 1;
        } else {
            return -1;
        }
    }
}
