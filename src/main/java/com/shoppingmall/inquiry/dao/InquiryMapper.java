package com.shoppingmall.inquiry.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryMapper {

	public int insertInquiry(
			@Param("userId") int userId,
			@Param("orderId") int orderId,
			@Param("subject") String subject,
			@Param("content") String content);
}
