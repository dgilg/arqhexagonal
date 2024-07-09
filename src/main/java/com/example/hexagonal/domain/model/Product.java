package com.example.hexagonal.domain.model;

public class Product {
	private int id;

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	private String name;
	private double price;

	public Product(final int id, final String name, final double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}
}
