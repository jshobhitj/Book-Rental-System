package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.UserDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;
import com.impetus.service.UserService;

public class UserServiceTest extends TestCase{

	private UserDAO userDAO;
	
	@Before 
	public void setUp() {
		userDAO = mock(UserDAO.class); 
	}
	
	@Test
	public void testbookshelf() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		String userId="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.findAll(userId)).thenReturn(expected);
		List<BookRentalLog> actual=userService.bookshelf(userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testreturnBookService() {
		String requestedAddress="";
		long transactionId=0;
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		userService.returnBookService(transactionId, requestedAddress);
		
		assertTrue(true);	
	}
	
	@Test
	public void testcancelReturnRequestsService() throws AddressException, MessagingException {
		SubscriptionLog sub=new SubscriptionLog();
		Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
		 Timestamp ts = new Timestamp(stamp1.getTime());
		 Calendar cal1 = Calendar.getInstance();
		 cal1.setTime(ts);
		 cal1.add(Calendar.DAY_OF_WEEK, 25);
		 ts = new Timestamp(cal1.getTime().getTime());
		sub.setEndDate(ts);
		boolean task=true;
		long transactionId=0;
		String userId="";
		String expected="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(sub);
		String actual=userService.cancelReturnRequestsService(transactionId, task,userId);
		
		assertNotNull(actual);
		assertEquals(expected, actual);	
		
		 cal1.add(Calendar.DAY_OF_WEEK, -45);
		 ts = new Timestamp(cal1.getTime().getTime());
		 sub.setEndDate(ts);
		 
		 when(userDAO.subscriptionPlanDAO(userId)).thenReturn(sub);
		 String actual1=userService.cancelReturnRequestsService(transactionId, task,userId);
		 String expected1="Can't cancel return/Re-Issue request as your subscription will end in 15 Days..";
		 
		 assertNotNull(actual1);
		assertEquals(expected1, actual1);	
	}
	
	@Test
	public void testcancelDeliveryRequestsService() {
		long transactionId=0;
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		userService.cancelDeliveryRequestsService(transactionId);
		
		assertTrue(true);	
	}
	
	@Test
	public void testsubscriptionPlanService() {
		SubscriptionLog expected=new SubscriptionLog();
		String userId="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(expected);
		SubscriptionLog actual=userService.subscriptionPlanService(userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testbookHistoryService() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		String user="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.bookHistoryDAO(user)).thenReturn(expected);
		List<BookRentalLog> actual=userService.bookHistoryService(user);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testsubscriptionHistoryService() {
		List <SubscriptionLog> expected=new ArrayList <SubscriptionLog>();
		String user="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.subscriptionHistoryDAO(user)).thenReturn(expected);
		List <SubscriptionLog> actual=userService.subscriptionHistoryService(user);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testaddToWishlistService() {
		String expected="Already in Wishlist";
		String userId="";
		long isbnCode=1;
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.statusWishlistDAO(isbnCode, userId)).thenReturn(true);
		String actual=userService.addToWishlistService(isbnCode, userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);
		
		String expected1="Added to Wishlist";
		
		when(userDAO.statusWishlistDAO(isbnCode, userId)).thenReturn(false);
		String actual1=userService.addToWishlistService(isbnCode, userId);
		
		assertNotNull(actual1);
		assertSame(expected1, actual1);
	}
	
	@Test
	public void testshowWishlistService() {
		List<Wishlist> expected=new ArrayList<Wishlist>();
		String userId="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.showWishlistDAO(userId)).thenReturn(expected);
		List<Wishlist> actual=userService.showWishlistService(userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testremoveFromWishlistService() {
		long sno=0;
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		userService.removeFromWishlistService(sno);
		
		assertTrue(true);
	}
	
	@Test
	public void testchangeSubscriptionStatusService() {
		SubscriptionLog subscription=new SubscriptionLog();
		Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
		Timestamp to = new Timestamp(stamp1.getTime());
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(to);
		cal1.add(Calendar.DAY_OF_YEAR, -10);
		Timestamp from = new Timestamp(cal1.getTime().getTime());
		subscription.setEndDate(from);
		String userId="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subscription);
		userService.changeSubscriptionStatusService(userId);
		
		assertTrue(true);
		
		cal1.add(Calendar.DAY_OF_YEAR, +30);
		Timestamp from1 = new Timestamp(cal1.getTime().getTime());
		subscription.setEndDate(from1);
		
		when(userDAO.subscriptionPlanDAO(userId)).thenReturn(subscription);
		userService.changeSubscriptionStatusService(userId);
		
		assertTrue(true);
	}
	
	@Test
	public void testuserHistoryService() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		String userId="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.gettingUserHistory(userId)).thenReturn(expected);
		List<BookRentalLog> actual=userService.userHistoryService(userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testgettingCategoryBooksService() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String category="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.gettingCategoryBooks(category)).thenReturn(expected);
		List<BookCatalogue> actual=userService.gettingCategoryBooksService(category);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testgettingAuthorBooksService() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String author="";
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.gettingAuthorBooks(author)).thenReturn(expected);
		List<BookCatalogue> actual=userService.gettingAuthorBooksService(author);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testupdateUserRecomService() {
		UserDetails user=new UserDetails();
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		userService.updateUserRecomService(user);	
		
		assertTrue(true);
	}
	
	@Test
	public void testrecommendedBooks() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String recom="1,2";
		BookCatalogue book=new BookCatalogue();
		expected.add(book);
		expected.add(book);
		
		UserService userService=new UserService();
		userService.setUserDAO(userDAO);
		when(userDAO.getTheBook(anyLong())).thenReturn(book);
		List<BookCatalogue> actual=userService.recommendedBooks(recom);

		assertNotNull(actual);
		assertEquals(expected, actual);		
	}
	
}
