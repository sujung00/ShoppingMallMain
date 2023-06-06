package com.shoppingmall.order.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.OrderAdminView;

@SpringBootTest
class OrderAdminBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderAdminBO orderAdminBO;
	
	@Transactional
	@Test
	void 주문관리화면만들기() {
		logger.info("#### 주문관리화면만들기");
		List<OrderAdminView> orderAdminViewList = orderAdminBO.generateOrderAdminView();
		assertNotNull(orderAdminViewList);
	}
	
}
