package com.example.hexagonal.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "\"OrderItem\"")
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String product;
	private int quantity;
	private BigDecimal price;

	public OrderItem() {
	}

	public OrderItem(final String product, final int quantity, final BigDecimal price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public String getProduct() {
		return this.product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setProduct(final String product) {
		this.product = product;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}
}
