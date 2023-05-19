package com.shoppingmall.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.Order;

@Repository
public interface OrderMapper {

	public void insertOrder(Order order);
	
	public List<Order> selectNewestOrderListByUserId(int userId);
}
