package com.impetus.serviceTest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.SubscriptionPlansDAO;
import com.impetus.model.SubscriptionPlans;

import com.impetus.service.SubscriptionPlansService;

public class SubscriptionPlansServiceTest extends TestCase{
	private SubscriptionPlansDAO subscriptionPlansDAO; 
	
	@Before 
	public void setUp() {
		subscriptionPlansDAO = mock(SubscriptionPlansDAO.class); 
	}
	
	@Test
	public void testlist() {
		List<SubscriptionPlans> expected=new ArrayList<SubscriptionPlans>();
		
		SubscriptionPlansService subscriptionPlansService=new SubscriptionPlansService();
		subscriptionPlansService.setSubscriptionPlansDAO(subscriptionPlansDAO);
		when(subscriptionPlansDAO.findAll()).thenReturn(expected);
		List<SubscriptionPlans> actual = subscriptionPlansService.list();
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
}
