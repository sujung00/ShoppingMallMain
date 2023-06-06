package com.shoppingmall.order.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.Order;

@SpringBootTest
class OrderBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderBO orderBO;
	
	@Transactional
	@Test
	void 주문서만들기() {
		logger.info("#### 주문서만들기");
		int orderId = orderBO.generateOrder(13, 7, 5, null, "신용카드", 20000, 100);
		assertNotNull(orderId);
	}
	
	@Transactional
	@Test
	void 주문() {
		logger.info("#### 주문");
		Order order = new Order();
		order.setUserId(13);
		order.setAddressId(7);
		order.setOrderRequest("조심히 와주세요");
		order.setPayType("신용카드");
		order.setTotalPay(200000);
		orderBO.addOrder(order);
	}
	
	@Test
	void 최신주문가져오기() {
		logger.info("#### 최신주문가져오기");
		List<Order> orderList = orderBO.getNewestOrderListByUserId(13);
		assertNotNull(orderList);
	}
	
	@Test
	void 주문가져오기() {
		logger.info("#### 주문가져오기");
		Order order = orderBO.getOrderByOrderId(3);
		assertNotNull(order);
	}
	
	@Test
	void 주문내역가져오기() {
		logger.info("#### 주문내역가져오기");
		List<Order> orderList = orderBO.getOrderListByUserId(13);
		assertNotNull(orderList);
	}
	
	@Transactional
	@Test
	void 주문주소변경하기() {
		logger.info("#### 주문주소변경하기");
		int rowCount = orderBO.updateAddressIdByOrderId(3, 2);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 주문취소하기() {
		logger.info("#### 주문취소하기");
		orderBO.deleteOrderByOrderIdUserId(3, 2, 819000);
	}
}
