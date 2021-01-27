package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DatabaseUtilities;

public class ItemDAOTest {

	private final ItemDao DAO = new ItemDao();

	@Before
	public void setup() {
		DatabaseUtilities.connect();
		DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void readAllTEST() {
		List<Item> ItemList = new ArrayList<>();
		ItemList.add(new Item(1L, "pencil", 0.49D));
		assertEquals(ItemList, DAO.readAll());

	}

	@Test
	public void createTEST() {
		final Item created = new Item(2L, "hole puncher", 9.99D);
		assertEquals(created, DAO.create(created));
		assertEquals(null, DAO.create(null));
	}

	@Test
	public void readTEST() {
		final long ID = 1L;
		assertEquals(new Item(ID, "pencil", 0.49D), DAO.read(ID));
		assertEquals(null, DAO.read(null));
	}

	@Test
	public void deleteTEST() {
		assertEquals(0, DAO.delete(1L));

	}

	@Test
	public void readLatestTEST() {
		assertEquals(new Item(1L, "pencil", 0.49D), DAO.readLatest());
	}

	@Test
	public void updateTEST() {
		final Item NewItem = new Item(1L, "stapler", 60000D);
		assertEquals(NewItem, DAO.update(NewItem));
		assertEquals(null, DAO.update(null));
	}
}
