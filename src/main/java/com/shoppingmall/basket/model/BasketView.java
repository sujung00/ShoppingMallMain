package com.shoppingmall.basket.model;

import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketView {

	// 장바구니 상품 정보
	private BasketProduct basketProduct;
	
	// 상품 정보
	private Product product;
	
	// 상품 옵션 정보
	private ProductOption productOption;
	
	// 유저 정보
	private User user;
}
