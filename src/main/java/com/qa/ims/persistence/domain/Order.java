package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Customer orderCustomer;
	private Double orderValue;
	private List<Item> orderItems = new ArrayList<>();

	public Order(Customer orderCustomer, Double orderValue) {
		super();
		this.orderCustomer = orderCustomer;
		this.orderValue = orderValue;
	}

	public Order(Long id, Customer orderCustomer, Double orderValue) {
		super();
		this.id = id;
		this.orderCustomer = orderCustomer;
		this.orderValue = orderValue;
	}

	public Order(Long id, Customer orderCustomer, Double orderValue, List<Item> orderItems) {
		super();
		this.id = id;
		this.orderCustomer = orderCustomer;
		this.orderValue = orderValue;
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public Double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		StringBuilder order = new StringBuilder();
		order.append(String.format("%s: %s %s", this.id, orderCustomer.getFirstName(), orderCustomer.getSurname()));
		if (this.orderItems.isEmpty()) {
			order.append("\n -> No items have been found in this order.");
		} else {
			order.append(String.format(" - value = £%.2f", this.orderValue));
			this.orderItems.forEach(item -> {
				order.append("\n -> ");
				order.append(String.format("%s: £%.2f", item.getName(), item.getValue()));
			});
		}
		return order.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderCustomer == null) ? 0 : orderCustomer.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((orderValue == null) ? 0 : orderValue.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderCustomer == null) {
			if (other.orderCustomer != null)
				return false;
		} else if (!orderCustomer.equals(other.orderCustomer))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (orderValue == null) {
			if (other.orderValue != null)
				return false;
		} else if (!orderValue.equals(other.orderValue))
			return false;
		return true;
	}

}
