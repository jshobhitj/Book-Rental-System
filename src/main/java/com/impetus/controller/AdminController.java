package com.impetus.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.service.AdminService;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController contains functionalities related to Admin Role such as adding a new book, changing status 
 * for user pending requests, etc.
 */
@Controller
@SuppressWarnings({ "rawtypes" })
public class AdminController {
    
    /** The admin service. */
    @Autowired
    private AdminService adminService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(AdminController.class);

    // book catalogue page
    /**
     * Book catalogue. This function lists all the books with its details from the database in a table.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminbookCatalogueAdmin")
    public String bookCatalogue(Model model) {
        try {
			LOGGER.info("Loading Admin book catalogue page.");
			List<BookCatalogue> books = adminService.listBooksService();
			List distinctCategory = adminService.listCategoryService();
			model.addAttribute("books", books);
			model.addAttribute("distinctCategory", distinctCategory);
			return "admin/bookCatalogueAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // Active subscription page
    /**
     * User list.This lists list of active subscribers at present.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminAdminUserList")
    public String userList(Model model) {
        try {
			LOGGER.info("Loading Admin active subscription plans page.");
			List<SubscriptionLog> subscriptions = adminService
					.activeSubscriptionsService();
			model.addAttribute("subscriptions", subscriptions);
			return "admin/activeSubscriptionsListAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // user Pending requests page
    /**
     * User pending requests. This lists list of pending delivery and pending return requests of active subscribers.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminAdminUserPendingRequests")
    public String userPendingRequests(Model model) {
        try {
			LOGGER.info("Loading Admin pending request page.");
			List<BookRentalLog> pendingDelivery = adminService
					.pendingDeliveryService();
			List<BookRentalLog> pendingReturn = adminService
					.pendingReturnService();
			model.addAttribute("pendingDelivery", pendingDelivery);
			model.addAttribute("pendingReturn", pendingReturn);
			return "admin/userRequestsPendingAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // user Cancelled requests page
    /**
     * User cancelled requests. This lists all the cancelled delivery and return requests of users.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminAdminUserCancelledRequests")
    public String userCancelledRequests(Model model) {
        try {
			LOGGER.info("Loading Admin cancelled request page.");
			List<BookRentalLog> cancelledDelivery = adminService
					.cancelledDeliveryService();
			List<BookRentalLog> cancelledReturn = adminService
					.cancelledReturnService();
			model.addAttribute("cancelledDelivery", cancelledDelivery);
			model.addAttribute("cancelledReturn", cancelledReturn);
			return "admin/userRequestsCancelledAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // user Closed requests page
    /**
     * User closed requests. This lists all the closed delivery and return requests of users.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminAdminUserClosedRequests")
    public String userClosedRequests(Model model) {
        try {
			LOGGER.info("Loading Admin closed request page.");
			List<BookRentalLog> closedDelivery = adminService
					.closedDeliveryService();
			List<BookRentalLog> closedReturn = adminService
					.closedReturnService();
			model.addAttribute("closedDelivery", closedDelivery);
			model.addAttribute("closedReturn", closedReturn);
			return "admin/userRequestsClosedAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // subscription plans list page
    /**
     * Subscription plans. This lists all the subscription plans (both active and expired).
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminsubscriptionPlansAdmin")
    public String subscriptionPlans(Model model) {
        try {
			LOGGER.info("Loading Admin subscription plans page.");
			List<SubscriptionPlans> subscriptions = adminService
					.subscriptionPlansService();
			model.addAttribute("subscriptions", subscriptions);
			return "admin/subscriptionPlansAdmin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    // update book
    /**
     * Update book. This method is for updating all book details such as title, author, category etc. except for ISBN Code,
     * publisher and published date. 
     *
     * @param request the request
     * @param response the response
     * @param isbnCode the isbn code
     * @param title the title
     * @param author the author
     * @param category the category
     * @param image the image
     * @param bookDescription the book description
     * @param noOfCopiesAvailable the no of copies available
     * @param availibility the availibility
     * @return the book catalogue
     */
    @RequestMapping(value = "/adminupdateBookAdmin")
    public @ResponseBody
    BookCatalogue updateBook(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("isbn_code") long isbnCode,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("image") String image,
            @RequestParam("book_description") String bookDescription,
            @RequestParam("no_of_copies_available") Integer noOfCopiesAvailable,
            @RequestParam("availibility") boolean availibility) {
        LOGGER.info("Updating book with ISBN Code: " + isbnCode);
        adminService.updateBookService(isbnCode, title, author, category,
                image, bookDescription, noOfCopiesAvailable, availibility);
        return adminService.getBook(isbnCode);
    }

