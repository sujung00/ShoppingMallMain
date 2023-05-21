package com.shoppingmall.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.OrderProduct;

@Repository
public interface OrderProductMapper {

	public void insertOrderProduct(
			@Param("orderId") int orderId,
			@Param("productId") int productId,
			@Param("optionId") int optionId,
			@Param("count") int count,
			@Param("state") String state);
	
	public List<OrderProduct> selectOrderProductListByOrderId(int orderId);
	
	public OrderProduct selectOrderProductByOrderProductId(int orderProductId);
	
	public List<OrderProduct> selectOrderProductList();
}
