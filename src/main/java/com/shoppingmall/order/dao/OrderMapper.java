package com.shoppingmall.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.Order;

@Repository
public interface OrderMapper {

	public void insertOrder(Order order);
	
	public List<Order> selectNewestOrderListByUserId(int userId);
	
	public Order selectOrderByOrderId(int orderId);
	
	public List<Order> selectOrderListByUserId(int userId);
	
	public int updateAddressIdByOrderId(
			@Param("orderId") int orderId,
			@Param("addressId") int addressId);
	
	public int deleteOrderByOrderIdUserId(
			@Param("orderId") int orderId,
			@Param("userId") int userId);
}