    // search if a book is issued
    /**
     * Search issued book service. This method checks if a book is issued or not so that admin can't delete a book
     * which at present is issued.
     *
     * @param request the request
     * @param response the response
     * @param isbnCode the isbn code
     * @return the string
     */
    @RequestMapping(value = "/adminsearchIfBookIssued", method = RequestMethod.GET)
    public @ResponseBody
    String searchIssuedBookService(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("isbn_code") long isbnCode) {
        LOGGER.info("Searching if a book with ISBN Code: " + isbnCode
                + " is issued");
        return Integer.toString(adminService.searchIssuedBookService(isbnCode));
    }

    // delete book
    /**
     * Delete book. This method is for deleting the books(i.e. change book availability from true to false).
     *
     * @param request the request
     * @param response the response
     * @param isbnCode the isbn code
     * @return the book catalogue
     */
    @RequestMapping(value = "/admindeleteBook", method = RequestMethod.POST)
    public @ResponseBody
    BookCatalogue deleteBook(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("isbn_code1") long isbnCode) {
        adminService.deleteBookService(isbnCode);
        LOGGER.info("Loading Admin book catalogue page.");
        return adminService.getBook(isbnCode);
    }

    // add Book
    /**
     * Adds the book.
     *
     * @param request the request
     * @param response the response
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
     * @return the string
     */
    @RequestMapping(value = "/adminaddBookAdmin", method = RequestMethod.POST)
    public @ResponseBody
    String addBook(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("isbn_code2") long isbnCode,
            @RequestParam("title2") String title,
            @RequestParam("author2") String author,
            @RequestParam("category2") String category,
            @RequestParam("image2") String image,
            @RequestParam("book_description2") String bookDescription,
            @RequestParam("publisher2") String publisher,
            @RequestParam("published_date2") Date publishedDate,
            @RequestParam("no_of_pages2") Integer noOfPages,
            @RequestParam("no_of_copies_available2") Integer noOfCopiesAvailable) {
        LOGGER.info("Adding book with ISBN Code: " + isbnCode);
        adminService.addBookService(isbnCode, title, author, category, image,
                bookDescription, publisher, publishedDate, noOfPages,
                noOfCopiesAvailable);
        return "";
    }

    // book delivered
    /**
     * Book delivered. This method changes the pending delivery status to closed when a book is delivered.
     *
     * @param request the request
     * @param response the response
     * @param transactionId the transaction id
     * @return the string
     */
    @RequestMapping(value = "/adminbookDelivered", method = RequestMethod.GET)
    public @ResponseBody
    String bookDelivered(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("transaction_id") long transactionId) {
        LOGGER.info("Done book delivery for transaction: " + transactionId);
        adminService.bookDeliveredService(transactionId);
        return "";
    }

    // book returned
    /**
     * Book returned.This method changes the pending return status to closed when a book is returned by the user.
     *
     * @param request the request
     * @param response the response
     * @param transactionId the transaction id
     * @return the string
     */
    @RequestMapping(value = "/adminbookReturned", method = RequestMethod.GET)
    public @ResponseBody
    String bookReturned(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("transaction_id") long transactionId) {
        LOGGER.info("Book returned for transaction: " + transactionId);
        adminService.bookReturnedService(transactionId);
        return "";
    }

    // check if book already exist
    /**
     * Available. This method checks whether a book already exists in database or not.
     *
     * @param request the request
     * @param response the response
     * @param isbnCode the isbn code
     * @return the string
     */
    @RequestMapping(value = "/adminavailableBookStatus", method = RequestMethod.POST)
    public @ResponseBody
    String available(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("isbn_code") long isbnCode) {
        LOGGER.info("Checking if book already exist for ISBN Code: " + isbnCode);
        boolean status = adminService.availableService(isbnCode);
        if (status) {
            return "Not";
        } else {
            return "";
        }
    }

