package com.shoppingmall.inquiry.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.inquiry.dao.InquiryMapper;
import com.shoppingmall.inquiry.model.Answer;
import com.shoppingmall.inquiry.model.Inquiry;
import com.shoppingmall.inquiry.model.InquiryView;

@Service
public class InquiryBO {

	@Autowired
	private InquiryMapper inquiryMapper;
	
	@Autowired
	private AnswerBO answerBO;
	
	public int addInquiry(int userId, int orderId, String subject, String content) {
		return inquiryMapper.insertInquiry(userId, orderId, subject, content);
	}
	
	public List<InquiryView> generateInquiryView(int userId){
		List<InquiryView> inquiryViewList = new ArrayList<>();
		
		List<Inquiry> inquiryList = getInquiryListByUserId(userId);
		for(Inquiry inquiry : inquiryList) {
			InquiryView inquiryView = new InquiryView();
			
			inquiryView.setInquiry(inquiry);
			
			Answer answer = answerBO.getAnswerByInquiryId(inquiry.getId());
			inquiryView.setAnswer(answer);
			
			inquiryViewList.add(inquiryView);
		}
		
		return inquiryViewList;
	}
	
	public List<Inquiry> getInquiryListByUserId(int userId){
		return inquiryMapper.selectInquiryListByUserId(userId);
	}
}
