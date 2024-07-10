package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.service.OrderService;
import com.example.hexagonal.domain.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Transactional
public class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		// RestAssured.config = RestAssured.config();
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	void testCreateOrder() {
		final Order order = new Order(LocalDateTime.now(), "Test Status");

		given().contentType(MediaType.APPLICATION_JSON).body(order).when().post("/orders").then().statusCode(201)
				.body("id", is(order.getId()));
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	void testGetOrder() {
		given().pathParam("id", 1L).when().get("/orders/{id}").then().statusCode(404);
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	void testGetAllOrders() {
		final List<Order> orders = given().when().get("/orders").then().statusCode(200).extract()
				.as(new TypeRef<List<Order>>() {
				});
		assertEquals(1, orders.size());
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	void testDeleteOrder() {
		final Order order = new Order(LocalDateTime.now(), "Test Status");

		given().contentType(MediaType.APPLICATION_JSON).body(order).when().post("/orders").then().statusCode(201)
				.body("id", is(order.getId()));
		given().pathParam("id", 1L).when().delete("/orders/{id}").then().statusCode(204);
	}

	private Order buildOrder(Long id, LocalDateTime date, String status) {
		return new Order(id, date, status);
	}
}
