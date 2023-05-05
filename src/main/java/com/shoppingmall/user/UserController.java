package com.shoppingmall.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	/**
	 * 로그인 페이지
	 * @param model
	 * @return
	 */
	// http://localhost/user/sign_in_view
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("view", "/user/signInView");
		return "template/layout";
	}
	
	// http://localhost/user/sign_up_view
		@GetMapping("/sign_up_view")
		public String signUpView(Model model) {
			model.addAttribute("view", "/user/signUpView");
			return "template/layout";
		}
}
