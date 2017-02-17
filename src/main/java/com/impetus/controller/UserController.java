package com.impetus.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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



import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.BookStatus;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;
import com.impetus.service.AddToBookshelfService;
import com.impetus.service.IndexService;
import com.impetus.service.RegisterService;
import com.impetus.service.SearchBookService;
import com.impetus.service.SubscriptionPlansService;
import com.impetus.service.UserLoginService;
import com.impetus.service.UserService;
import com.impetus.utility.JavaEmail;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController. This class contains all methods and handlers for user functionalities such as listing books issued books,
 * listing wishlist , adding to wishlist etc.
 */
@Controller
@SuppressWarnings("rawtypes")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The add to bookshelf service. */
	@Autowired
	private AddToBookshelfService addToBookshelfService;

	/** The index service. */
	@Autowired
	private IndexService indexService;

	/** The search book service. */
	@Autowired
	private SearchBookService searchBookService;

	/** The user login service. */
	@Autowired
	private UserLoginService userLoginService;

	/** The Constant LOGGER. */
	final static Logger LOGGER = Logger.getLogger(UserController.class);

	// loading homepage
	/**
	 * Home page. This method will load the home page with few recommended books 
	 * and some other new and popular books to the user, among various other 
	 * functionalities. 
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/userhomepage", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model model) {
		try {
			LOGGER.info("Loding homepage for user.");
			String k = request.getUserPrincipal().getName();
			UserDetails user = new UserDetails();
			user = userLoginService.userLoginService(k);
			List<BookCatalogue> recommendedBooks = userService
					.recommendedBooks(user.getRecommended());
			List<BookCatalogue> newBooks = indexService.listNewReleaseService();
			List<BookCatalogue> mostPopular = indexService
					.listMostPopularService();
			model.addAttribute("newBooks", newBooks);
			model.addAttribute("mostPopular", mostPopular);
			model.addAttribute("recommendedBooks", recommendedBooks);
			return "user/homepage";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// search book
	/**
	 * List. This method in return to user's book search query
	 * returns with a list of books with their details.
	 *
	 * @param searchBook the search book
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/usersearchBook", method = RequestMethod.GET)
	public String list(@RequestParam("find") String searchBook, Model model) {
		try {
			LOGGER.info("searching  book for user with: " + searchBook);
			List<BookCatalogue> books = searchBookService
					.findAllService(searchBook);
			ListIterator<BookCatalogue> book = books.listIterator();
			if (book.hasNext()) {
				model.addAttribute("books", books);
			} else {
				model.addAttribute("message1", "No Results Found");
			}
			List distinctCategory = indexService.listCategoryService();
			model.addAttribute("distinctCategory", distinctCategory);
			return "resultpage";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// add to bookshelf
	/**
	 * Adds the to bookshelf. This method is used to add user's delivery request 
	 * to the book_rental_log table, if the user satisfies the various criterions,
	 * such as, this book is not already issued, book is available or not etc..
	 *
	 * @param request the request
	 * @param response the response
	 * @param isbnCode the isbn code
	 * @param userId the user id
	 * @param requestedAddress the requested address
	 * @param firstName the first name
	 * @param title the title
	 * @return the string
	 */
	@RequestMapping(value = "/useraddMyBooks", method = RequestMethod.POST)
	public @ResponseBody
	String addToBookshelf(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("isbn_code") long isbnCode,
			@RequestParam("user_id") String userId,
			@RequestParam("requested_address") String requestedAddress,
			@RequestParam("first_name") String firstName,
			@RequestParam("title") String title) {
		LOGGER.info("Adding book: " + isbnCode + " to shelf for User: "
				+ userId);
		String message2 = addToBookshelfService.addToBookshelf(isbnCode,
				userId, requestedAddress);
		if (message2.equals("Added to Bookshelf")) {
			try {
				JavaEmail.main(userId, "Order", firstName, title);
			} catch (Exception e) {
			    LOGGER.error("Exception in Controller: " + e.getMessage()
	                    + "\nCaused By:\n" + e);
			} 
		}
		return message2;
	}

	// bookshelf with details
	/**
	 * Bookshelf. This method is used for loading the bookshelf page, 
	 * which shows a list of books that the user has got issued at the current 
	 * moment. 
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/userbookshelf")
	public String bookshelf(HttpServletRequest request, Model model) {
		try {
			String k = request.getUserPrincipal().getName();
			LOGGER.info("Loading bookshelf for user with user id: " + k);
			List<BookRentalLog> bookshelf = userService.bookshelf(k);
			List<BookRentalLog> holdingBooks = new ArrayList<BookRentalLog>();
			List<BookRentalLog> pendingReturnBooks = new ArrayList<BookRentalLog>();
			List<BookRentalLog> pendingDeliveryBooks = new ArrayList<BookRentalLog>();
			Iterator<BookRentalLog> bsi = bookshelf.iterator();
			if (bsi.hasNext()) {
				while (bsi.hasNext()) {
					BookRentalLog book = bsi.next();
					if (book.getDeliveryStatus() == BookStatus.PENDING
							&& book.getReturnStatus() == BookStatus.NONE) {
						pendingDeliveryBooks.add(book);

					}
					if (book.getDeliveryStatus() == BookStatus.CLOSED
							&& (book.getReturnStatus() == BookStatus.NONE || book
									.getReturnStatus() == BookStatus.CANCELLED)) {
						holdingBooks.add(book);

					}
					if (book.getDeliveryStatus() == BookStatus.CLOSED
							&& book.getReturnStatus() == BookStatus.PENDING) {
						pendingReturnBooks.add(book);

					}
				}

				model.addAttribute("holdingBooks", holdingBooks);
				model.addAttribute("pendingReturnBooks", pendingReturnBooks);
				model.addAttribute("pendingDeliveryBooks", pendingDeliveryBooks);
			} else {
				model.addAttribute("NoBooks", "No Books are Issued Currently");
			}
			return "user/bookshelf";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// Current Requests
	/**
	 * Current requests. This method loads the current request page which shows the list
	 * of delivery and return requests that the user has made.
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/usercurrentRequests")
	public String currentRequests(HttpServletRequest request, Model model) {
		try {
			String k = request.getUserPrincipal().getName();
			List<BookRentalLog> bookshelf = userService.bookshelf(k);
			LOGGER.info("Loading current request page for User: " + k);
			List<BookRentalLog> pendingReturnBooks = new ArrayList<BookRentalLog>();
			List<BookRentalLog> pendingDeliveryBooks = new ArrayList<BookRentalLog>();
			Iterator<BookRentalLog> bsi = bookshelf.iterator();
			while (bsi.hasNext()) {
				BookRentalLog book = bsi.next();
				if (book.getDeliveryStatus() == BookStatus.PENDING
						&& book.getReturnStatus() == BookStatus.NONE) {
					pendingDeliveryBooks.add(book);

				}
				if (book.getDeliveryStatus() == BookStatus.CLOSED
						&& book.getReturnStatus() == BookStatus.PENDING) {
					pendingReturnBooks.add(book);

				}
			}
			model.addAttribute("pendingReturnBooks", pendingReturnBooks);
			model.addAttribute("pendingDeliveryBooks", pendingDeliveryBooks);
			return "user/currentRequests";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// return books
	/**
	 * Return my book. This method allows user to put a return request for 
	 * a book which he/she is currently holding.
	 *
	 * @param request the request
	 * @param transactionId the transaction id
	 * @param requestedAddress the requested address
	 * @param firstName the first name
	 * @param title the title
	 * @param userId the user id
	 * @return the string
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(value = "/userreturnMyBook", method = RequestMethod.POST)
	public String returnMyBook(HttpServletRequest request,
			@RequestParam("transaction_id") long transactionId,
			@RequestParam("requested_address") String requestedAddress,
			@RequestParam("first_name") String firstName,
			@RequestParam("title") String title,
			@RequestParam("user_id") String userId) 
		 {
		try {
			LOGGER.info("Return request for book with transaction ID: "
					+ transactionId);
			userService.returnBookService(transactionId, requestedAddress);
			try {
				JavaEmail.main(userId, "Return", firstName, title);
			} catch (Exception e) {
				LOGGER.error("Exception in Controller: " + e.getMessage()
						+ "\nCaused By:\n" + e);
			}
			return "redirect:userbookshelf";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// Cancel Return Request
	/**
	 * Cancel return request. This method allows user to cancel a return request
	 * by either reissuing the book or by just canceling the return request.
	 *
	 * @param transactionId the transaction id
	 * @param task the task
	 * @param firstName the first name
	 * @param title the title
	 * @param userId the user id
	 * @return the string
	 */
	@RequestMapping(value = "/usercancelReturn", method = RequestMethod.POST)
	public @ResponseBody
	String cancelReturnRequest(
			@RequestParam("transaction_id") long transactionId, boolean task,
			@RequestParam("first_name") String firstName,
			@RequestParam("title") String title,
			@RequestParam("user_id") String userId) {
		LOGGER.info("Cancel return request for book with transaction Id: "
				+ transactionId);
		String message = userService.cancelReturnRequestsService(transactionId,
				task, userId);
		if (message.equals("")) {
			try {
				JavaEmail.main(userId, "Cancellation", firstName, title);
			} catch (Exception e) {
                LOGGER.error("Exception in Controller: " + e.getMessage()
                        + "\nCaused By:\n" + e);

            } 
		}
		return message;
	}

	// Cancel Delivery Request
	/**
	 * Cancel delivery request. 
	 *
	 * @param request the request
	 * @param response the response
	 * @param transactionId the transaction id
	 * @param firstName the first name
	 * @param title the title
	 * @param userId the user id
	 * @return the string
	 */
	@RequestMapping(value = "/usercancelDelivery", method = RequestMethod.POST)
	public @ResponseBody
	String cancelDeliveryRequest(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("transaction_id") long transactionId,
			@RequestParam("first_name") String firstName,
			@RequestParam("title") String title,
			@RequestParam("user_id") String userId) {
		LOGGER.info("Cancel delivery request for book with transaction Id: "
				+ transactionId);
		userService.cancelDeliveryRequestsService(transactionId);
		try {
			JavaEmail.main(userId, "Cancellation", firstName, title);
		} catch (Exception e) {
            LOGGER.error("Exception in Controller: " + e.getMessage()
                    + "\nCaused By:\n" + e);

        } 
		return "";
	}

	// Book History Page
	/**
	 * Book history page. This method loads the user's history page with his/her
	 * book history and subscription plan history. 
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/userhistoryPage")
	public String bookHistorypage(HttpServletRequest request, Model model) {

		try {
			String k = request.getUserPrincipal().getName();
			LOGGER.info("Loading user history page for user: " + k);
			List<BookRentalLog> books = userService.bookHistoryService(k);
			List<SubscriptionLog> subscriptions = userService
					.subscriptionHistoryService(k);
			model.addAttribute("books", books);
			model.addAttribute("subscriptions", subscriptions);
			return "user/history";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// add to Wishlist
	/**
	 * Adds book to the wishlist.
	 *
	 * @param request the request
	 * @param response the response
	 * @param isbnCode the isbn code
	 * @param userId the user id
	 * @return the string
	 */
	@RequestMapping(value = "/useraddToWishlist", method = RequestMethod.POST)
	public @ResponseBody
	String addToWishlist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("isbn_code") long isbnCode,
			@RequestParam("user_id") String userId) {
		LOGGER.info("Adding book: " + isbnCode + "to wishlist for User: "
				+ userId);
		return userService.addToWishlistService(isbnCode, userId);
	}

	// show wishlist
	/**
	 * Show wishlist. This method loads user wishlist page with the list of 
	 * books which user has previous books user has requested.
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/usershowWishlist")
	public String showWishlist(HttpServletRequest request, Model model) {
		try {
			String k = request.getUserPrincipal().getName();
			LOGGER.info("Loading Wishlist page for user: " + k);
			List<Wishlist> wishlist = userService.showWishlistService(k);
			model.addAttribute("wishlist", wishlist);
			return "user/wishlist";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// Remove from Wishlist
	/**
	 * Removes the book from wishlist.
	 *
	 * @param request the request
	 * @param response the response
	 * @param sno the sno
	 * @return the string
	 */
	@RequestMapping(value = "/userremoveFromWishlist", method = RequestMethod.POST)
	public @ResponseBody
	String removeFromWishlist(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("sno") long sno) {
		LOGGER.info("Removing book from wishlist with sno: " + sno);
		userService.removeFromWishlistService(sno);
		return "";
	}

	// Profile page
	/** The subscription plans service. */
	@Autowired
	private SubscriptionPlansService subscriptionPlansService;

	/**
	 * Profilepage. This method loads the user profile page with user' personal
	 * details and user's current subscription plan with its details.  
	 *
	 * @param request the request
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/userprofilepage")
	public String profilepage(HttpServletRequest request, Model model) {
		try {
			String k = request.getUserPrincipal().getName();
			LOGGER.info("Loading profile page for user: " + k);
			SubscriptionLog subscription = userService
					.subscriptionPlanService(k);
			List<SubscriptionPlans> subscriptionPlans = subscriptionPlansService
					.list();
			model.addAttribute("subscriptionPlans", subscriptionPlans);
			model.addAttribute("subscription", subscription);
			return "user/profilepage";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}

	// switch to a new subscription plan
	/** The register service. */
	@Autowired
	private RegisterService registerService;

	/**
	 * Switch to new plan. This method allows user to switch to a new a new plan if 
	 * current user's subscription plan has ended. 
	 *
	 * @param request the request
	 * @param subscriptionPlan the subscription plan
	 * @return the string
	 */
	@RequestMapping(value = "/userswitchToNewPlan", method = RequestMethod.POST)
	public String switchToNewPlan(HttpServletRequest request,
			@RequestParam("subscription_plan") Integer subscriptionPlan) {
		try {
			String userId = request.getUserPrincipal().getName();
			LOGGER.info("Switching to new subscription plan: "
					+ subscriptionPlan + "for user: " + userId);
			SubscriptionLog subscription = new SubscriptionLog();
			subscription = userService.subscriptionPlanService(userId);
			if (subscription == null) {
				registerService.registerPlanService(userId, subscriptionPlan);
				return "redirect:userprofilepage";
			} else {
				return "redirect:userprofilepage";
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
	}
}