package com.shoppingmall.order.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.OrderProduct;

@SpringBootTest
class OrderProductBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderProductBO orderProductBO;
	
	@Transactional
	@Test
	void 주문상품추가하기() {
		logger.info("#### 주문상품추가하기");
		orderProductBO.addOrderProduct(3, 34, 71, 1, "결제대기");
	}
	
	@Test
	void 주문상품리스트가져오기() {
		logger.info("#### 주문상품리스트가져오기");
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductListByOrderId(3);
		assertNotNull(orderProductList);
	}
	
	@Test
	void 주문상품가져오기() {
		logger.info("#### 주문상품가져오기");
		OrderProduct orderProduct = orderProductBO.getOrderProductByOrderProductId(1);
		assertNotNull(orderProduct);
	}
	
	@Test 
	void 주문상품리스트전부가져오기() {
		logger.info("#### 주문상품리스트전부가져오기");
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductList();
		assertNotNull(orderProductList);
	}
	
	@Transactional
	@Test
	void 주문상태수정하기() {
		logger.info("#### 주문상태수정하기");
		int rowCount = orderProductBO.updateStateByOrderProductId("결제완료", 1);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 주문상품옵션수량변경하기() {
		logger.info("#### 주문상품옵션수량변경하기");
		orderProductBO.updateOptionCountByOrderProductId(1, "black", "S", "dark blue", "M", 1);
	}
	
	@Transactional
	@Test
	void 주문상품옵션수정하기() {
		logger.info("#### 주문상품옵션수정하기");
		orderProductBO.updateOptionIdByOrderProductId(1, 14);
	}
	
	@Transactional
	@Test
	void 결제완료로상태변경하기() {
		logger.info("#### 결제완료로상태변경하기");
		orderProductBO.updateStateByOrderId(3);
	}
}
