package com.impetus.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Wishlist.
 */
@Entity
@Table(name = "wishlist")
public class Wishlist {
    
    /** The sno. */
    @Id
    @GeneratedValue
    private long sno;

    /** The book catalogue. */
    @ManyToOne
    @JoinColumn(name = "isbn_code")
    private BookCatalogue bookCatalogue;

    /** The user details. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;
    
    /** The wish date. */
    @Column(name = "wish_date")
    private Timestamp wishDate;

    /**
     * Gets the sno.
     *
     * @return the sno
     */
    public long getSno() {
        return sno;
    }

    /**
     * Sets the sno.
     *
     * @param sno the new sno
     */
    public void setSno(long sno) {
        this.sno = sno;
    }

    /**
     * Gets the book catalogue.
     *
     * @return the book catalogue
     */
    public BookCatalogue getBookCatalogue() {
        return bookCatalogue;
    }

    /**
     * Sets the book catalogue.
     *
     * @param bookCatalogue the new book catalogue
     */
    public void setBookCatalogue(BookCatalogue bookCatalogue) {
        this.bookCatalogue = bookCatalogue;
    }

    /**
     * Gets the user details.
     *
     * @return the user details
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Sets the user details.
     *
     * @param userDetails the new user details
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Gets the wish date.
     *
     * @return the wish date
     */
    public Timestamp getWishDate() {
        return wishDate;
    }

    /**
     * Sets the wish date.
     *
     * @param wishDate the new wish date
     */
    public void setWishDate(Timestamp wishDate) {
        this.wishDate = wishDate;
    }

}