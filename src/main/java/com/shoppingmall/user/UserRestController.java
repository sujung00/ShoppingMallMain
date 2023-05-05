package com.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.common.EncryptUtils;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name")String name,
			@RequestParam("email") String email,
			@RequestParam("phoneNumber") String phoneNumber){
		Map<String, Object> result = new HashMap<>();
		
		// id 중복 확인
		User idUser = userBO.getUserByLoginId(loginId);
		if(idUser != null) {
			result.put("code", 300);
			result.put("errorMessage", "이미 존재하는 아이디 입니다.");
			
			return result;
		}
		// email 중복 확인
		User emailUser = userBO.getUserByEmail(email);
		if(emailUser != null) {
			result.put("code", 301);
			result.put("errorMessage", "이미 존재하는 이메일 입니다.");
			
			return result;
		}
		
		// password 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// db insert
		int rowCount = userBO.addUser(loginId, hashedPassword, name, email, phoneNumber);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "회원가입이 완료되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패했습니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session){
		// password 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// db select
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
				
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			result.put("code", 1);
			result.put("result", "성공");
			
			// 세션에 유저 추가
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userLoginId", user.getLoginId());
		} else {
			result.put("code", 300);
			result.put("errorMessage", "입력하신 아이디와 비밀번호는 등록되어 있지 않습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/find_id")
	public Map<String, Object> findId(
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		// db select
		String loginId = userBO.getLoginIdByNameEmail(name, email);
		
		// id 변환
		int idLength = loginId.length()-2;
		loginId = loginId.substring(0, 2);
		for(int i = 0; i < idLength; i++) {
			loginId += "*";
		}
		
		Map<String, Object> result = new HashMap<>();
		if(loginId != null) {
			result.put("code", 1);
			result.put("result", "성공");
			result.put("loginId", loginId);
		} else {
			result.put("code", 300);
			result.put("errorMessage", "등록된 아이디가 없습니다.");
		}
		
		return result;
	}
}
