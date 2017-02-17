package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.RegisterDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.SubscriptionPlans;
import com.impetus.service.IndexService;
import com.impetus.service.RegisterService;

public class RegisterServiceTest extends TestCase{
	private RegisterDAO registerDAO;
	private IndexService indexService;
	
	@Before 
	public void setUp() {
		registerDAO = mock(RegisterDAO.class); 
		indexService = mock(IndexService.class); 
	}
	
	@Test
	public void testregisterService() {
		
		RegisterService registerService=new RegisterService();
		registerService.setRegisterDAO(registerDAO);
		registerService.setIndexService(indexService);
		List<BookCatalogue> bookMostPopular=new ArrayList<BookCatalogue>();
		bookMostPopular.add(new BookCatalogue());
		bookMostPopular.add(new BookCatalogue());
		bookMostPopular.add(new BookCatalogue());
		bookMostPopular.add(new BookCatalogue());
		bookMostPopular.add(new BookCatalogue());
		bookMostPopular.add(new BookCatalogue());
		when(indexService.listMostPopularService()).thenReturn(bookMostPopular);
		registerService.registerService("firstName", "lastName", "permanentAddress", 9999, "userEmailId", "userPassword", "language");
		
		assertTrue(true);		
	}
	
	@Test
	public void testregisterPlanService() {
		Integer subscriptionPlan=2;  
		SubscriptionPlans subscrptionPlan=new SubscriptionPlans();
		subscrptionPlan.setPeriod(2);
		
		RegisterService registerService=new RegisterService();
		registerService.setRegisterDAO(registerDAO);
		when(registerDAO.subscriptionPlan(subscriptionPlan)).thenReturn(subscrptionPlan);
		registerService.registerPlanService("userEmailId", 2);
		
		assertTrue(true);
		
	}
	
	@Test
	public void testavailableService() {
		
		RegisterService registerService=new RegisterService();
		registerService.setRegisterDAO(registerDAO);
		registerService.availableService("");
		
		assertTrue(true);		
	}

}
