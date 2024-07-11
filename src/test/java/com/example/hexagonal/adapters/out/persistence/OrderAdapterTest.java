package com.example.hexagonal.adapters.out.persistence;

import com.example.hexagonal.adapters.out.persistence.mapper.OrderEntityMapper;
import com.example.hexagonal.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderAdapterTest {

	@Mock
	private JpaOrderRepository orderRepository;

	@Mock
	private OrderEntityMapper orderEntityMapper;

	@InjectMocks
	private OrderAdapter orderAdapter;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveOrder() {
		final OrderEntity orderEntity = new OrderEntity(LocalDateTime.now(), "Order Status");
		orderEntity.setId(1L);
		final Order order = new Order(LocalDateTime.now(), "Order Status");
		order.setId(1L);

		when(this.orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
		when(this.orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(order);
		when(this.orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);

		final Order savedOrder = this.orderAdapter.save(order);

		assertNotNull(savedOrder);
		assertNotNull(savedOrder.getId());
	}

	@Test
	void testFinById() {
		final Order order = new Order(LocalDateTime.now(), "Order Status");
		order.setId(1L);

		when(this.orderRepository.findById(1L))
				.thenReturn(Optional.of(new OrderEntity(LocalDateTime.now(), "Order Status")));
		when(this.orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(order);

		final Optional<Order> optOrder = this.orderAdapter.findById(1L);

		assertTrue(optOrder.isPresent());
		assertEquals("Order Status", optOrder.get().getStatus());
		assertEquals(1L, optOrder.get().getId());
	}

	@Test
	void testDeleteOrder() {
		this.orderAdapter.deleteById(1L);
		verify(this.orderRepository, times(1)).deleteById(1L);
	}
}
