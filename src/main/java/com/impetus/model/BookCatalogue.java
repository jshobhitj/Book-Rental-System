package com.impetus.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class BookCatalogue.
 */
@Entity
@Table(name = "book_catalogue")
public class BookCatalogue {
    
    /** The isbn code. */
    @Id
    @Column(name = "isbn_code")
    private long isbnCode;
    
    /** The title. */
    private String title;
    
    /** The author. */
    private String author;
    
    /** The category. */
    private String category;
    
    /** The image. */
    private String image;
    
    /** The book description. */
    @Column(name = "book_description")
    private String bookDescription;
    
    /** The publisher. */
    private String publisher;
    
    /** The published date. */
    @Column(name = "published_date")
    private Date publishedDate;
    
    /** The no of pages. */
    @Column(name = "no_of_pages")
    private Integer noOfPages;
    
    /** The no of copies available. */
    @Column(name = "no_of_copies_available")
    private Integer noOfCopiesAvailable;
    
    /** The recomment count. */
    @Column(name = "recomment_count")
    private Integer recommentCount;
    
    /** The availibility. */
    private boolean availibility;

    /**
     * Gets the isbn code.
     *
     * @return the isbn code
     */
    public long getIsbnCode() {
        return isbnCode;
    }

    /**
     * Sets the isbn code.
     *
     * @param isbnCode the new isbn code
     */
    public void setIsbnCode(long isbnCode) {
        this.isbnCode = isbnCode;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image.
     *
     * @param image the new image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the book description.
     *
     * @return the book description
     */
    public String getBookDescription() {
        return bookDescription;
    }

    /**
     * Sets the book description.
     *
     * @param bookDescription the new book description
     */
    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    /**
     * Gets the publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher.
     *
     * @param publisher the new publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the published date.
     *
     * @return the published date
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * Sets the published date.
     *
     * @param publishedDate the new published date
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * Gets the no of pages.
     *
     * @return the no of pages
     */
    public Integer getNoOfPages() {
        return noOfPages;
    }

    /**
     * Sets the no of pages.
     *
     * @param noOfPages the new no of pages
     */
    public void setNoOfPages(Integer noOfPages) {
        this.noOfPages = noOfPages;
    }

    /**
     * Gets the no of copies available.
     *
     * @return the no of copies available
     */
    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    /**
     * Sets the no of copies available.
     *
     * @param noOfCopiesAvailable the new no of copies available
     */
    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    /**
     * Gets the recomment count.
     *
     * @return the recomment count
     */
    public Integer getRecommentCount() {
        return recommentCount;
    }

    /**
     * Sets the recomment count.
     *
     * @param recommentCount the new recomment count
     */
    public void setRecommentCount(Integer recommentCount) {
        this.recommentCount = recommentCount;
    }

    /**
     * Checks if is availibility.
     *
     * @return true, if is availibility
     */
    public boolean isAvailibility() {
        return availibility;
    }

    /**
     * Sets the availibility.
     *
     * @param availibility the new availibility
     */
    public void setAvailibility(boolean availibility) {
        this.availibility = availibility;
    }

}