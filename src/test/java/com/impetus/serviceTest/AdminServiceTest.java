package com.impetus.serviceTest;

import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.impetus.dao.AdminDAO;
import com.impetus.model.BookCatalogue;
import com.impetus.model.BookRentalLog;
import com.impetus.model.SubscriptionLog;
import com.impetus.model.SubscriptionPlans;
import com.impetus.service.AdminService;



public class AdminServiceTest  extends TestCase{
    private AdminDAO adminDAO;
    
    @Before 
    public void setUp() {
        adminDAO = mock(AdminDAO.class); 
    }
    
    @Test
    public void testlistBooksService() {
        List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listBooksDAO()).thenReturn(expected);
        List<BookCatalogue> actual=adminService.listBooksService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    @SuppressWarnings("rawtypes")
    public void testlistTitleService() {
       
        List expected=new ArrayList();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listTitleDAO()).thenReturn(expected);
        List actual=adminService.listTitleService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    @SuppressWarnings("rawtypes")
    public void testlistAuthorService() {
       
        List expected=new ArrayList();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listAuthorDAO()).thenReturn(expected);
        List actual=adminService.listAuthorService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    @SuppressWarnings("rawtypes")
    public void testlistCategoryService() {
       
        List expected=new ArrayList();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listCategoryDAO()).thenReturn(expected);
        List actual=adminService.listCategoryService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testupdateBookService() {
        BookCatalogue book=new BookCatalogue();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.searchBookDAO(anyLong())).thenReturn(book);
        adminService.updateBookService(1," title"," author", "category"," image", "bookDescription", 2, true);
        
        assertTrue(true);      
    }
    
    @Test
    public void testsearchIssuedBookService() {
        Integer expected=1;
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.searchIssuedBookDAO(anyLong())).thenReturn(expected);
        Integer actual=adminService.searchIssuedBookService(1);
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testdeleteBookService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO); 
        adminService.deleteBookService(1);
        
        assertTrue(true);   
    }
    
    @Test
    public void testaddBookService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        adminService.addBookService(1," title", "author", "category", "image", "bookDescription", "publisher", new Date(0), 2, 2);;
        
        assertTrue(true);      
    }
    
    @Test
    public void testactiveSubscriptionsService() {
        List<SubscriptionLog> expected=new ArrayList<SubscriptionLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.activeSubscriptionsDAO()).thenReturn(expected);
        List<SubscriptionLog> actual=adminService.activeSubscriptionsService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testsubscriptionPlansService() {
        List<SubscriptionPlans> expected=new ArrayList<SubscriptionPlans>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.subscriptionPlansDAO()).thenReturn(expected);
        List<SubscriptionPlans> actual=adminService.subscriptionPlansService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testpendingDeliveryService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.pendingDeliveryDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.pendingDeliveryService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testpendingReturnService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.pendingReturnDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.pendingReturnService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testcancelledDeliveryService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.cancelledDeliveryDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.cancelledDeliveryService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testcancelledReturnService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.cancelledReturnDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.cancelledReturnService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testclosedDeliveryService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.closedDeliveryDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.closedDeliveryService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testclosedReturnService() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.closedReturnDAO()).thenReturn(expected);
        List<BookRentalLog> actual=adminService.closedReturnService();
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testbookDeliveredService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        adminService.bookDeliveredService(anyLong());
        
        assertTrue(true);    
    }
    
    @Test
    public void testbookReturnedService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        adminService.bookReturnedService(anyLong());
        
        assertTrue(true);    
    }
    
    @Test
    public void testavailableService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        boolean actual=adminService.availableService(anyLong());
        
        assertFalse(actual);
        
        BookCatalogue book = new BookCatalogue();
        when(adminDAO.searchBookDAO(anyLong())).thenReturn(book);
        boolean actual1=adminService.availableService(anyLong());
        
        assertTrue(actual1);
    }
    
    @Test
    public void testgetBook() {
        BookCatalogue expected=new BookCatalogue();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.searchBookDAO(anyLong())).thenReturn(expected);
        BookCatalogue actual=adminService.getBook(anyLong());
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testeditPlansService() {
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        adminService.editPlansService("");
        
        assertTrue(true);    
    }
    
    @Test
    public void testlistBooks() {
        List<BookRentalLog> expected=new ArrayList<BookRentalLog>();
        List<BookRentalLog> book=new ArrayList<BookRentalLog>();
        expected.addAll(book);
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listBooks1(new Timestamp(0), new Timestamp(0))).thenReturn(book);
        List<BookRentalLog> actual=adminService.listBooks("all", "value", new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual);
        assertEquals(expected, actual);
        
        when(adminDAO.listBooks2("value",new Timestamp(0), new Timestamp(0))).thenReturn(book);
        List<BookRentalLog> actual1=adminService.listBooks("title", "value", new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual1);
        assertEquals(expected, actual1);  
        
        when(adminDAO.listBooks3("value",new Timestamp(0), new Timestamp(0))).thenReturn(book);
        List<BookRentalLog> actual2=adminService.listBooks("author", "value", new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual2);
        assertEquals(expected, actual2);
        
        when(adminDAO.listBooks4("value",new Timestamp(0), new Timestamp(0))).thenReturn(book);
        List<BookRentalLog> actual3=adminService.listBooks("category", "value", new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual3);
        assertEquals(expected, actual3);
    }
    
    @Test
    public void testlistBooks1() {
        List<BookCatalogue> expected=new ArrayList<BookCatalogue>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listBooks5(new Timestamp(0), new Timestamp(0))).thenReturn(expected);
        List<BookCatalogue> actual=adminService.listBooks1(new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @SuppressWarnings({"rawtypes","unchecked"})
    @Test
    public void testlistBooksCount() {
        List expected=new ArrayList();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        when(adminDAO.listBooks6(new Timestamp(0), new Timestamp(0))).thenReturn(expected);
        List actual=adminService.listBooksCount(new Timestamp(0), new Timestamp(0));
        
        assertNotNull(actual);
        assertSame(expected, actual);       
    }
    
    @Test
    public void testbookCSVService() {
        List<String> book=new ArrayList<String>();
        
        AdminService adminService=new AdminService();
        adminService.setAdminDAO(adminDAO);
        adminService.bookCSVService(book);
        
        assertTrue(true);    
    }
}
