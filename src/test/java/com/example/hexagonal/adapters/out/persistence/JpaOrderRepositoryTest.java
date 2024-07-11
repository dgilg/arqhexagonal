package com.example.hexagonal.adapters.out.persistence;

import com.example.hexagonal.adapters.out.persistence.mapper.OrderEntityMapperImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Transactional
public class JpaOrderRepositoryTest {

	@Inject
	private JpaOrderRepository orderRepository;

	@Inject
	private EntityManager entityManager;

	@Inject
	private OrderEntityMapperImpl orderEntityMapper;

	@Test
	void testSaveOrder() {
		OrderEntity order = new OrderEntity(LocalDateTime.now(), "Order Status");
		order = this.orderRepository.save(order);
		this.entityManager.flush();
		this.entityManager.clear();

		final Optional<OrderEntity> savedOrder = this.orderRepository.findById(order.getId());
		assertTrue(savedOrder.isPresent());
		assertEquals(order.getStatus(), savedOrder.get().getStatus());
	}

	@Test
	void testDeleteOrder() {
		OrderEntity order = new OrderEntity(LocalDateTime.now(), "Order Status");
		order = this.orderRepository.save(order);
		this.entityManager.flush();
		this.entityManager.clear();

		this.orderRepository.deleteById(order.getId());
		this.entityManager.flush();
		this.entityManager.clear();

		final Optional<OrderEntity> deletedOrder = this.orderRepository.findById(order.getId());
		assertTrue(deletedOrder.isEmpty());
	}
}
