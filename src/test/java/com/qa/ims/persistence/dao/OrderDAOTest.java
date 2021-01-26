package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrderDAOTest {

    private final OrderDao DAO = new OrderDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void createTEST() {
        final Order created = new Order(1L, 1L);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void readAllTEST() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, 1L));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void readLatestTEST() {
        assertEquals(new Order(1L, 1L), DAO.readLatest());
    }

    @Test
    public void readTEST() {
        final long ID = 1L;
        assertEquals(new Order(ID, 1L), DAO.read(ID));
    }

    @Test
    public void deleteTEST() {
        assertEquals(1L, DAO.delete(1L));
    }
    
    @Test
    public void updateTEST() {
    	assertNull(DAO.update(new Order(1L, 1L)));
    }
    
    @Test
    public void addToOrder_NewUpdateTEST() {
        final long orderID = 2L;
        final long customerID = 2L;
        final long itemID = 1L;
        assertEquals(new Order(orderID, customerID), DAO.addToOrder_NewUpdate(new Order(orderID, customerID), itemID));
    }

    @Test
    public void removeFromOrder_NewUpdateTEST() {
        final long orderID = 2L;
        final long customerID = 1L;
        final long itemID = 1L;
        assertEquals(1L, DAO.removeFromOrder_NewUpdate(new Order(orderID, customerID), itemID));
    }

    @Test
    public void calculateOrderTotalCostTEST() {
        final long orderID = 1L;
        final long customerID = 1L;
        final Order order = new Order(orderID, customerID);
        final Double total = DAO.calculateOrderTotalCost(order);
        final Double expected = Double.parseDouble("0.49");
        assertEquals(expected, total);
    }
}