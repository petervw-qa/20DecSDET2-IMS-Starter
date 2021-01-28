package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDAOTest {
	
	private final OrderDao DAO = new OrderDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }
    
    @After
    public void testDelete() {
    	assertEquals(1, DAO.deleteOrdersItems(1L));
        assertEquals(1, DAO.delete(1L));
        
    }

    @Test
    public void testReadAll() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> listOfItems = new ArrayList<>();
		final Item item = new Item(1L,"pencil", 2.99D);
		listOfItems.add(item);
		final double price = item.getPrice();
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, customer, listOfItems, price));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> listOfItems = new ArrayList<>();
		final Item item = new Item(1L,"pencil", 2.99D);
		listOfItems.add(item);
		final double price = item.getPrice();
        assertEquals(new Order(1L, customer, listOfItems, price), DAO.readLatest());
      
    }

    @Test
    public void testRead() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> listOfItems = new ArrayList<>();
		final Item item = new Item(1L,"pencil", 2.99D);
		listOfItems.add(item);
        final long ID = 1L;
        assertEquals(new Order(ID, customer, listOfItems, 2.99D), DAO.read(ID));
        assertEquals(null, DAO.read(null));
    }
    
    @Test
    public void testCreate() {
		final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> listOfItems = new ArrayList<>();
        final Order newOrder = new Order(2L, customer, listOfItems, 0.1);
        assertEquals(newOrder, DAO.create(newOrder));
        assertEquals(null, DAO.create(null));
    }

    @Test
    public void testUpdate() {
        assertEquals(null, DAO.update(null));
    }
    
    @Test
    public void testUpdateAdd() {
    	final Customer customer = new Customer(2L, "peter", "vaughan-williams");
		final List<Item> itemList = new ArrayList<>();
		final Item item = new Item(1L,"pencil", 2.99D);
		itemList.add(item);
        final long ID = 1L;
        final Order order = new Order(ID, customer, itemList, 2.99D);
        assertEquals(order, DAO.addToOrder_NewUpdate(order, order.getId(), item.getId()));
        assertEquals(null, DAO.read(null));
    }
    
    @Test
    public void testUpdateRemove() {
    	final Customer customer = new Customer(2L, "peter", "vaughan-williams");
		final List<Item> itemList = new ArrayList<>();
		final Item item = new Item(1L,"pencil", 2.99D);
		itemList.add(item);
        final long ID = 1L;
        final Order order = new Order(ID, customer, itemList, 2.99D);
        final List<Item> emptyList = new ArrayList<>();
        assertEquals(new Order(ID, customer, emptyList, 0.0), DAO.removeFromOrder_NewUpdate(order, order.getId(), item.getId()));
        assertEquals(null, DAO.read(null));
    }
   
}