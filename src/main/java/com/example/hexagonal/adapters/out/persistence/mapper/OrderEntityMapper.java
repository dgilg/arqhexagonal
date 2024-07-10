package com.example.hexagonal.adapters.out.persistence.mapper;

import com.example.hexagonal.adapters.out.persistence.OrderEntity;
import com.example.hexagonal.domain.model.Order;

import java.util.List;

public interface OrderEntityMapper {

	OrderEntity toEntity(final Order order);

	Order toDomain(final OrderEntity orderEntity);

	List<Order> toDomainList(final List<OrderEntity> orders);

}
