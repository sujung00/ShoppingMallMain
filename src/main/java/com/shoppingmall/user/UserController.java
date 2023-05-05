package com.shoppingmall.user;

import javax.servlet.http.HttpSession;

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

	/**
	 * 회원가입 페이지
	 * @param model
	 * @return
	 */
	// http://localhost/user/sign_up_view
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "/user/signUpView");
		return "template/layout";
	}
	
	/**
	 * 로그아웃 API
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		return "redirect:/main/main_view";
	}
	
	@GetMapping("/find_id_view")
	public String findIdView(Model model) {
		model.addAttribute("view", "/user/findIdView");
		return "template/layout";
	}

	@GetMapping("/find_pw_view")
	public String findPWView(Model model) {
		model.addAttribute("view", "/user/findPWView");
		return "template/layout";
	}
}
