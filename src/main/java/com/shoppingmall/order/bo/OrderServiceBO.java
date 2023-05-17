package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.address.bo.AddressBO;
import com.shoppingmall.address.model.Address;
import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.bo.BasketProductBO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketProduct;
import com.shoppingmall.basket.model.BasketView;
import com.shoppingmall.order.model.OrderView;
import com.shoppingmall.point.bo.PointBO;
import com.shoppingmall.point.model.Point;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class OrderServiceBO {

	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private BasketProductBO basketProductBO;
	
	@Autowired
	private AddressBO addressBO;
	
	@Autowired
	private PointBO pointBO;
	
	public OrderView generateOrderView(int basketId) {
		OrderView orderView = new OrderView();
		
		// 장바구니 1개
		Basket basket = basketBO.getBasketByBasketId(basketId);
		orderView.setBasket(basket);
		
		// user
		int userId = basket.getUserId();
		User user = userBO.getUserByUserId(userId);
		orderView.setUser(user);
		
		// 장바구니 상품들
		List<BasketView> basketViewList = basketProductBO.generateBasket(userId);
		orderView.setBasketViewList(basketViewList);
		
		// 배송지 목록
		List<Address> addrssList = addressBO.getAddressListByUserId(userId);
		orderView.setAddressList(addrssList);
		
		// 포인트
		int point = pointBO.getTotalPointByUserId(userId);
		orderView.setTotalPoint(point);
		
		return orderView;
	}
}
