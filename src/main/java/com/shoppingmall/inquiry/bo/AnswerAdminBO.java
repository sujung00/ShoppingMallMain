package com.shoppingmall.inquiry.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.inquiry.dao.AnswerMapper;

@Service
public class AnswerAdminBO {
	
	@Autowired
	private AnswerMapper answerMapper;

	public int updateAnswer(int answerId, String content) {
		return answerMapper.updateAnswer(answerId, content);
	}
	
	public int addAnswer(int inquiryId, String content) {
		return answerMapper.inserAnswer(inquiryId, content);
	}
}
