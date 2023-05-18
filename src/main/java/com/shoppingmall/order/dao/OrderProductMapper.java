package com.shoppingmall.order.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductMapper {

	public void insertOrderProduct(
			@Param("orderId") int orderId,
			@Param("productId") int productId,
			@Param("optionId") int optionId,
			@Param("count") int count,
			@Param("state") String state);
}
