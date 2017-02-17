package com.impetus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.impetus.model.BookCatalogue;
import com.impetus.service.SearchBookService;

// TODO: Auto-generated Javadoc
/**
 * The Class RestController. This class contains handlers related to rest web service.
 */
@Controller
@RequestMapping(value = "/searchBookRest")
public class RestController {
    
    /** The search book service. */
    @Autowired
    private SearchBookService searchBookService;

    /** The Constant LOGGER. */
    final static Logger LOGGER = Logger.getLogger(RestController.class);

    /**
     * Gets the book. This handler handles rest web service requests for searching books.
     *
     * @param name the name
     * @return the book
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<BookCatalogue> getBook(@PathVariable String name) {
        LOGGER.info("Rest Service for searching book: " + name);
        return searchBookService.findAllService(name);
    }
}
