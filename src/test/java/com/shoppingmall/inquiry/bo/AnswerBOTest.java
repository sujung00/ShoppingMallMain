package com.shoppingmall.inquiry.bo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shoppingmall.inquiry.model.Answer;

@SpringBootTest
class AnswerBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AnswerBO answerBO;
	
	@Test
	void 답변받아오기() {
		logger.info("#### 답변받아오기");
		Answer answer = answerBO.getAnswerByInquiryId(1);
		assertNotNull(answer);
	}
}
