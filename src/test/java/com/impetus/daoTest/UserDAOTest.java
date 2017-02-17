package com.impetus.daoTest;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.UserDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;
import com.impetus.model.Wishlist;

public class UserDAOTest extends TestCase{
	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	private Query query;
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
		query = mock(Query.class);
		
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.createQuery(anyString())).thenReturn(query);
	    when(session.createCriteria(SubscriptionPlans.class)).thenReturn(criteria);
	    when(session.createCriteria(BookCatalogue.class)).thenReturn(criteria);
	    when(session.createCriteria(BookRentalLog.class)).thenReturn(criteria);
	    when(session.createCriteria(UserDetails.class)).thenReturn(criteria);
	    when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
	    when(criteria.setProjection( Projections.distinct( Projections.property(anyString())))).thenReturn(criteria);
	}
	
	@Test
	public void testfindAll() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		List<BookRentalLog> actual=userDAO.findAll("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testsubscriptionPlanDAO() {
		SubscriptionLog expected=new SubscriptionLog();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.uniqueResult()).thenReturn(expected);		
		SubscriptionLog actual=userDAO.subscriptionPlanDAO("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testbookHistoryDAO() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		List<BookRentalLog> actual=userDAO.bookHistoryDAO("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testsubscriptionHistoryDAO() {
		List<SubscriptionLog> expected=new ArrayList<SubscriptionLog>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		List<SubscriptionLog> actual=userDAO.subscriptionHistoryDAO("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testshowWishlistDAO() {
		List<Wishlist> expected=new ArrayList<Wishlist>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		List<Wishlist> actual=userDAO.showWishlistDAO("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testbookDetails() {
		BookCatalogue expected=new BookCatalogue();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.uniqueResult()).thenReturn(expected);		
		BookCatalogue actual=userDAO.bookDetails(0);
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void testnoOfBooksIssued() {
		List<BookRentalLog> expected1=new ArrayList<BookRentalLog>();
		Integer expected=0;
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected1);		
		Integer actual=userDAO.noOfBooksIssued("");
		
		assertNotNull(actual);
		assertSame(expected, actual);		
	}
	
	@Test
	public void teststatusIssued() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		BookRentalLog book=new BookRentalLog();
		expected.add(book);
		
		List<BookRentalLog> expected1=new ArrayList<BookRentalLog>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		boolean actual=userDAO.statusIssued(0,"");
		
		assertTrue(actual);	
		
		when(query.list()).thenReturn(expected1);		
		boolean actual1=userDAO.statusIssued(0,"");
		
		assertFalse(actual1);
	}
	
	@Test
	public void teststatusWishlistDAO() {
		List<Wishlist> expected=new ArrayList<Wishlist>();
		Wishlist wish=new Wishlist();
		expected.add(wish);
		
		List<Wishlist> expected1=new ArrayList<Wishlist>();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(query.list()).thenReturn(expected);		
		boolean actual=userDAO.statusWishlistDAO(0,"");
		
		assertTrue(actual);	
		
		when(query.list()).thenReturn(expected1);		
		boolean actual1=userDAO.statusWishlistDAO(0,"");
		
		assertFalse(actual1);
	}
	
	@Test
	public void testreturnBookDAO() {
		BookRentalLog bookRequest=new BookRentalLog();
		long transactionId=0;
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.get(BookRentalLog.class, transactionId)).thenReturn(bookRequest);
		userDAO.returnBookDAO(transactionId,"");
		
		assertTrue(true);
	}
	
	@Test
	public void testcancelReturnRequestsDAO() {
		BookRentalLog bookRequest=new BookRentalLog();
		long transactionId=0;
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.get(BookRentalLog.class, transactionId)).thenReturn(bookRequest);
		userDAO.cancelReturnRequestsDAO(transactionId,true);
		userDAO.cancelReturnRequestsDAO(transactionId,false);
		
		assertTrue(true);
	}
	
	@Test
	public void testcancelDeliveryRequestsDAO() {
		BookRentalLog bookRequest=new BookRentalLog();
		long transactionId=0;
		long isbnCode=0;
		BookCatalogue book=new BookCatalogue();
		book.setIsbnCode(isbnCode);
		book.setNoOfCopiesAvailable(1);
		bookRequest.setBookCatalogue(book);
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.get(BookRentalLog.class, transactionId)).thenReturn(bookRequest);
		when(session.get(BookCatalogue.class, isbnCode)).thenReturn(book);
		userDAO.cancelDeliveryRequestsDAO(transactionId);
		
		assertTrue(true);
	}
	
	@Test
	public void testaddToWishlistDAO() {
		Wishlist wish=new Wishlist();
		long isbnCode=0;
		BookCatalogue book=new BookCatalogue();
		book.setIsbnCode(isbnCode);
		book.setRecommentCount(1);
		wish.setBookCatalogue(book);
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.get(BookCatalogue.class, isbnCode)).thenReturn(book);
		userDAO.addToWishlistDAO(wish);
		
		assertTrue(true);
	}
	
	@Test
	public void testremoveFromWishlistDAO() {
		Wishlist wish=new Wishlist();
		long sno=1;
		wish.setSno(sno);
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.get(Wishlist.class, sno)).thenReturn(wish);
		userDAO.removeFromWishlistDAO(sno);
		
		assertTrue(true);
	}
	
	@Test
	public void testchangeSubscriptionStatusDAO() {
		SubscriptionLog subscription=new SubscriptionLog();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		userDAO.changeSubscriptionStatusDAO(subscription);
		
		assertTrue(true);
	}
	
	@Test
	public void testgettingUserHistory() {
		List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
		String userId="";
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.createQuery("from BookRentalLog where user_id='"+userId+"' order by issueDate desc").list()).thenReturn(expected);
		List<BookRentalLog> actual=userDAO.gettingUserHistory( userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
	
	@Test
	public void testgettingCategoryBooks() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String category="";
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.createQuery("from BookCatalogue where category='"+category+"'").list()).thenReturn(expected);
		List<BookCatalogue> actual=userDAO.gettingCategoryBooks( category);
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
	
	@Test
	public void testgettingAuthorBooks() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String author="";
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(session.createQuery("from BookCatalogue where author='"+author+"'").list()).thenReturn(expected);
		List<BookCatalogue> actual=userDAO.gettingAuthorBooks( author);
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
	
	@Test
	public void testupdateUserRecomDAO() {
		UserDetails user=new UserDetails();
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		userDAO.updateUserRecomDAO(user);
		
		assertTrue(true);
	}
	
	@Test
	public void testgetTheBook() {
		BookCatalogue expected=new BookCatalogue();
		long isbnCode=0;
		
		UserDAO userDAO=new UserDAO();
		userDAO.setSessionFactory(sessionFactory);
		when(criteria.uniqueResult()).thenReturn(expected);
		BookCatalogue actual=userDAO.getTheBook(isbnCode);

		assertNotNull(actual);
		assertSame(expected, actual);
	}
}

