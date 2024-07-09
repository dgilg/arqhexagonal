package com.example.hexagonal.adapters.in;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {

	@Test
	public void testCreateOrderEndpoint() {
		final Order order = new Order(LocalDateTime.now(), "PENDING");
		given().contentType("application/json").body(order).when().post("/orders").then().statusCode(201).body("status",
				is("PENDING"));
	}

	@Test
	public void testAddItemToOrderEndpoint() {
		Order order = new Order(LocalDateTime.now(), "PENDING");
		order = given().contentType("application/json").body(order).when().post("/orders").then().statusCode(201)
				.extract().as(Order.class);

		final OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
		given().contentType("application/json").body(item).when().post("/orders/{orderId}/items", order.getId()).then()
				.statusCode(200);
	}

	@Test
	public void testUpdateOrderStatusEndpoint() {
		Order order = new Order(LocalDateTime.now(), "PENDING");
		order = given().contentType("application/json").body(order).when().post("/orders").then().statusCode(201)
				.extract().as(Order.class);

		given().contentType("application/json").body("CONFIRMED").when().put("/orders/{orderId}/status", order.getId())
				.then().statusCode(200);
	}
}
