package com.shoppingmall.basket.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.basket.model.Basket;

@Repository
public interface BasketMapper {

	public void insertBasket(int userId);
	
	public Basket selectBasketByUserId(int userId);
	
	public void updateTotalPrice(
			@Param("basketId") int basketId,
			@Param("totalPrice") int totalPrice);
}
