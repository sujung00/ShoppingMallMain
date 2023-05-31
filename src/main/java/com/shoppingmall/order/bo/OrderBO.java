package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.bo.BasketProductBO;
import com.shoppingmall.basket.model.BasketView;
import com.shoppingmall.order.dao.OrderMapper;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderProduct;
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
	
	@Transactional
	public int generateOrder(int userId, int addressId, int basketId,
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
			orderProductBO.addOrderProduct(orderId, basketView.getProduct().getId(), basketView.getProductOption().getId(), basketView.getBasketProduct().getCount(), "결제대기");
		
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
		
		
		return order.getId();
	}
	
	@Transactional
	public void addOrder(Order order) {
		orderMapper.insertOrder(order);
	}
	
	public List<Order> getNewestOrderListByUserId(int userId){
		return orderMapper.selectNewestOrderListByUserId(userId);
	}
	
	public Order getOrderByOrderId(int orderId) {
		return orderMapper.selectOrderByOrderId(orderId);
	}
	
	public List<Order> getOrderListByUserId(int userId){
		return orderMapper.selectOrderListByUserId(userId);
	}
	
	@Transactional
	public int updateAddressIdByOrderId(int orderId, int addressId) {
		return orderMapper.updateAddressIdByOrderId(orderId, addressId);
	}
	
	public void deleteOrderByOrderIdUserId(int orderId, int userId, int totalPay) {
		int totalPoint = pointBO.getTotalPointByUserId(userId);
		// 포인트 다시 적립
		pointBO.addPoint(userId, (int)(totalPay*0.01), "결제 취소", totalPoint+(int)(totalPay*0.01));
		
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductListByOrderId(orderId);
		for(OrderProduct orderProduct : orderProductList) {
			orderProductBO.updateStateByOrderProductId("주문취소", orderProduct.getId());
		}
	}
}
