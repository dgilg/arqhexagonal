package com.example.hexagonal.adapters.out.persistence.mapper;

import com.example.hexagonal.adapters.out.persistence.OrderEntity;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderEntityMapperImpl implements OrderEntityMapper {

	public OrderEntity toEntity(final Order order) {
		if (null == order) {
			return null;
		}
		final OrderEntity orderEntity = new OrderEntity(order.getCreationDate(), order.getStatus());
		orderEntity.setId(order.getId());
		return orderEntity;
	}

	public Order toDomain(final OrderEntity orderEntity) {
		return (null == orderEntity)
				? null
				: new Order(orderEntity.getId(), orderEntity.getCreationDate(), orderEntity.getStatus());
	}

	public List<Order> toDomainList(final List<OrderEntity> orders) {
		return (null == orders) ? List.of() : orders.stream().map(this::toDomain).toList();
	}
}