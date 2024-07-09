package com.example.hexagonal.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "\"Order\"")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime creationDate;
	private String status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	public Order() {
	}

	public Order(final LocalDateTime creationDate, final String status) {
		this.creationDate = creationDate;
		this.status = status;
	}

	public void addItem(final OrderItem item) {
		this.items.add(item);
	}

	public void updateStatus(final String status) {
		this.status = status;
	}

	public Long getId() {
		return this.id;
	}

	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}

	public String getStatus() {
		return this.status;
	}

	public List<OrderItem> getItems() {
		return this.items;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setCreationDate(final LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setItems(final List<OrderItem> items) {
		this.items = items;
	}
}
