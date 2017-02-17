package com.impetus.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.BookCatalogue;
import com.impetus.service.IndexService;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController. This class contains methods and handlers for main index page.
 */
@Controller
@RequestMapping("/")
@SuppressWarnings("rawtypes")
public class MainController {

    /** The index service. */
    @Autowired
    private IndexService indexService;
    
    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(MainController.class);

    /**
     * List. This method loads index page with the list of book categories, new released books and most popular books.
     *
     * @param model the model
     * @return the string
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) throws AddressException, MessagingException {
        try {
			LOGGER.info("Loading main index page.");
			List<BookCatalogue> newBooks = indexService.listNewReleaseService();
			List<BookCatalogue> mostPopular = indexService
					.listMostPopularService();
			List distinctCategory = indexService.listCategoryService();
			model.addAttribute("distinctCategory", distinctCategory);
			model.addAttribute("newBooks", newBooks);
			model.addAttribute("mostPopular", mostPopular);
			return "index";
		} catch (Exception e) {
			LOGGER.error("Exception in Controller: " + e.getMessage()
					+ "\nCaused By:\n" + e);
			throw new CustomGenericException(e.getMessage());
		}
    }
    
    @RequestMapping(value = { "error404","error500" })
	public String errorPage() {
		return "errorpage";
	}
    
}
