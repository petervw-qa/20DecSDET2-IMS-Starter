package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Customer fk_customer_id;
	private double totalPrice;
	private List<Item> ordersItems = new ArrayList<>();

	public Order() {
	}

	public Order(Long id, Customer fk_customer_id, List<Item> ordersItems, double totalPrice) {
		this.setId(id);
		this.setFk_customer_id(fk_customer_id);
		this.setOrdersItems(ordersItems);
		this.setTotalPrice(totalPrice);
	}

	public Order(Long id, Customer fk_customer_id, double totalPrice, List<Item> ordersItems) {
		this.setId(id);
		this.setFk_customer_id(fk_customer_id);
		this.setOrdersItems(ordersItems);
		this.setTotalPrice(totalPrice);
	}

	public Order(Long id, List<Item> ordersItems) {
		super();
		this.id = id;
		this.ordersItems = ordersItems;
	}

	public Order(Customer fk_customer_id) {
		this.setFk_customer_id(fk_customer_id);
	}

	public Order(Long id, Customer fk_customer_id) {
		this.setId(id);
		this.setFk_customer_id(fk_customer_id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getFk_customer_id() {
		return fk_customer_id;
	}

	public void setFk_customer_id(Customer Fk_customer_id) {
		this.fk_customer_id = Fk_customer_id;
	}

	public List<Item> getOrdersItems() {
		return ordersItems;
	}

	public void setOrdersItems(List<Item> ordersItems) {
		this.ordersItems = ordersItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + fk_customer_id + ", ordersItems=" + ordersItems + ", totalPrice="
				+ totalPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ordersItems == null) ? 0 : ordersItems.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (fk_customer_id == null) {
			if (other.fk_customer_id != null)
				return false;
		} else if (!fk_customer_id.equals(other.fk_customer_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ordersItems == null) {
			if (other.ordersItems != null)
				return false;
		} else if (!ordersItems.equals(other.ordersItems))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

}
