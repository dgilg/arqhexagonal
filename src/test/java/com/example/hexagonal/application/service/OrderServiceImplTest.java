package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.out.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.orderService = new OrderServiceImpl(this.orderRepository);
	}

	@Test
	void testCreateOrder() {
		final Order order = new Order(null, LocalDateTime.now(), "Order Status");
		when(this.orderRepository.save(any(Order.class))).thenReturn(order);

		final Order createdOrder = this.orderService.createOrder(order);

		assertEquals(order.getStatus(), createdOrder.getStatus());
		verify(this.orderRepository, times(1)).save(order);
	}

	@Test
	void testGetOrder() {
		final Order order = new Order(1L, LocalDateTime.now(), "Order Status");
		when(this.orderRepository.findById(1L)).thenReturn(Optional.of(order));

		final Optional<Order> fetchedOrder = this.orderService.getOrder(1L);

		assertEquals(order.getStatus(), fetchedOrder.get().getStatus());
		verify(this.orderRepository, times(1)).findById(1L);
	}

	@Test
	void testGetAllOrders() {
		// similar to above, test for getAllOrders()
		final Order order = new Order(1L, LocalDateTime.now(), "Order Status");
		when(this.orderRepository.findAll()).thenReturn(List.of(order));

		final List<Order> fetchedOrder = this.orderService.getAllOrders();

		assertEquals(1, fetchedOrder.size());
		assertEquals("Order Status", fetchedOrder.get(0).getStatus());
		verify(this.orderRepository, times(1)).findAll();
	}

	@Test
	void testDeleteOrder() {
		doNothing().when(this.orderRepository).deleteById(1L);

		this.orderService.deleteOrder(1L);

		verify(this.orderRepository, times(1)).deleteById(1L);
	}

}
