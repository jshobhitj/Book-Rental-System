package com.impetus.daoTest;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.AdminDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;

@SuppressWarnings({"rawtypes"})
public class AdminDAOTest extends TestCase{

	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	private Query query;
	private SQLQuery sqlquery;

	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
		query = mock(Query.class);
		sqlquery=mock(SQLQuery.class);
		
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
	public void testlistBooksDAO() {
	List<BookCatalogue> expected= new ArrayList<BookCatalogue>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(criteria.list()).thenReturn(expected);
	List<BookCatalogue> actual=adminDAO.listBooksDAO();
	
	assertNotNull(actual);
	assertSame(expected, actual);	
	}

	@Test
	public void testlistTitleDAO() {
	List expected= new ArrayList();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(criteria.list()).thenReturn(expected);
	List actual=adminDAO.listTitleDAO();
	
	assertNotNull(actual);
	assertSame(expected, actual);	
	}
	
	@Test
	public void testlistAuthorDAO() {
	List expected= new ArrayList();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(criteria.list()).thenReturn(expected);
	List actual=adminDAO.listAuthorDAO();
	
	assertNotNull(actual);
	assertSame(expected, actual);	
	}
	
	@Test
	public void testlistCategoryDAO() {
	List expected= new ArrayList();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(criteria.list()).thenReturn(expected);
	List actual=adminDAO.listCategoryDAO();
	
	assertNotNull(actual);
	assertSame(expected, actual);	
	}
	
	
	@Test
	public void testsearchBookDAO() {
	BookCatalogue expected= new BookCatalogue();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(criteria.uniqueResult()).thenReturn(expected);
	BookCatalogue actual=adminDAO.searchBookDAO(1);
	
	assertNotNull(actual);
	assertSame(expected, actual);	
	}
	
