package com.shoppingmall.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.user.bo.KakaoService;

@RequestMapping("/user")
@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KakaoService kakaoService;
	
	/**
	 * 로그인 화면
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
	 * 회원가입 화면
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
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		session.removeAttribute("userEmail");
		session.removeAttribute("userPhoneNumber");
		
		return "redirect:/main/main_view";
	}
	
	/**
	 * 아이디 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find_id_view")
	public String findIdView(Model model) {
		model.addAttribute("view", "/user/findIdView");
		return "template/layout";
	}

	/**
	 * 비밀번호 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find_pw_view")
	public String findPWView(Model model) {
		model.addAttribute("view", "/user/findPWView");
		return "template/layout";
	}
	
	/**
	 * 회원 정보 화면(MY TRNED)
	 * @param model
	 * @return
	 */
	@GetMapping("/profile_view")
	public String profileUpdateView(Model model) {
		model.addAttribute("view", "/user/profileView");
		return "template/layout";
	}
	
	/**
	 * 이름 변경 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_name_view")
	public String updateNameView(Model model) {
		model.addAttribute("view", "/user/updateNameView");
		return "template/layout";
		
	}
	
	/**
	 * 전화번호 변경 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_phonenumber_view")
	public String updatePhoneNumberView(Model model) {
		model.addAttribute("view", "/user/updatePhoneNumberView");
		return "template/layout";
	}
	
	/**
	 * 비밀번호 변경 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_password_view")
	public String updatePasswordView(Model model) {
		model.addAttribute("view", "/user/updatePasswordView");
		return "template/layout";
	}
	
	/**
	 * 카카오 로그인 API
	 * @param code
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/kakao")
	public String kakoLogin(
			@RequestParam("code") String code,
			HttpSession session,
			Model model) {
		
		logger.info("*****[kakao login]***** code : " + code);
		String access_token = kakaoService.getAccessToken(code);
		
		Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);
		logger.info("*****[kakao login]***** userInfo : " + userInfo);
	    
	    // 클라이언트의 정보가 존재할 때 로그인 또는 회원가입 진행
	    if (userInfo != null) {
	    	String nickname = (String)userInfo.get("nickname");
	    	String email = (String)userInfo.get("email");
	    	kakaoService.kakaoLogin(nickname, email, session);
	    }
	    
		return "redirect:/main/main_view";
	}
}