    // Show Results
    /**
     * Show results. This method lists list of all authors or category or titles depending on admin requests.
     *
     * @param request the request
     * @param response the response
     * @param parameter the parameter
     * @return the list
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/adminShowResults", method = RequestMethod.GET)
    public @ResponseBody
    List showResults(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("parameter") String parameter) {
        LOGGER.info("for pdf generation parameter: " + parameter);
        List list = new ArrayList();
        if (parameter.equals("title")) {
            list.addAll(adminService.listTitleService());
        }
        if (parameter.equals("author")) {
            list.addAll(adminService.listAuthorService());
        }
        if (parameter.equals("category")) {
            list.addAll(adminService.listCategoryService());
        }
        return list;
    }

    // Generate PDF
    /**
     * Generate pdf. This method generates a pdf result table for admin requests like for this author or category how many
     * books are issued from some date to some other date. 
     *
     * @param parameter the parameter
     * @param value the value
     * @param from the from
     * @param to the to
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminGeneratePDF", method = RequestMethod.GET)
    public String generatePDF(@RequestParam("parameter") String parameter,
            @RequestParam("value") String value,
            @RequestParam("from") String from, @RequestParam("to") String to,
            Model model) {
        LOGGER.info("for PDF Generation parameter :" + parameter + " value: "
                + value + " from: " + from + " to: " + to);
        String input = "MM/dd/yyyy";

        java.util.Date date;
        try {
            date = (new SimpleDateFormat(input)).parse(from);
            Timestamp from1 = new Timestamp(date.getTime());

            java.util.Date date1 = (new SimpleDateFormat(input)).parse(to);
            Timestamp to1 = new Timestamp(date1.getTime());
            List<BookRentalLog> listBooks = adminService.listBooks(parameter,
                    value, from1, to1);

            model.addAttribute("listBooks", listBooks);
            model.addAttribute("from", from1);
            model.addAttribute("to", to1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            LOGGER.info(e);
        }

        return "pdfView";
    }

    // Generate PDF
    /**
     * Generate pdf1. This method generates a summary table containing all the information of book requests in some time period.
     *
     * @param from2 the from2
     * @param to2 the to2
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/adminGeneratePDF1", method = RequestMethod.GET)
    public String generatePDF1(@RequestParam("from") String from2,
            @RequestParam("to") String to2, Model model) {
        LOGGER.info("for summary PDF Generation from: " + from2 + " to: " + to2);
        String input = "MM/dd/yyyy";

        java.util.Date date;
        try {
            date = (new SimpleDateFormat(input)).parse(from2);
            Timestamp from3 = new Timestamp(date.getTime());

            java.util.Date date1 = (new SimpleDateFormat(input)).parse(to2);
            Timestamp to3 = new Timestamp(date1.getTime());

            List<BookCatalogue> listBooks = adminService.listBooks1(from3, to3);
            List listBooksCount = adminService.listBooksCount(from3, to3);

            model.addAttribute("listBooks", listBooks);
            model.addAttribute("from", from3);
            model.addAttribute("to", to3);
            model.addAttribute("listBooksCount", listBooksCount);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            LOGGER.info(e);
        }
        return "pdfView1";
    }

    // Add/Update/Delete Subscription plans
    /**
     * Upload file handler. this method is for uploading a xml file on server so that we can alter subscription plan details or
     * can add a new one.
     *
     * @param myName the my name
     * @param myXmlfile the my xmlfile
     * @return the string
     */
    @RequestMapping(value = "/adminUploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("myName") String myName,
            @RequestParam("myXmlfile") MultipartFile myXmlfile) {
        LOGGER.info("XML file uplopad and subscription plan update.");
        if (!myXmlfile.isEmpty()) {
            try {
                byte[] bytes = myXmlfile.getBytes();

                // Creating the directory to store file
                String myRootPath = System.getProperty("catalina.home");
                File dir = new File(myRootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File myServerFile = new File(dir.getAbsolutePath()
                        + File.separator + myName + ".xml");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(myServerFile));
                stream.write(bytes);
                stream.close();
                LOGGER.info("Server File Location="
                        + myServerFile.getAbsolutePath());
                adminService.editPlansService(myServerFile.getAbsolutePath()
                        .replace('\\', '/'));

                return "Subscription Plans Are Changed";
            } catch (Exception e) {
                return "You failed to upload " + myName + " => "
                        + e.getMessage();
            }
        } else {
            return "You failed to upload " + myName
                    + " because the file was empty.";
        }
    }
}
