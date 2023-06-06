package com.shoppingmall.order.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.OrderDetailView;
import com.shoppingmall.order.model.OrderView;

@SpringBootTest
class OrderServiceBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderServiceBO orderServiceBO;
	
	@Transactional
	@Test
	void 주문서화면생성하기() {
		logger.info("#### 주문서화면생성하기");
		OrderView orderView = orderServiceBO.generateOrderView(5);
		assertNotNull(orderView);
	}
	
	@Transactional
	@Test
	void 주문화면리스트생성하기() {
		logger.info("#### 주문화면리스트생성하기");
		List<OrderDetailView> orderDetailViewList = orderServiceBO.generateOrderDetailListView(13);
		assertNotNull(orderDetailViewList);
	}
	
	@Transactional
	@Test
	void 주문상세화면생성하기() {
		logger.info("#### 주문상세화면생성하기");
		OrderDetailView orderDetailView = orderServiceBO.generateOrderDetailView(4);
		assertNotNull(orderDetailView);
	}
}
