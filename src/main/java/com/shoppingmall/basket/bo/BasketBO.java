package com.shoppingmall.basket.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.dao.BasketMapper;
import com.shoppingmall.basket.model.Basket;

@Service
public class BasketBO {
	
	@Autowired
	private BasketMapper basketMapper;
	
	public void addBasket(int userId) {
		basketMapper.insertBasket(userId);
	}
	
	public Basket getBasketByUserId(int userId) {
		return basketMapper.selectBasketByUserId(userId);
	}
	
	public void updateTotalPrice(int basketId, int totalPrice) {
		basketMapper.updateTotalPrice(basketId, totalPrice);
	}
	
	public Basket getBasketByBasketId(int basketId) {
		return basketMapper.selectBasketByBasketId(basketId);
	}
	
	public void deleteBasketByBasketId(int basketId) {
		basketMapper.deleteBasketByBasketId(basketId);
	}
}
