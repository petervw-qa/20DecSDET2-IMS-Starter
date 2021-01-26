package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	@Test
	public void testToString() {
		Customer customer = new Customer(1L, "peter", "vaughan-williams");
		String expected = "id:1 first name:peter surname:vaughan-williams";
		assertEquals(expected, customer.toString());
	}

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
