package com.shoppingmall.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {

	/**
	 * Main 페이지
	 * @param model
	 * @return
	 */
	// http://localhost/main/main_view
	@RequestMapping("/main_view")
	public String mainView(Model model) {
		model.addAttribute("view", "/main/mainView");
		return "template/layout";
	}
	
}
