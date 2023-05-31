package com.shoppingmall.inquiry;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.inquiry.bo.InquiryBO;
import com.shoppingmall.inquiry.model.InquiryView;

@RequestMapping("/inquiry")
@Controller
public class InquiryController {
	
	@Autowired
	private InquiryBO inquiryBO;

	/**
	 * 문의 작성 화면
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/inquiry_view")
	public String inquiryView(Model model, HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		
		List<InquiryView> inquiryViewList = inquiryBO.generateInquiryView(userId);
		
		model.addAttribute("inquiryViewList", inquiryViewList);
		model.addAttribute("view", "/inquiry/inquiryView");
		return "template/layout";
	}
}
