package com.impetus.daoTest;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.AddToBookshelfDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.UserDetails;

public class AddToBookshelfDAOTest extends TestCase{

	private SessionFactory sessionFactory; 
	private Session session; 
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	}
	
	@Test
	public void test() {
		long isbnCode=0;
		BookCatalogue book=new BookCatalogue();
		book.setIsbnCode(isbnCode);
		book.setRecommentCount(1);
		book.setNoOfCopiesAvailable(1);
		BookRentalLog bookRequest=new BookRentalLog();
		UserDetails user=new UserDetails();
		bookRequest.setBookCatalogue(book);
		bookRequest.setUserDetails(user);
		
		AddToBookshelfDAO addToBookshelfDAO=new AddToBookshelfDAO();
		addToBookshelfDAO.setSessionFactory(sessionFactory);
		when((BookCatalogue)session.get(BookCatalogue.class, isbnCode)).thenReturn(book);
		addToBookshelfDAO.addToBookshelfDAO(bookRequest);
		
		assertTrue(true);
	}

}
