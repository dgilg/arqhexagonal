package com.example.hexagonal.adapters.in;

import com.example.hexagonal.application.OrderService;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

	@Inject
	OrderService orderService;

	@POST
	public Response createOrder(final Order order) {
		this.orderService.createOrder(order);
		return Response.status(Response.Status.CREATED).entity(order).build();
	}

	@POST
	@Path("/{orderId}/items")
	public Response addItemToOrder(@PathParam("orderId") final Long orderId, final OrderItem item) {
		this.orderService.addItemToOrder(orderId, item);
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	@Path("/{orderId}/status")
	public Response updateOrderStatus(@PathParam("orderId") final Long orderId, final String status) {
		this.orderService.updateOrderStatus(orderId, status);
		return Response.status(Response.Status.OK).build();
	}

	@GET
	public List<Order> getAllOrders() {
		return this.orderService.getAllOrders();
	}

	@GET
	@Path("/{orderId}")
	public Order getOrderById(@PathParam("orderId") final Long orderId) {
		return this.orderService.findOrderById(orderId);
	}

}
