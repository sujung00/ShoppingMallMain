package com.shoppingmall.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.inquiry.bo.InquiryAdminBO;
import com.shoppingmall.inquiry.model.InquiryView;

@RequestMapping("/inquiry_admin")
@Controller
public class InquiryAdminController {
	
	@Autowired
	private InquiryAdminBO inquiryAdminBO;

	/**
	 * ADMIN - 문의 사항 관리 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/inquiry_view")
	public String inquiryView(Model model) {
		List<InquiryView> inquiryViewList = inquiryAdminBO.generateInQuiryView();
		
		model.addAttribute("inquiryViewList", inquiryViewList);
		model.addAttribute("view", "/admin/inquiry/inquiryView");
		return "template/layout";
	}
}
