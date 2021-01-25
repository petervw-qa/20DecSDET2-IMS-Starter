package com.qa.ims.persistence.dao;

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
    public void testCreate() {
        final Order created = new Order(1L, 1L);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, 1L));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(1L, 1L), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Order(ID, 1L), DAO.read(ID));
    }

    @Test
    public void testDelete() {
        assertEquals(1L, DAO.delete(1L));
    }
}