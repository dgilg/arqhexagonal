package com.example.hexagonal.adapters.out.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "OrderEntity")
@Entity
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime creationDate;
	private String status;

	public OrderEntity() {
	}

	public OrderEntity(final LocalDateTime creationDate, final String status) {
		this.creationDate = creationDate;
		this.status = status;
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
