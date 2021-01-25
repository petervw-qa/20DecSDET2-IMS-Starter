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

}
