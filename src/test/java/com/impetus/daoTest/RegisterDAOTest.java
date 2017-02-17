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




import com.impetus.dao.RegisterDAO;
import com.impetus.model.Status;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.model.UserDetails;
import com.impetus.model.UserRoles;

public class RegisterDAOTest extends TestCase{

	private SessionFactory sessionFactory; 
	private Session session; 
	private Criteria criteria;
	
	@Before 
	public void setUp() {
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		criteria = mock(Criteria.class);
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.createCriteria(SubscriptionPlans.class)).thenReturn(criteria);
	    when(session.createCriteria(UserDetails.class)).thenReturn(criteria);
	    when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
	}
	
	@Test
	public void testsubscriptionPlan() {
		SubscriptionPlans expectedSubscription= new SubscriptionPlans();
		expectedSubscription.setSubscriptionPlan(2);
		expectedSubscription.setPeriod(2);
		expectedSubscription.setAmount(2500);
		expectedSubscription.setNoOfBooks(6);
		expectedSubscription.setStatus(Status.ACTIVE);
		
		RegisterDAO registerDAO= new RegisterDAO();
		registerDAO.setSessionFactory(sessionFactory);
		when(criteria.uniqueResult()).thenReturn(expectedSubscription);
		SubscriptionPlans actualSubscription=registerDAO.subscriptionPlan(2);
		
		assertNotNull(actualSubscription);
		assertSame(expectedSubscription, actualSubscription);
	}
	
	@Test
	public void testAvailableDAO() {
		List<UserDetails> expected= new ArrayList<UserDetails>();
		UserDetails user=new UserDetails();
		expected.add(user);
		
		RegisterDAO registerDAO= new RegisterDAO();
		registerDAO.setSessionFactory(sessionFactory);
		when(criteria.list()).thenReturn(expected);
		boolean result=registerDAO.availableDAO("");
		
		assertNotNull(result);
		assertTrue(result);
		
		List<UserDetails> expected1= new ArrayList<UserDetails>();

		when(criteria.list()).thenReturn(expected1);
		boolean result1=registerDAO.availableDAO("");
		
		assertNotNull(result1);
		assertFalse(result1);		
	}
	
	@Test
	public void testregisterDAO() {
		UserDetails user=new UserDetails();
		
		RegisterDAO registerDAO= new RegisterDAO();
		registerDAO.setSessionFactory(sessionFactory);
		registerDAO.registerDAO(user);
		
		assertTrue(true);
	}
	
	@Test
	public void testregisterRole() {
		UserRoles user=new UserRoles();
		
		RegisterDAO registerDAO= new RegisterDAO();
		registerDAO.setSessionFactory(sessionFactory);
		registerDAO.registerRole(user);
		
		assertTrue(true);
	}
	
	@Test
	public void testregisterPlanDAO() {
		SubscriptionLog user=new SubscriptionLog();
		
		RegisterDAO registerDAO= new RegisterDAO();
		registerDAO.setSessionFactory(sessionFactory);
		registerDAO.registerPlanDAO(user);
		
		assertTrue(true);
	}
}
