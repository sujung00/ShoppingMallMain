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
public class InquiryAdminBO {

	@Autowired
	private InquiryMapper inquiryMapper;
	
	@Autowired
	private AnswerBO answerBO;
	
	public List<InquiryView> generateInQuiryView(){
		List<InquiryView> inquiryViewList = new ArrayList<>();
		
		List<Inquiry> inquirieList = getInquiryList();
		for(Inquiry inquiry : inquirieList) {
			InquiryView inquiryView = new InquiryView();
			
			inquiryView.setInquiry(inquiry);
			
			Answer answer = answerBO.getAnswerByInquiryId(inquiry.getId());
			inquiryView.setAnswer(answer);
			
			inquiryViewList.add(inquiryView);
		}
		
		return inquiryViewList;
	}
	
	public List<Inquiry> getInquiryList(){
		return inquiryMapper.selectInquiryList();
	}
}
