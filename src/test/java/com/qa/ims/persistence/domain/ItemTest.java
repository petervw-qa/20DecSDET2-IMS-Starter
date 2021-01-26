package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTest {
	
	@Test
	public void toStringTEST() {
		Item item = new Item(1L, "pencil", 100);
		String expected = "id=1 name=pencil price=100.0";
		assertEquals(expected, item.toString());
	}
	
	@Test
	public void firstConstructorTEST() {
		Item item = new Item("pencil", 100);
		assertEquals("pencil", item.getName());
		assertEquals(100.0, item.getPrice(), 0.1);
	}
	
	@Test
	public void secondConstructorTEST() {
		Item item = new Item(1L, "super rubber", 100);
		assertEquals(Long.valueOf("1"), item.getId());
		assertEquals("super rubber", item.getName());
		assertEquals(100.0, item.getPrice(), 0.1);
		
	}


}
