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
        final Order created = new Order(2L, 1L);
        assertEquals(created, DAO.create(created));
        assertEquals(null, DAO.create(null));
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
        final long read = 1L;
        assertEquals(new Order(read, 1L), DAO.read(read));
    }

    @Test
    public void deleteTEST() {
        assertEquals(0L, DAO.delete(1L));
    }
    
    @Test
    public void updateTEST() {
    	assertNull(DAO.update(new Order(1L, 1L)));
    }
    
    @Test
    public void addToOrder_NewUpdateTEST() {
    	final long item = 1L;
    	final long order = 2L;
        final long customer = 2L;
        assertEquals(new Order(order, customer), DAO.addToOrder_NewUpdate(new Order(order, customer), item));
    }

    @Test
    public void removeFromOrder_NewUpdateTEST() {
    	final long item = 1L;
    	final long order = 2L;
        final long customer = 2L;
        assertEquals(0L, DAO.removeFromOrder_NewUpdate(new Order(order, customer), item));
    }

}