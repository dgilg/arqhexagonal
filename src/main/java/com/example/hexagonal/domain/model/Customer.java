package com.example.hexagonal.domain.model;

import jakarta.persistence.*;

@Table(name = "\"Customer\"")
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Embedded
	private Address address;

	public Customer() {
	}

	public Customer(final String name, final Address address) {
		this.name = name;
		this.address = address;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Address getAddress() {
		return this.address;
	}
}
