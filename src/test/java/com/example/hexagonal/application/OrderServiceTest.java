package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderServiceTest {

	@Inject
	OrderService orderService;

	@Test
	public void testCreateOrder() {
		final Order order = new Order(LocalDateTime.now(), "PENDING");
		this.orderService.createOrder(order);
		assertNotNull(order.getId());
	}

	@Test
	public void testAddItemToOrder() {
		final Order order = new Order(LocalDateTime.now(), "PENDING");
		this.orderService.createOrder(order);
		final OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
		this.orderService.addItemToOrder(order.getId(), item);
		assertEquals(1, this.orderService.findOrderById(order.getId()).getItems().size());
	}

	@Test
	public void testUpdateOrderStatus() {
		final Order order = new Order(LocalDateTime.now(), "PENDING");
		this.orderService.createOrder(order);
		this.orderService.updateOrderStatus(order.getId(), "CONFIRMED");
		assertEquals("CONFIRMED", this.orderService.findOrderById(order.getId()).getStatus());
	}

}
