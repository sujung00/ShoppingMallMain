package com.shoppingmall.inquiry;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.inquiry.bo.InquiryBO;

@RequestMapping("/inquiry")
@RestController
public class InquiryRestController {

	@Autowired
	private InquiryBO inquiryBO;
	
	/**
	 * 문의 작성 API
	 * @param orderId
	 * @param subject
	 * @param content
	 * @param seesion
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("orderId") int orderId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			HttpSession seesion){
		int userId = (int)seesion.getAttribute("userId");
		
		// db insert
		int rowCount = inquiryBO.addInquiry(userId, orderId, subject, content);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "문의가 등록되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "문의가 등록에 실패하였습니다.");
		}
		
		return result;
	}
}
