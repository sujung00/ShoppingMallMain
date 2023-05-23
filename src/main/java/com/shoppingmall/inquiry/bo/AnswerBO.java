package com.shoppingmall.inquiry.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.inquiry.dao.AnswerMapper;
import com.shoppingmall.inquiry.model.Answer;

@Service
public class AnswerBO {
	
	@Autowired
	private AnswerMapper answerMapper;

	public Answer getAnswerByInquiryId(int inquiryId) {
		return answerMapper.selectAnswerByInquiryId(inquiryId);
	}
}
