package com.impetus.daoTest;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;



import com.impetus.dao.UserLoginDAO;
import com.impetus.model.UserDetails;

public class UserLoginDAOTest extends TestCase{

	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.createCriteria(UserDetails.class)).thenReturn(criteria);
	    when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
	}
	
	@Test
	public void testUserDetailsDAO() {
		UserDetails expected=new UserDetails();
		
		UserLoginDAO userLoginDAO= new UserLoginDAO();
		userLoginDAO.setSessionFactory(sessionFactory);
		when(criteria.uniqueResult()).thenReturn(expected);
		UserDetails actual=userLoginDAO.userDetailsDAO("s@s.com");
		
		assertNotNull(actual);
		assertSame(expected, actual);

	}

}
