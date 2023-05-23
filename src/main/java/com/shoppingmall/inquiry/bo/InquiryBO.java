package com.shoppingmall.inquiry.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.inquiry.dao.InquiryMapper;

@Service
public class InquiryBO {

	@Autowired
	private InquiryMapper inquiryMapper;
	
	public int addInquiry(int userId, int orderId, String subject, String content) {
		return inquiryMapper.insertInquiry(userId, orderId, subject, content);
	}
}
