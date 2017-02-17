package com.impetus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.IndexDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.BookCatalogue;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexService.
 */
@Service
@SuppressWarnings({ "rawtypes" })
public class IndexService {
    
    /** The index dao. */
    @Autowired
    private IndexDAO indexDAO;

    /**
     * Sets the index dao.
     *
     * @param indexDAO the new index dao
     */
    public void setIndexDAO(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }

    // list all category
    /**
     * List category service.
     *
     * @return the list
     */
    public List listCategoryService() {
        try {
            return indexDAO.listCategoryDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // list new release
    /**
     * List new release service.
     *
     * @return the list
     */
    public List<BookCatalogue> listNewReleaseService() {
        try {
            return indexDAO.listNewReleaseDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }

    // list Most Popular
    /**
     * List most popular service.
     *
     * @return the list
     */
    public List<BookCatalogue> listMostPopularService() {
        try {
            return indexDAO.listMostPopularDAO();
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}
