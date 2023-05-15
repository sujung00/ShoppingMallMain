package com.shoppingmall.basket.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.dao.BasketProductMapper;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketProduct;
import com.shoppingmall.productOption.bo.ProductOptionBO;

@Service
public class BasketProductBO {
	
	@Autowired
	private BasketBO basketBO;

	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Autowired
	private BasketProductMapper basketProductMapper;
	
	public void addBasketProduct(int userId, int producId, String color, String size, int count) {
		// 유저의 장바구니 찾기
		Basket basket = basketBO.getBasketByUserId(userId);
		
		// 장바구니가 존재하지 않는다면 장바구니 생성
		if(basket == null) {
			basketBO.addBasket(userId);
			basket = basketBO.getBasketByUserId(userId);
		}
		
		// 상품이 장바구니에 존재하지 않는다면 BasketProduct 생성 후 장바구니에 추가
		int optionId = productOptionBO.getProductOptionByProductId(producId, color, size).getId();
		BasketProduct basketProduct = getBasketProductByBasketId(basket.getId(), producId, optionId);
		if(basketProduct == null) {
			createBasketProduct(userId, basket.getId(), producId, optionId, count);
		} else { // 장바구니에 존재하면 수량만 증가
			updateBasketProduct(basketProduct.getId(), count);
		}
		
	}
	
	public void createBasketProduct(int userId, int basketId, int productId, int optionId, int count) {
		basketProductMapper.insertBasketProduct(userId, basketId, productId, optionId, count);
	}
	
	public void updateBasketProduct(int basketProductId, int count) {
		basketProductMapper.updateBasketProduct(basketProductId, count);
	}
	
	public BasketProduct getBasketProductByBasketId(int basketId, int productId, int productOptionId) {
		return basketProductMapper.selectBasketProductByBasketId(basketId, productId, productOptionId);
	}
}
