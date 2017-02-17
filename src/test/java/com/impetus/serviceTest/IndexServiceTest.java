package com.impetus.serviceTest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.IndexDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.service.IndexService;

public class IndexServiceTest extends TestCase{
	private IndexDAO indexDAO; 
	
	@Before 
	public void setUp() {
		indexDAO = mock(IndexDAO.class); 
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testlistCategoryService() {		
		List expected=new ArrayList();
		
		IndexService indexService=new IndexService();
		indexService.setIndexDAO(indexDAO);
		when(indexDAO.listCategoryDAO()).thenReturn(expected);
		List actual = indexService.listCategoryService();
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
	
	@Test
	public void testlistNewReleaseService() {		
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		
		IndexService indexService=new IndexService();
		indexService.setIndexDAO(indexDAO);
		when(indexDAO.listNewReleaseDAO()).thenReturn(expected);
		List<BookCatalogue> actual = indexService.listNewReleaseService();
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}

	@Test
	public void testlistMostPopularService() {		
		List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
		
		IndexService indexService=new IndexService();
		indexService.setIndexDAO(indexDAO);
		when( indexDAO.listMostPopularDAO()).thenReturn(expected);
		List<BookCatalogue> actual = indexService.listMostPopularService();
		
		assertNotNull(actual);
		assertSame(expected, actual);
	}
}
