package com.impetus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlans.
 */
@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlans {

    /** The subscription plan. */
    @Id
    @GeneratedValue
    @Column(name = "subscription_plan")
    private int subscriptionPlan;
    
    /** The period. */
    private Integer period;
    
    /** The amount. */
    private int amount;
    
    /** The no of books. */
    @Column(name = "no_of_books")
    private int noOfBooks;

    /** The status. */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Gets the subscription plan.
     *
     * @return the subscription plan
     */
    public int getSubscriptionPlan() {
        return subscriptionPlan;
    }

    /**
     * Sets the subscription plan.
     *
     * @param subscriptionPlan the new subscription plan
     */
    public void setSubscriptionPlan(int subscriptionPlan) {
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
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the no of books.
     *
     * @return the no of books
     */
    public int getNoOfBooks() {
        return noOfBooks;
    }

    /**
     * Sets the no of books.
     *
     * @param noOfBooks the new no of books
     */
    public void setNoOfBooks(int noOfBooks) {
        this.noOfBooks = noOfBooks;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}