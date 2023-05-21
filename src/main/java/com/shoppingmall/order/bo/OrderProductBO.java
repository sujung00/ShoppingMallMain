package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.OrderProductMapper;
import com.shoppingmall.order.model.OrderProduct;

@Service
public class OrderProductBO {

	@Autowired
	private OrderProductMapper orderProductMapper;
	
	public void addOrderProduct(int orderId, int productId, int optionId, int count, String state) {
		orderProductMapper.insertOrderProduct(orderId, productId, optionId, count, state);
	}
	
	public List<OrderProduct> getOrderProductListByOrderId(int orderId){
		return orderProductMapper.selectOrderProductListByOrderId(orderId);
	}
	
	public OrderProduct getOrderProductByOrderProductId(int orderProductId) {
		return orderProductMapper.selectOrderProductByOrderProductId(orderProductId);
	}
	
	public List<OrderProduct> getOrderProductList(){
		return orderProductMapper.selectOrderProductList();
	}
}