	@Test
	public void testsearchIssuedBookDAO() {
    List<BookRentalLog> expectedlist= new ArrayList<BookRentalLog>();
	Integer expected=expectedlist.size();
    
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expectedlist);
	Integer actual=adminDAO.searchIssuedBookDAO(1);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testactiveSubscriptionsDAO() {
	List<SubscriptionLog> expected= new ArrayList<SubscriptionLog>();
	
    
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<SubscriptionLog> actual=adminDAO.activeSubscriptionsDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testsubscriptionPlansDAO() {
	List<SubscriptionPlans> expected= new ArrayList<SubscriptionPlans>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<SubscriptionPlans> actual=adminDAO.subscriptionPlansDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testpendingDeliveryDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.pendingDeliveryDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testpendingReturnDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.pendingReturnDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testcancelledDeliveryDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.cancelledDeliveryDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testcancelledReturnDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.cancelledReturnDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testclosedDeliveryDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.closedDeliveryDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testclosedReturnDAO() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();
	
	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);
	when(query.list()).thenReturn(expected);
	List<BookRentalLog> actual=adminDAO.closedReturnDAO();
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@SuppressWarnings({"unchecked"})
	@Test
	public void testlistBooks6() {

	List<Long> expected= new ArrayList();
	expected.add((long) 1);
	expected.add((long) 1);
	expected.add((long) 1);
	expected.add((long) 1);
	expected.add((long) 1);
	expected.add((long) 1);

	List expected1= new ArrayList();
	long isbnCode=0;
	expected1.add(isbnCode);

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	
	when(session.createCriteria( BookRentalLog.class,"bookRentalLog" )).thenReturn(criteria);
	when(criteria.add(Restrictions.between("bookRentalLog.issueDate", from, to))).thenReturn(criteria);
	when(criteria.createCriteria("bookRentalLog.bookCatalogue", "book")).thenReturn(criteria);	
	when(criteria.setProjection( Projections.distinct( Projections.property( "book.isbnCode")))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected1);
	when(session.createQuery(anyString())).thenReturn(query);
	when(query.uniqueResult()).thenReturn((long)1);
	List<Long> actual=adminDAO.listBooks6(from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testupdateBookDAO() {
		BookCatalogue book=new BookCatalogue();
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		adminDAO.updateBookDAO(book);
		
		assertTrue(true);
	}
	
	@Test
	public void testdeleteBookDAO() {
		BookCatalogue book=new BookCatalogue();
		long isbnCode=0;
		book.setIsbnCode(isbnCode);
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		when(session.get(BookCatalogue.class, isbnCode)).thenReturn(book);
		adminDAO.deleteBookDAO(isbnCode);
		
		assertTrue(true);
	}
	
	@Test
	public void testaddBookDAO() {
		BookCatalogue book=new BookCatalogue();
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		adminDAO.addBookDAO(book);
		
		assertTrue(true);
	}
	
	@Test
	public void testbookDeliveredDAO() {
		BookRentalLog transaction=new BookRentalLog();
		long transactionId=0;
		transaction.setTransactionId(transactionId);
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		when(session.get(BookRentalLog.class, transactionId)).thenReturn(transaction);
		adminDAO.bookDeliveredDAO(transactionId);
		
		assertTrue(true);
	}
	
	@Test
	public void testbookReturnedDAO() {
		BookRentalLog transaction=new BookRentalLog();
		long transactionId=0;
		long isbnCode1=0;
		BookCatalogue book=new BookCatalogue();
		book.setIsbnCode(isbnCode1);
		book.setNoOfCopiesAvailable(1);
		transaction.setTransactionId(transactionId);
		transaction.setBookCatalogue(book);
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		when(session.get(BookRentalLog.class, transactionId)).thenReturn(transaction);
		when(session.get(BookCatalogue.class, isbnCode1)).thenReturn(book);		
		adminDAO.bookReturnedDAO(transactionId);
		
		assertTrue(true);
	}
	
	@Test
	public void testlistBooks1() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	when(session.createCriteria( BookRentalLog.class ,"bookRentalLog")).thenReturn(criteria);
	when(criteria.add(Restrictions.between("bookRentalLog.issueDate", from, to))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected);

	List<BookRentalLog> actual=adminDAO.listBooks1(from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testlistBooks2() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	String title="";
	
	when(session.createCriteria( BookRentalLog.class ,"bookRentalLog")).thenReturn(criteria);
	when(criteria.createCriteria("bookRentalLog.bookCatalogue", "book")).thenReturn(criteria);	
	when(criteria.add(Restrictions.and(Restrictions.between("bookRentalLog.issueDate", from, to),Restrictions.eq("book.title", title)))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected);

	List<BookRentalLog> actual=adminDAO.listBooks2(title,from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testlistBooks3() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	String author="";
	
	when(session.createCriteria( BookRentalLog.class ,"bookRentalLog")).thenReturn(criteria);
	when(criteria.createCriteria("bookRentalLog.bookCatalogue", "book")).thenReturn(criteria);	
	when(criteria.add(Restrictions.and(Restrictions.between("bookRentalLog.issueDate", from, to),Restrictions.eq("book.author", author)))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected);

	List<BookRentalLog> actual=adminDAO.listBooks3(author,from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testlistBooks4() {
	List<BookRentalLog> expected= new ArrayList<BookRentalLog>();

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	String category="";
	
	when(session.createCriteria( BookRentalLog.class ,"bookRentalLog")).thenReturn(criteria);
	when(criteria.createCriteria("bookRentalLog.bookCatalogue", "book")).thenReturn(criteria);	
	when(criteria.add(Restrictions.and(Restrictions.between("bookRentalLog.issueDate", from, to),Restrictions.eq("book.category", category)))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected);

	List<BookRentalLog> actual=adminDAO.listBooks4(category,from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testlistBooks5() {
	List<BookCatalogue> expected= new ArrayList<BookCatalogue>();
	BookCatalogue book=new BookCatalogue();
	expected.add(book);	
	
	List<BookRentalLog> expected1= new ArrayList<BookRentalLog>();
	BookRentalLog bookrentallog=new BookRentalLog();
	expected1.add(bookrentallog);

	AdminDAO adminDAO = new AdminDAO();
	adminDAO.setSessionFactory(sessionFactory);

	Timestamp stamp1 = new Timestamp(System.currentTimeMillis());
	Timestamp to = new Timestamp(stamp1.getTime());
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(to);
	cal1.add(Calendar.DAY_OF_YEAR, -10);
	Timestamp from = new Timestamp(cal1.getTime().getTime());
	
	when(session.createCriteria( BookRentalLog.class,"bookRentalLog" )).thenReturn(criteria);
	when(criteria.add(Restrictions.between("bookRentalLog.issueDate", from, to))).thenReturn(criteria);
	when(criteria.createCriteria("bookRentalLog.bookCatalogue", "book")).thenReturn(criteria);	
	when(criteria.setProjection( Projections.distinct( Projections.property( "book.isbnCode")))).thenReturn(criteria);
	when(criteria.list()).thenReturn(expected1);
	when(session.createCriteria( BookCatalogue.class )).thenReturn(criteria);
	when(criteria.add(Restrictions.eq("isbnCode",anyLong()))).thenReturn(criteria);
	when(criteria.uniqueResult()).thenReturn(book);
	List<BookCatalogue> actual=adminDAO.listBooks5(from,to);
	
	assertNotNull(actual);
	assertEquals(expected, actual);	
	}
	
	@Test
	public void testeditPlansDAO() {
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		when(session.createSQLQuery(anyString())).thenReturn(sqlquery);
		adminDAO.editPlansDAO("");
		
		assertTrue(true);
	}
	
	@Test
	public void testbookCSVDAO() {
		List<String> books=new ArrayList<String>();
		String b="";
		books.add(b);
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.setSessionFactory(sessionFactory);
		when(session.createSQLQuery(anyString())).thenReturn(sqlquery);
		when(sqlquery.setParameter("stringBook", b)).thenReturn(sqlquery);
		adminDAO.bookCSVDAO(books);
		
		assertTrue(true);
	}
}
