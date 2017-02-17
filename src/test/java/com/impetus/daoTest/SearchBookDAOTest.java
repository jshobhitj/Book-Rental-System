package com.impetus.daoTest;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.SearchBookDAO;
import com.impetus.model.BookCatalogue;

public class SearchBookDAOTest extends TestCase{

	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.createCriteria(BookCatalogue.class)).thenReturn(criteria);
	    when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
	}
	
	@Test
	public void testfindAllDAO() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		BookCatalogue book=new BookCatalogue();
		expected.add(book);
		
		SearchBookDAO searchBookDAO =new SearchBookDAO();
		searchBookDAO.setSessionFactory(sessionFactory);
		when(criteria.list()).thenReturn(expected);
		List<BookCatalogue> actual= searchBookDAO.findAllDAO("the");
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}

}
