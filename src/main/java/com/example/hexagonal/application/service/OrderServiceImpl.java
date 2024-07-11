package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.in.OrderServiceUseCase;
import com.example.hexagonal.application.ports.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class OrderServiceImpl implements OrderServiceUseCase {

	@Inject
	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public Optional<Order> getOrder(Long id) {
		return this.orderRepository.findById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long id) {
		this.orderRepository.deleteById(id);
	}

}
