package com.example.hexagonal.application;

import com.example.hexagonal.adapters.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OrderService {

	@Inject
	OrderRepository orderRepository;

	@Transactional
	public Order createOrder(final Order order) {
		this.orderRepository.persist(order);
		return order;
	}

	@Transactional
	public void addItemToOrder(final Long orderId, final OrderItem item) {
		final Order order = this.orderRepository.findById(orderId);
		if (order != null) {
			order.addItem(item);
			this.orderRepository.persist(order);
		}
	}

	@Transactional
	public void updateOrderStatus(final Long orderId, final String status) {
		final Order order = this.orderRepository.findById(orderId);
		if (order != null) {
			order.updateStatus(status);
			this.orderRepository.persist(order);
		}
	}

	public List<Order> getAllOrders() {
		return this.orderRepository.listAll();
	}

	public Order findOrderById(final Long id) {
		return this.orderRepository.findById(id);
	}
}
