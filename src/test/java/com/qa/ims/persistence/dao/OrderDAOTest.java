package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDAOTest {

    private final OrderDao DAO = new OrderDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void createTEST() {
    	final Customer TestCustomer = new Customer(1L, "jordan", "harrison");
		final List<Item> ListOfItems = new ArrayList<>();
        final Order created = new Order(2L, TestCustomer, ListOfItems, 0.0);
        
        assertEquals(created, DAO.create(created));
        assertEquals(null, DAO.create(null));
    }

    @Test
    public void readAllTEST() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> ListOfItems = new ArrayList<>();
		final Item item = new Item(1L, "Super Glue", 2.99D);
		ListOfItems.add(item);
		final double cost = item.getPrice();
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, customer, ListOfItems, cost));
        
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void readLatestTEST() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> ListOfItems = new ArrayList<>();
		final Item item = new Item(1L, "Super Glue", 2.99D);
		ListOfItems.add(item);
		final double price = item.getPrice();
		
        assertEquals(new Order(1L, customer, ListOfItems, price), DAO.readLatest());
    }

    @Test
    public void readTEST() {
        final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> ListOfItems = new ArrayList<>();
		final Item item = new Item(1L, "Super Glue", 2.99D);
		ListOfItems.add(item);
        final long ID = 1L;
        
        assertEquals(new Order(ID, customer, ListOfItems, 2.99D), DAO.read(ID));
        assertEquals(null, DAO.read(null));
    }

    @Test
    public void deleteTEST() {
    	
        assertEquals(0L, DAO.delete(1L));
    }
    
    @Test
    public void updateTEST() {
    	
    	assertEquals(null, DAO.update(null));
    }
    
    @Test
    public void addToOrder_NewUpdateTEST() {
    	final Customer customer = new Customer(2L, "Peter", "Vaughan-Williams");
		final List<Item> ListOfItems = new ArrayList<>();
		final Item item = new Item(1L, "Super Glue", 2.99D);
		ListOfItems.add(item);
        final long ID = 1L;
        final Order order = new Order(ID, customer, ListOfItems, 2.99D);
        
        assertEquals(order, DAO.addToOrder_NewUpdate(order, order.getId(), item.getId()));
        assertEquals(null, DAO.read(null));
    }

    @Test
    public void removeFromOrder_NewUpdateTEST() {
    	final Customer customer = new Customer(1L, "jordan", "harrison");
    	final List<Item> ListOfItems = new ArrayList<>();
		final Item item = new Item(1L, "Super Glue", 2.99D);
		ListOfItems.add(item);
        final long ID = 1L;
        final Order order = new Order(ID, customer, ListOfItems, 2.99D);
        final List<Item> NewList = new ArrayList<>();
        
        assertEquals(new Order(ID, customer, NewList, 0.0), DAO.removeFromOrder_NewUpdate(order, order.getId(), item.getId()));
        assertEquals(null, DAO.read(null));
    }

}