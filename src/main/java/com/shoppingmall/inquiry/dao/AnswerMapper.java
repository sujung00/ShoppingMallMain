package com.shoppingmall.inquiry.dao;

import org.springframework.stereotype.Repository;

import com.shoppingmall.inquiry.model.Answer;

@Repository
public interface AnswerMapper {

	public Answer selectAnswerByInquiryId(int inquiryId);
}
