package com.impetus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.dao.UserLoginDAO;
import com.impetus.exceptions.ServiceLayerException;
import com.impetus.model.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginService.
 */
@Service
public class UserLoginService {
    
    /** The user login dao. */
    @Autowired
    private UserLoginDAO userLoginDAO;

    /**
     * Sets the user login dao.
     *
     * @param userLoginDAO the new user login dao
     */
    public void setUserLoginDAO(UserLoginDAO userLoginDAO) {
        this.userLoginDAO = userLoginDAO;
    }

    /**
     * User login service.
     *
     * @param userId the user id
     * @return the user details
     */
    public UserDetails userLoginService(String userId) {
        try {
            return userLoginDAO.userDetailsDAO(userId);
        } catch (Exception e) {
            throw new ServiceLayerException(e.getMessage(), e);
        }
    }
}