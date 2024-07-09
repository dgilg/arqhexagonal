package com.example.hexagonal.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String city;
	private String zipCode;

	public Address() {
	}

	public Address(final String street, final String city, final String zipCode) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return this.street;
	}

	public String getCity() {
		return this.city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
}
