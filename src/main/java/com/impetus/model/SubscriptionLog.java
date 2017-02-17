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
 * The Class SubscriptionLog.
 */
@Entity
@Table(name = "subscription_log")
public class SubscriptionLog {
    
    /** The sno. */
    @Id
    @GeneratedValue
    private long sno;

    /** The user details. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;
    
    /** The subscription plan. */
    @Column(name = "subscription_plan")
    private Integer subscriptionPlan;
    
    /** The period. */
    private Integer period;
    
    /** The amount. */
    private Integer amount;
    
    /** The no of books. */
    @Column(name = "no_of_books")
    private Integer noOfBooks;

    /** The start date. */
    @Column(name = "start_date")
    private Timestamp startDate;
    
    /** The end date. */
    @Column(name = "end_date")
    private Timestamp endDate;

    /** The subscription plan status. */
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan_status")
    private Status subscriptionPlanStatus;

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
     * Gets the subscription plan.
     *
     * @return the subscription plan
     */
    public Integer getSubscriptionPlan() {
        return subscriptionPlan;
    }

    /**
     * Sets the subscription plan.
     *
     * @param subscriptionPlan the new subscription plan
     */
    public void setSubscriptionPlan(Integer subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    /**
     * Gets the period.
     *
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * Sets the period.
     *
     * @param period the new period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Gets the no of books.
     *
     * @return the no of books
     */
    public Integer getNoOfBooks() {
        return noOfBooks;
    }

    /**
     * Sets the no of books.
     *
     * @param noOfBooks the new no of books
     */
    public void setNoOfBooks(Integer noOfBooks) {
        this.noOfBooks = noOfBooks;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the subscription plan status.
     *
     * @return the subscription plan status
     */
    public Status getSubscriptionPlanStatus() {
        return subscriptionPlanStatus;
    }

    /**
     * Sets the subscription plan status.
     *
     * @param subscriptionPlanStatus the new subscription plan status
     */
    public void setSubscriptionPlanStatus(Status subscriptionPlanStatus) {
        this.subscriptionPlanStatus = subscriptionPlanStatus;
    }

}