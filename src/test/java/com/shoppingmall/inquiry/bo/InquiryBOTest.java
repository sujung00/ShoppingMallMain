package com.shoppingmall.inquiry.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.inquiry.model.Inquiry;
import com.shoppingmall.inquiry.model.InquiryView;

@SpringBootTest
class InquiryBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InquiryBO inquiryBO;
	
	@Transactional
	@Test
	void 문의하기() {
		logger.info("#### 문의하기");
		int rowCount = inquiryBO.addInquiry(22, 72, "문의제목", "문의내용");
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 문의하기화면만들기() {
		logger.info("#### 문의하기화면만들기");
		List<InquiryView> inquiryViewList = inquiryBO.generateInquiryView(22);
		assertNotNull(inquiryViewList);
	}
	
	@Test
	void 문의하기내역가져오기() {
		logger.info("#### 문의하기내역가져오기");
		List<Inquiry> inquiryList = inquiryBO.getInquiryListByUserId(22);
		assertNotNull(inquiryList);
	}
	
}
