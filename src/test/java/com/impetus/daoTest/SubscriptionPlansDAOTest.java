package com.impetus.daoTest;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.SubscriptionPlansDAO;
import com.impetus.model.SubscriptionPlans;

public class SubscriptionPlansDAOTest extends TestCase{

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
	    when(criteria.add((Criterion) anyObject())).thenReturn(criteria);
	    when(criteria.addOrder(Order.asc(anyString()))).thenReturn(criteria);
	}
	
	@Test
	public void testfindAll() {
		List<SubscriptionPlans> expected=new ArrayList<SubscriptionPlans>();
		SubscriptionPlans subscription=new SubscriptionPlans();
		expected.add(subscription);
		
		SubscriptionPlansDAO searchPlanDAO =new SubscriptionPlansDAO();
		searchPlanDAO.setSessionFactory(sessionFactory);
		when(criteria.list()).thenReturn(expected);
		List<SubscriptionPlans> actual= searchPlanDAO.findAll();
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}

}
