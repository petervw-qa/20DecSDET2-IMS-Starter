package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Long fk_customer_id;
	private Double orderValue;
	private List<Item> orderItems = new ArrayList<>();

	public Order(Long id, Long fk_customer_id, Double orderValue) {
		super();
		this.id = id;
		this.fk_customer_id = fk_customer_id;
		this.orderValue = orderValue;
	}

	public Order(Long id, Long fk_customer_id, Double orderValue, List<Item> orderItems) {
		super();
		this.id = id;
		this.fk_customer_id = fk_customer_id;
		this.orderValue = orderValue;
		this.orderItems = orderItems;
	}

	public Order(Long id, Long fk_customer_id) {
		super();
		this.id = id;
		this.fk_customer_id = fk_customer_id;
	}

	public Order(Long customer_id) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFk_customer_id() {
		return fk_customer_id;
	}

	public void setFk_customer_id(Long fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
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
		return "Order [id=" + id + ", fk_customer_id=" + fk_customer_id + ", orderValue=" + orderValue + ", orderItems="
				+ orderItems + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
