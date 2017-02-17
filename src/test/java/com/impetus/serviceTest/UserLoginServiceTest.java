package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.UserLoginDAO;
import com.impetus.model.UserDetails;

import com.impetus.service.UserLoginService;

public class UserLoginServiceTest extends TestCase{
	
	private UserLoginDAO userLoginDAO; 
	
	@Before 
	public void setUp() {
		userLoginDAO = mock(UserLoginDAO.class); 
	}
	
	@Test
	public void test() {
		UserDetails expected=new UserDetails();
		String userId="";
		
		UserLoginService userLoginService=new UserLoginService();
		userLoginService.setUserLoginDAO(userLoginDAO);
		when(userLoginDAO.userDetailsDAO(userId)).thenReturn(expected);
		UserDetails actual = userLoginService.userLoginService(userId);
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}

}
