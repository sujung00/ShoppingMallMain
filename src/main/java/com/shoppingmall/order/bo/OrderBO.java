package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.bo.BasketProductBO;
import com.shoppingmall.basket.model.BasketView;
import com.shoppingmall.order.dao.OrderMapper;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.point.bo.PointBO;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;

@Service
public class OrderBO {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BasketProductBO basketProductBO;
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private PointBO pointBO;

	@Autowired
	private ProductOptionBO productOptionBO;
	
	public void generateOrder(int userId, int addressId, int basketId,
			String orderRequest, String payType, int totalPay, Integer usePoint) {
		// order db insert
		Order order = new Order();
		order.setUserId(userId);
		order.setAddressId(addressId);
		order.setOrderRequest(orderRequest);
		order.setPayType(payType);
		order.setTotalPay(totalPay);
		addOrder(order);
		
		// order product insert
		// 주문 상품들(장바구니 상품들)
		int orderId = order.getId();
		List<BasketView> basketViewList = basketProductBO.generateBasket(userId);
		for(BasketView basketView : basketViewList) {
			orderProductBO.addOrderProduct(orderId, basketView.getProduct().getId(), basketView.getProductOption().getId(), basketView.getBasketProduct().getCount(), "배송준비중");
		
			// product(재고) update
			ProductOption productOption = productOptionBO.getProductOptionByProductId(basketView.getProduct().getId(), basketView.getProductOption().getColor(), basketView.getProductOption().getSize());
			productOptionBO.updateStockByProductOptionId(productOption.getId(), productOption.getStock() - basketView.getBasketProduct().getCount());
		}
		
		// basketProduct delete
		basketProductBO.deleteBasketProductByBasketId(basketId);
		
		// basket delete => totalPrice 0으로 update
		basketBO.updateTotalPrice(basketId, 0);
		
		// 포인트 삭제 및 적립
		int totalPoint = pointBO.getTotalPointByUserId(userId);
		if(usePoint != null) {
			pointBO.addPoint(userId, usePoint, "결제 시 포인트 사용", totalPoint-usePoint);
		}
		pointBO.addPoint(userId, (int)(totalPay*0.01), "결제 적립", totalPoint-usePoint+(int)(totalPay*0.01));
		
	}
	
	public void addOrder(Order order) {
		orderMapper.insertOrder(order);
	}
	
	public List<Order> getNewestOrderListByUserId(int userId){
		return orderMapper.selectNewestOrderListByUserId(userId);
	}
}
