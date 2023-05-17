package com.shoppingmall.order.model;

import java.util.List;

import com.shoppingmall.address.model.Address;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketView;
import com.shoppingmall.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderView {

	// user
	private User user;

	// 장바구니 1개
	private Basket basket; // 유저id, 총 가격
	
	// 장바구니 상품들
	private List<BasketView> basketViewList; // 제품, 옵션
	
	// 배송지 목록
	private List<Address> addressList;
	
	// 포인트
	private int totalPoint;
	
}
