package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.SearchBookDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.service.SearchBookService;

public class SearchBookServiceTest extends TestCase {	
	private SearchBookDAO searchBookDAO; 
	
	@Before 
	public void setUp() {
		searchBookDAO = mock(SearchBookDAO.class); 
	}
	
	@Test
	public void testlistCategoryService() {		
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		String searchBook="";
		
		SearchBookService searchBookService=new SearchBookService();
		searchBookService.setSearchBookDAO(searchBookDAO);
		when(searchBookDAO.findAllDAO(searchBook)).thenReturn(expected);
		List<BookCatalogue> actual = searchBookService.findAllService(searchBook);
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
}
