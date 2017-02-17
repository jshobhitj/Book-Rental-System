package com.impetus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.BookCatalogue;
import com.impetus.model.UserDetails;
import com.impetus.service.IndexService;
import com.impetus.service.UserLoginService;
import com.impetus.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController. This class contains all the request handlers for redirecting a user to various pages based on their
 * role eg. admin or user etc and also for displaying messages in case of bad credentials, access denied or logout.
 */
@Controller
@SuppressWarnings("rawtypes")
public class LoginController {
    
    /** The user login service. */
    @Autowired
    private UserLoginService userLoginService;
    
    /** The index service. */
    @Autowired
    private IndexService indexService;
    
    /** The user service. */
    @Autowired
    private UserService userService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(LoginController.class);

    /**
     * Gets the login form. This method returns user to login form page(i.e. index page) in case of access denied, logout or bad credentials.
     *
     * @param request the request
     * @param model the model
     * @param authfailed the authfailed
     * @param logout the logout
     * @param denied the denied
     * @return the login form
     */
    @RequestMapping("login")
    public String getLoginForm(HttpServletRequest request, Model model,
            @RequestParam(required = false) String authfailed, String logout,
            String denied) {
        String message = "";
        try {
			if (authfailed != null) {
				message = "Invalid username of password, try again !";
			} else if (logout != null) {
				HttpSession session = request.getSession();
				session.invalidate();
				message = "Logged Out successfully, login again to continue !";
			} else if (denied != null) {
				message = "Access denied for this user !";
			}
			LOGGER.info("User login failed or Logout");
			List<BookCatalogue> newBooks = indexService.listNewReleaseService();
			List<BookCatalogue> mostPopular = indexService
					.listMostPopularService();
			List distinctCategory = indexService.listCategoryService();
			model.addAttribute("distinctCategory", distinctCategory);
			model.addAttribute("newBooks", newBooks);
			model.addAttribute("mostPopular", mostPopular);
			model.addAttribute("message", message);
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
		return "index";
    }

    /**
     * Ge user page. This method is for directing users with ROLE_USER to homepage with their user details uploaded in session.
     *
     * @param request the request
     * @param model the model
     * @return the string
     */
    @RequestMapping("user")
    public String geUserPage(HttpServletRequest request, Model model) {
        try {
			String k = request.getUserPrincipal().getName();
			userService.changeSubscriptionStatusService(k);
			LOGGER.info("User login with user id: " + k);
			UserDetails user = new UserDetails();
			user = userLoginService.userLoginService(k);
			List<BookCatalogue> recommendedBooks = userService
					.recommendedBooks(user.getRecommended());
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			List<BookCatalogue> newBooks = indexService.listNewReleaseService();
			List<BookCatalogue> mostPopular = indexService
					.listMostPopularService();
			List distinctCategory = indexService.listCategoryService();
			session.setAttribute("distinctCategory", distinctCategory);
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

    /**
     * Get admin page. This method directs user with ROLE_ADMIN to admin page.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("admin")
    public String geAdminPage(Model model) {
        try {
			LOGGER.info("Admin Login");
			return "admin/admin";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }

    /**
     * Ge403denied.
     *
     * @return the string
     */
    @RequestMapping("403page")
    public String ge403denied() {
        LOGGER.info("Error 403 Page");
        return "redirect:login?denied";
    }

}