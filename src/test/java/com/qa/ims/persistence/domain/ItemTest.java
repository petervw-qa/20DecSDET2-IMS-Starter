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


}
