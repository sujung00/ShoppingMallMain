package com.shoppingmall.inquiry.bo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AnswerAdminBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AnswerAdminBO answerAdminBO;
	
	@Transactional
	@Test
	void 답변수정하기() {
		logger.info("#### 답변수정하기");
		int rowCount = answerAdminBO.updateAnswer(1, "6월 10일 출고 될 예정입니다.");
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 답변하기() {
		logger.info("#### 답변하기");
		int rowCount = answerAdminBO.addAnswer(1, "6월 10일 출고 될 예정입니다.");
		assertNotNull(rowCount);
	}
	
}
