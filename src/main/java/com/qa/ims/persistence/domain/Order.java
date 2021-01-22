package com.qa.ims.persistence.domain;

public class Order {
	
	private Long id;
	private Long fk_customer_id;
	
	public Order(Long id, Long fk_customer_id) {
		super();
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", fk_customer_id=" + fk_customer_id + "]";
	}
	
	
	

}
