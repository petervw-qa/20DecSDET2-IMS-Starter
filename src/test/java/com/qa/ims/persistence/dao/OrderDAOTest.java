package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDAOTest {
	
	private final OrderDao DAO = new OrderDao(new CustomerDao(), new ItemDao());
	private Order testOrder;
	private Item testItem;

    @Before
    public void setup() {
    	DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
        List<Item> listOfItems = new ArrayList<>();
        testItem = new Item(1L,"pencil", 2.99D);
		listOfItems.add(testItem);
		Customer customer = new Customer(1L, "jordan", "harrison");
		testOrder = new Order(1L, customer, listOfItems, 2.99D);
    }
    
    @Test
    public void testDelete() {
    	assertEquals(0, DAO.deleteOrdersItems(1L));
        assertEquals(0, DAO.delete(1L));     
    }

    @Test
    public void testRead() {
        assertEquals(testOrder, DAO.read(1L));
    }
    
    @Test
    public void testReadLatest() {
        assertEquals(testOrder, DAO.readLatest());
    }
    
    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(testOrder);
        assertEquals(expected, DAO.readAll());
    }
    
    @Test
    public void testCreate() {
		final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> listOfItems = new ArrayList<>();
        final Order newOrder = new Order(2L, customer, listOfItems, 0.0);
        assertEquals(newOrder, DAO.create(newOrder));
    }

    @Test
    public void testUpdate() {
        assertEquals(null, DAO.update(null));
    }
    
    @Test
    public void testUpdateAdd() {
    	testOrder.getOrdersItems().add(testItem);
        assertEquals(testOrder, DAO.addToOrder_NewUpdate(testOrder.getId(), testItem.getId()));
    }
    
    @Test
    public void testUpdateRemove() {
    	testOrder.getOrdersItems().remove(testItem);
    	assertEquals(testOrder, DAO.removeFromOrder_NewUpdate(testOrder.getId(), testItem.getId()));
    }
   
}