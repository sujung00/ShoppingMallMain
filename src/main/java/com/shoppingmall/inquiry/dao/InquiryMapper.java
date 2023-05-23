package com.shoppingmall.inquiry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.inquiry.model.Inquiry;

@Repository
public interface InquiryMapper {

	public int insertInquiry(
			@Param("userId") int userId,
			@Param("orderId") int orderId,
			@Param("subject") String subject,
			@Param("content") String content);
	
	public List<Inquiry> selectInquiryListByUserId(int userId);
}
