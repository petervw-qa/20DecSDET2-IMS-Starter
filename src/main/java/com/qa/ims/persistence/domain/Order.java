package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Long fk_customer_id;
	private List<Item> orderItems = new ArrayList<>();

	public Order(Long id, Long fk_customer_id) {
		super();
		this.id = id;
		this.fk_customer_id = fk_customer_id;
	}

	public Order(Long fk_customer_id) {
		super();
		this.fk_customer_id = fk_customer_id;
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

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", fk_customer_id=" + fk_customer_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		return true;
	}

}
