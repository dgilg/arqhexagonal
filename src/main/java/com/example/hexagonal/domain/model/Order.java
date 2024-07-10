package com.example.hexagonal.domain.model;

import java.time.LocalDateTime;

public class Order {

	private Long id;

	private LocalDateTime creationDate;
	private String status;

	public Order() {
		super();
	}

	public Order(final LocalDateTime creationDate, final String status) {
		this();
		this.creationDate = creationDate;
		this.status = status;
	}

	public Order(final Long id, final LocalDateTime creationDate, final String status) {
		this(creationDate, status);
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
