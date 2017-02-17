package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.AddToBookshelfDAO;
import com.impetus.dao.UserDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;
import com.impetus.service.AddToBookshelfService;

public class AddToBookshelfServiceTest extends TestCase{
	private AddToBookshelfDAO addToBookshelfDAO;
	private UserDAO userDAO;
	
	@Before 
	public void setUp() {
		addToBookshelfDAO = mock(AddToBookshelfDAO.class); 
		userDAO = mock(UserDAO.class);
	}
	
	@Test
	public void testsaddToBookshelf() {
		SubscriptionLog subLog1=null;
		long isbnCode=1;
		String userId="";
		
		AddToBookshelfService addToBookshelfService=new AddToBookshelfService();
		addToBookshelfService.setAddToBookshelfDAO(addToBookshelfDAO);
		addToBookshelfService.setUserDAO(userDAO);
		
		//no subscription plan
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog1);
		String actual=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual);
		assertSame("Your subscription plan is not Active", actual);
		
		UserDetails user=new UserDetails();
		user.setUserId(userId);
		SubscriptionLog subLog=new SubscriptionLog();
		BookCatalogue book=new BookCatalogue();
		book.setIsbnCode(isbnCode);
		subLog.setUserDetails(user);
		Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(stamp1.getTime());
		subLog.setEndDate(endDate);
		
		//10 days left
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog);
		String actual1=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual1);
		assertSame("Book Can't be Issued!! Your subscription will expire in 10 Days.", actual1);
		
		//book availability
		Timestamp endDate1 = new Timestamp(stamp1.getTime());
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(endDate1);
		cal1.add(Calendar.DAY_OF_YEAR, 30);
		endDate1 = new Timestamp(cal1.getTime().getTime());
		subLog.setEndDate(endDate1);
		book.setNoOfCopiesAvailable(0);
		
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog);
		when(userDAO.bookDetails(isbnCode)).thenReturn(book);
		String actual2=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual2);
		assertSame("Not Available", actual2);
		
		//no of books can be issued
		book.setNoOfCopiesAvailable(2);
		subLog.setNoOfBooks(6);
		
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog);
		when(userDAO.bookDetails(isbnCode)).thenReturn(book);
		when(userDAO.noOfBooksIssued(userId)).thenReturn(6);
		String actual3=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual3);
		assertSame("Book Issue Limit is Exceeded!! Check Your Subscription Plan.", actual3);
		
		//book already issued 
		subLog.setNoOfBooks(8);
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog);
		when(userDAO.bookDetails(isbnCode)).thenReturn(book);
		when(userDAO.noOfBooksIssued(userId)).thenReturn(6);
		when(userDAO.statusIssued(isbnCode, userId)).thenReturn(true);
		String actual4=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual4);
		assertSame("Already Issued!!", actual4);
		
		//add to bookshelf
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subLog);
		when(userDAO.bookDetails(isbnCode)).thenReturn(book);
		when(userDAO.noOfBooksIssued(userId)).thenReturn(6);
		when(userDAO.statusIssued(isbnCode, userId)).thenReturn(false);
		String actual5=addToBookshelfService.addToBookshelf(isbnCode, userId,"");
		
		assertNotNull(actual5);
		assertSame("Added to Bookshelf", actual5);
	}
}
