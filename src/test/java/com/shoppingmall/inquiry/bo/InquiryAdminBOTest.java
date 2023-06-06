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
class InquiryAdminBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	InquiryAdminBO inquiryAdminBO;
	
	@Transactional
	@Test
	void 문의내역화면만들기() {
		logger.info("#### 문의내역화면만들기");
		List<InquiryView> inquiryViewList = inquiryAdminBO.generateInquiryView();
		assertNotNull(inquiryViewList);
	}
	
	@Test
	void 문의내역가져오기() {
		logger.info("#### 문의내역가져오기");
		List<Inquiry> inquirieList = inquiryAdminBO.getInquiryList();
		assertNotNull(inquirieList);
	}
}
