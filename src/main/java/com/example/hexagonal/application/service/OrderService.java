package com.example.hexagonal.application.service;

import com.example.hexagonal.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	Order createOrder(final Order order);

	Optional<Order> getOrder(Long id);

	List<Order> getAllOrders();

	void deleteOrder(Long id);
}
