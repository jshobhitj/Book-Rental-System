package com.impetus.daoTest;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.IndexDAO;
import com.impetus.model.BookCatalogue;

public class IndexDAOTest extends TestCase{
	
	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
		
	}

	@Test
	@SuppressWarnings({"unchecked","rawtypes"})
	public void testListCategoryDAO() {

		List expectedCategory=new ArrayList();
		expectedCategory.add("Fiction");
		expectedCategory.add("Mystery");
		
		IndexDAO indexDAO=new IndexDAO();
		indexDAO.setSessionFactory(sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createCriteria( BookCatalogue.class )).thenReturn(criteria);
		when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
		when(criteria.setProjection( Projections.distinct( Projections.property(anyString())))).thenReturn(criteria);
		when(criteria.list()).thenReturn(expectedCategory);
		List actualCategory=indexDAO.listCategoryDAO();
		
		assertNotNull(actualCategory);
		assertSame(expectedCategory, actualCategory);
	}

	@Test
	public void testlistNewReleaseDAO() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		
		IndexDAO indexDAO=new IndexDAO();
		indexDAO.setSessionFactory(sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createCriteria( BookCatalogue.class )).thenReturn(criteria);
		when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
		when(criteria.addOrder(Order.desc(anyString()))).thenReturn(criteria);
		when(criteria.setMaxResults(6)).thenReturn(criteria);
		when(criteria.list()).thenReturn(expected);
		List<BookCatalogue> actual=indexDAO.listNewReleaseDAO();

		assertNotNull(actual);
		assertSame(expected, actual);
	}

	@Test
	public void testlistMostPopularDAO() {
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		
		IndexDAO indexDAO=new IndexDAO();
		indexDAO.setSessionFactory(sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createCriteria( BookCatalogue.class )).thenReturn(criteria);
		when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
		when(criteria.addOrder(Order.desc(anyString()))).thenReturn(criteria);
		when(criteria.setMaxResults(6)).thenReturn(criteria);
		when(criteria.list()).thenReturn(expected);
		List<BookCatalogue> actual=indexDAO.listMostPopularDAO();

		assertNotNull(actual);
		assertSame(expected, actual);
	}
}
