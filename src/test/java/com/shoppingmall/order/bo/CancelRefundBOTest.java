package com.shoppingmall.order.bo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.CancelRefund;

@SpringBootTest
class CancelRefundBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CancelRefundBO cancelRefundBO;
	
	@Test
	void 주문상품의취소환불정보가져오기() {
		logger.info("#### 주문상품의취소환불정보가져오기");
		CancelRefund cancelRefund = cancelRefundBO.getCancelRefundByOrderProductId(1);
		assertNotNull(cancelRefund);
	}
	
	@Transactional
	@Test
	void 취소환불하기() {
		logger.info("#### 취소환불하기");
		cancelRefundBO.addCancelRefund(63, "단순변심", "환불", 2);
	}
	
	@Transactional
	@Test
	void 취소환불삭제하기() {
		logger.info("#### 취소환불삭제하기");
		int rowCount = cancelRefundBO.deleteCancelRefund(3);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 취소환불이유변경() {
		logger.info("#### 취소환불이유변경");
		int rowCount = cancelRefundBO.updateReasonByCancelRefundId(3, "단순변심");
		assertNotNull(rowCount);
	}
}
