package com.example.hexagonal.adapters.out.persistence;

import com.example.hexagonal.adapters.out.persistence.mapper.OrderEntityMapper;
import com.example.hexagonal.application.ports.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private OrderEntityMapper orderEntityMapper;

	@Override
	public Order save(Order order) {
		OrderEntity orderEntity = this.orderEntityMapper.toEntity(order);
		if (orderEntity.getId() == null) {
			this.entityManager.persist(orderEntity);
		} else {
			orderEntity = this.entityManager.merge(orderEntity);
		}
		return this.orderEntityMapper.toDomain(orderEntity);
	}

	@Override
	public Optional<Order> findById(Long id) {
		final Optional<OrderEntity> optOrderEntity = Optional
				.ofNullable(this.entityManager.find(OrderEntity.class, id));
		return Optional.ofNullable(this.orderEntityMapper.toDomain(optOrderEntity.orElse(null)));
	}

	@Override
	public List<Order> findAll() {
		return this.orderEntityMapper.toDomainList(
				this.entityManager.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class).getResultList());
	}

	@Override
	public void deleteById(Long id) {
		final Optional<Order> optOrder = this.findById(id);
		if (optOrder.isPresent()) {
			final OrderEntity orderEntity = this.orderEntityMapper.toEntity(optOrder.get());
			this.entityManager.remove(
					this.entityManager.contains(orderEntity) ? orderEntity : this.entityManager.merge(orderEntity));
		}
	}
}
