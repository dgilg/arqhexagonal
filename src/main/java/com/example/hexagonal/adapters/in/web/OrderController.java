package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.service.OrderService;
import com.example.hexagonal.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

	@Inject
	private OrderService orderService;

	@POST
	public Response createOrder(final Order order) {
		this.orderService.createOrder(order);
		return Response.status(Response.Status.CREATED).entity(order).build();
	}

	@GET
	public Response getAllOrders() {
		return Response.ok(this.orderService.getAllOrders()).build();
	}

	@GET
	@Path("/{id}")
	public Response getOrder(@PathParam("id") final Long orderId) {
		final Optional<Order> order = this.orderService.getOrder(orderId);
		return order.map(value -> Response.ok(value).build())
				.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}

	@DELETE
	@Path("/{id}")
	public Response deleteOrder(@PathParam("id") Long id) {
		this.orderService.deleteOrder(id);
		return Response.noContent().build();
	}

}
