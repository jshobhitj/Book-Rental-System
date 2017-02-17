package com.impetus.controller;

import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.impetus.exceptions.CustomGenericException;
import com.impetus.model.BookCatalogue;
import com.impetus.service.IndexService;
import com.impetus.service.SearchBookService;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchBookController. This class contains methods and handler related to searching book table. Searching is done 
 * based on authors name,  or book title or book category.
 */
@Controller
@SuppressWarnings("rawtypes")
public class SearchBookController {

    /** The search book service. */
    @Autowired
    private SearchBookService searchBookService;
    
    /** The index service. */
    @Autowired
    private IndexService indexService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(SearchBookController.class);

    /**
     * List. This lists list of all related books with user search query.
     *
     * @param request the request
     * @param searchBook the search book
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/searchBook", method = RequestMethod.GET)
    public String list(HttpServletRequest request,
            @RequestParam("find") String searchBook, Model model) {
        try {
			LOGGER.info("Searching book with: " + searchBook);
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

}