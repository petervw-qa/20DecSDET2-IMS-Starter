package com.qa.ims.persistence.domain;

public class Item {
	
	// variables 
	private Long id;
	private String name;
	private double value;
	
	// constructors 
	public Item(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Item(Long id, String name, double value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
	
	
	
	

}
