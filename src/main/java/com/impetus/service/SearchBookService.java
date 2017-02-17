package com.impetus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.SearchBookDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchBookService.
 */
@Service
public class SearchBookService {
    
    /** The search book dao. */
    @Autowired
    private SearchBookDAO searchBookDAO;

    /**
     * Sets the search book dao.
     *
     * @param searchBookDAO the new search book dao
     */
    public void setSearchBookDAO(SearchBookDAO searchBookDAO) {
        this.searchBookDAO = searchBookDAO;
    }

    // search book
    /**
     * Find all service.
     *
     * @param searchBook the search book
     * @return the list
     */
    public List<BookCatalogue> findAllService(String searchBook) {
        try {
            return searchBookDAO.findAllDAO(searchBook);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

}