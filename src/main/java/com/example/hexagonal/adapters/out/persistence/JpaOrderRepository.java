package com.example.hexagonal.adapters.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaOrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public OrderEntity save(OrderEntity orderEntity) {
		if (orderEntity.getId() == null) {
			this.entityManager.persist(orderEntity);
		} else {
			orderEntity = this.entityManager.merge(orderEntity);
		}
		return orderEntity;
	}

	public Optional<OrderEntity> findById(Long id) {
		return Optional.ofNullable(this.entityManager.find(OrderEntity.class, id));
	}

	public List<OrderEntity> findAll() {
		return this.entityManager.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class).getResultList();
	}

	public void deleteById(Long id) {
		final Optional<OrderEntity> optOrderEntity = this.findById(id);
		if (optOrderEntity.isPresent()) {
			final OrderEntity orderEntity = optOrderEntity.get();
			this.entityManager.remove(
					this.entityManager.contains(orderEntity) ? orderEntity : this.entityManager.merge(orderEntity));
		}
	}
}
