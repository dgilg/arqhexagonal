package com.example.hexagonal.adapters.out.persistence;

import com.example.hexagonal.adapters.out.persistence.mapper.OrderEntityMapper;
import com.example.hexagonal.application.ports.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderAdapter implements OrderRepository {

	@Inject
	private JpaOrderRepository jpaOrderRepository;

	@Inject
	private OrderEntityMapper orderEntityMapper;

	@Override
	public Order save(Order order) {
		final OrderEntity orderEntity = this.orderEntityMapper.toEntity(order);
		return this.orderEntityMapper.toDomain(this.jpaOrderRepository.save(orderEntity));
	}

	@Override
	public Optional<Order> findById(Long id) {
		final Optional<OrderEntity> optOrderEntity = this.jpaOrderRepository.findById(id);
		return Optional.ofNullable(this.orderEntityMapper.toDomain(optOrderEntity.orElse(null)));
	}

	@Override
	public List<Order> findAll() {
		return this.orderEntityMapper.toDomainList(this.jpaOrderRepository.findAll());
	}

	@Override
	public void deleteById(Long id) {
		this.jpaOrderRepository.deleteById(id);
	}
}
