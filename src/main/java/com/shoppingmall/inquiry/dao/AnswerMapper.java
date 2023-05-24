package com.shoppingmall.inquiry.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.inquiry.model.Answer;

@Repository
public interface AnswerMapper {

	public Answer selectAnswerByInquiryId(int inquiryId);
	
	public int updateAnswer(
			@Param("answerId") int answerId,
			@Param("content") String content);
	
	public int inserAnswer(
			@Param("inquiryId") int inquiryId,
			@Param("content") String content);
}
