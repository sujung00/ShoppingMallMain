package com.shoppingmall.inquiry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.inquiry.bo.AnswerAdminBO;

@RequestMapping("/inquiry_admin")
@RestController
public class InquiryAdminRestController {

	@Autowired
	private AnswerAdminBO answerAdminBO;
	
	/**
	 * ADMIN - 문의 답변 수정 API
	 * @param answerId
	 * @param content
	 * @return
	 */
	@PostMapping("/answer_update")
	public Map<String, Object> answerUpdate(
			@RequestParam("answerId") int answerId,
			@RequestParam("content") String content){
		// inquiry update
		int rowCount = answerAdminBO.updateAnswer(answerId, content);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "문의 답변이 수정되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "문의 답변 수정 실패");
		}
		
		return result;
	}
	
	/**
	 * ADMIN - 문의 답변 API
	 * @param inquiryId
	 * @param content
	 * @return
	 */
	@PostMapping("/answer_create")
	public Map<String, Object> answerCreate(
			@RequestParam("inquiryId") int inquiryId,
			@RequestParam("content") String content) {
		// inquiry insert
		int rowCount = answerAdminBO.addAnswer(inquiryId, content);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "문의 답변이 작성되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "문의 답변 실패");
		}
		
		return result;		
	}
}
