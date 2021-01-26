package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	@Test
	public void toStringTEST() {
		Customer customer = new Customer(1L, "Peter", "Vaughan-Williams");
		String expected = "id:1 first name:Peter surname:Vaughan-Williams";
		assertEquals(expected, customer.toString());
	}

	@Test
	public void equalsTEST() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	
	@Test
	public void firstConstructorTEST() {
		Customer customer = new Customer("Peter", "Vaughan-Williams");
		assertEquals("Peter", customer.getFirstName());
		assertEquals("Vaughan-Williams", customer.getSurname());

	}

}
