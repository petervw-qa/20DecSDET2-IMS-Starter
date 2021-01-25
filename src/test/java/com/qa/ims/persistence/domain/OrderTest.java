package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

}
