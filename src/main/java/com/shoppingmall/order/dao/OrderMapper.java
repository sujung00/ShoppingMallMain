package com.shoppingmall.order.dao;

import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.Order;

@Repository
public interface OrderMapper {

	public void insertOrder(Order order);
}
