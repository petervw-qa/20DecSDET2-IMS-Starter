package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void firstConstructorTEST() {
		Customer customer = new Customer("John", "Smith");
		Order order = new Order(3L, customer);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(customer, order.getFk_customer_id());
	}

	@Test
	public void secondConstructorTEST() {
		Customer customer = new Customer("Jane", "Smith");
		Order order = new Order(customer);

		assertEquals(customer, order.getFk_customer_id());
	}

	@Test
	public void thirdConstuctorTEST() {
		Order order = new Order();

		assertNotNull(order);
	}

	@Test
	public void fourthConstructorTEST() {
		Customer customer = new Customer("Abraham", "Lincoln");
		Item Axe = new Item("Axe", 9.99);
		Item Wood = new Item(3L, "Wood", 1.45);
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(Axe);
		listOfItems.add(Wood);
		double totalPrice = Axe.getPrice() + Wood.getPrice();
		Order order = new Order(3L, customer, totalPrice, listOfItems);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalPrice(), 0.01);
		assertEquals(listOfItems, order.getOrdersItems());
		assertEquals(customer, order.getFk_customer_id());
	}

	@Test
	public void fifthConstructorTEST() {
		Customer customer = new Customer("Abraham", "Lincoln");
		Item Axe = new Item("Axe", 9.99);
		Item Wood = new Item(3L, "Wood", 1.45);
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(Axe);
		listOfItems.add(Wood);
		double totalPrice = Axe.getPrice() + Wood.getPrice();
		Order order = new Order(3L, customer, listOfItems, totalPrice);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalPrice(), 0.01);
		assertEquals(listOfItems, order.getOrdersItems());
		assertEquals(customer, order.getFk_customer_id());
	}

	@Test
	public void sixthConstructorTEST() {
		Item Wood = new Item(3L, "Wood", 1.45);
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(Wood);
		Order order = new Order(3L, listOfItems);
		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(listOfItems, order.getOrdersItems());

	}

	@Test
	public void equalsTEST() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void toStringTEST() {
		Customer customer = new Customer(3L, "Abraham", "Lincoln");
		Item Axe = new Item(4L, "Axe", 9.99);
		Item Wood = new Item(3L, "Wood", 1.45);
		List<Item> ListOfItems = new ArrayList<>();
		ListOfItems.add(Axe);
		ListOfItems.add(Wood);
		double totalPrice = Axe.getPrice() + Wood.getPrice();
		Order order = new Order(3L, customer, ListOfItems, totalPrice);

		assertEquals(
				"Order [id=3, customer=id:3 first name:Abraham surname:Lincoln, ordersItems=[id=4 name=Axe price=9.99, id=3 name=Wood price=1.45], totalPrice=11.44]",
				order.toString());
	}

}
