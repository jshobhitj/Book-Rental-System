package com.impetus.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class BookRentalLog.
 */
@Entity
@Table(name = "book_rental_log")
public class BookRentalLog {
    
    /** The transaction id. */
    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private long transactionId;

    /** The issue date. */
    @Column(name = "issue_date")
    private Timestamp issueDate;
    
    /** The return date. */
    @Column(name = "return_date")
    private Timestamp returnDate;
    
    /** The delivery status. */
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private BookStatus deliveryStatus;
    
    /** The return status. */
    @Enumerated(EnumType.STRING)
    @Column(name = "return_status")
    private BookStatus returnStatus;
    
    /** The requested address. */
    @Column(name = "requested_address")
    private String requestedAddress;
    
    /** The bookshelf. */
    private boolean bookshelf;

    /** The book catalogue. */
    @ManyToOne
    @JoinColumn(name = "isbn_code")
    private BookCatalogue bookCatalogue;

    /** The user details. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;

    /**
     * Gets the transaction id.
     *
     * @return the transaction id
     */
    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction id.
     *
     * @param transactionId the new transaction id
     */
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the issue date.
     *
     * @return the issue date
     */
    public Timestamp getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the issue date.
     *
     * @param issueDate the new issue date
     */
    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Gets the return date.
     *
     * @return the return date
     */
    public Timestamp getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     *
     * @param returnDate the new return date
     */
    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the delivery status.
     *
     * @return the delivery status
     */
    public BookStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * Sets the delivery status.
     *
     * @param deliveryStatus the new delivery status
     */
    public void setDeliveryStatus(BookStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * Gets the return status.
     *
     * @return the return status
     */
    public BookStatus getReturnStatus() {
        return returnStatus;
    }

    /**
     * Sets the return status.
     *
     * @param returnStatus the new return status
     */
    public void setReturnStatus(BookStatus returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * Gets the requested address.
     *
     * @return the requested address
     */
    public String getRequestedAddress() {
        return requestedAddress;
    }

    /**
     * Sets the requested address.
     *
     * @param requestedAddress the new requested address
     */
    public void setRequestedAddress(String requestedAddress) {
        this.requestedAddress = requestedAddress;
    }

    /**
     * Checks if is bookshelf.
     *
     * @return true, if is bookshelf
     */
    public boolean isBookshelf() {
        return bookshelf;
    }

    /**
     * Sets the bookshelf.
     *
     * @param bookshelf the new bookshelf
     */
    public void setBookshelf(boolean bookshelf) {
        this.bookshelf = bookshelf;
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

}