package com.shoppingmall.order.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.OrderProductMapper;

@Service
public class OrderProductBO {

	@Autowired
	private OrderProductMapper orderProductMapper;
	
	public void addOrderProduct(int orderId, int productId, int optionId, int count, String state) {
		orderProductMapper.insertOrderProduct(orderId, productId, optionId, count, state);
	}
}
