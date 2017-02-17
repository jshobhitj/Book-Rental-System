package com.impetus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetails.
 */
@Entity
@Table(name = "user_details")
public class UserDetails {
    
    /** The user id. */
    @Id
    @Column(name = "user_id")
    private String userId;
    
    /** The user password. */
    @Column(name = "user_password")
    private String userPassword;
    
    /** The first name. */
    @Column(name = "first_name")
    private String firstName;
    
    /** The last name. */
    @Column(name = "last_name")
    private String lastName;
    
    /** The permanent address. */
    @Column(name = "permanent_address")
    private String permanentAddress;
    
    /** The phone no. */
    @Column(name = "phone_no")
    private long phoneNo;
    
    /** The user email id. */
    @Column(name = "user_email_id")
    private String userEmailId;
    
    /** The language. */
    private String language;
    
    /** The recommended. */
    private String recommended;

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user password.
     *
     * @param userPassword the new user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the permanent address.
     *
     * @return the permanent address
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * Sets the permanent address.
     *
     * @param permanentAddress the new permanent address
     */
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * Gets the phone no.
     *
     * @return the phone no
     */
    public long getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the phone no.
     *
     * @param phoneNo the new phone no
     */
    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Gets the user email id.
     *
     * @return the user email id
     */
    public String getUserEmailId() {
        return userEmailId;
    }

    /**
     * Sets the user email id.
     *
     * @param userEmailId the new user email id
     */
    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    /**
     * Gets the language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     *
     * @param language the new language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the recommended.
     *
     * @return the recommended
     */
    public String getRecommended() {
        return recommended;
    }

    /**
     * Sets the recommended.
     *
     * @param recommended the new recommended
     */
    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

}