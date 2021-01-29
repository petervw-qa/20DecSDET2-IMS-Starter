package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DatabaseUtilities;

public class CustomerDAOTest {

	private final CustomerDao DAO = new CustomerDao();

	@Before
	public void setup() {
		DatabaseUtilities.connect();
		DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void readTEST() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
		assertEquals(null, DAO.read(null));
	}

	@Test
	public void updateTEST() {
		final Customer updated = new Customer(1L, "nick", "johnson");
		assertEquals(updated, DAO.update(updated));
		assertEquals(null, DAO.update(null));

	}

	@Test
	public void deleteTEST() {
		assertEquals(1, DAO.delete(1));
	}

	@Test
	public void readLatestTEST() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void createTEST() {
		final Customer created = new Customer(2L, "nick", "johnson");
		assertEquals(created, DAO.create(created));
		assertEquals(null, DAO.create(null));
	}

	@Test
	public void readAllTEST() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertEquals(expected, DAO.readAll());
	}

}
