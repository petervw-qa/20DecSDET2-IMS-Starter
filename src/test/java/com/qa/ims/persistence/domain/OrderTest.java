package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void toStringTEST() {
		Order order = new Order(100L, 100L);
		String expected = "id= 100 fk_customer_id= 100";
		assertEquals(expected, order.toString());
	}

	@Test
	public void firstConstructorTEST() {
		Order order = new Order(100L, 100L);
		assertEquals(Long.valueOf(100), order.getId());
		assertEquals(Long.valueOf(100), order.getFk_customer_id());
	}

	@Test
	public void secondConstructorTEST() {
		Order order = new Order(500L);
		assertEquals(Long.valueOf(500), order.getFk_customer_id());
	}

	@Test
	public void equalsTEST() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void setIdTEST() {
		Order order = new Order(100L, 100L);
		order.setId(5000L);
		assertEquals(Long.valueOf("5000"), order.getId());

	}

	@Test
	public void setFk_customer_idTEST() {
		Order order = new Order(100L, 100L);
		order.setFk_customer_id(5000L);
		assertEquals(Long.valueOf("5000"), order.getFk_customer_id());

	}

}
