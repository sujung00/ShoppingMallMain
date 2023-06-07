package com.shoppingmall.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.common.EncryptUtils;
import com.shoppingmall.point.bo.PointBO;
import com.shoppingmall.user.bo.MailBO;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.Mail;
import com.shoppingmall.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private MailBO mailBO;
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private PointBO pointBO;
	
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
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("kakaoUser") boolean kakaoUser){
		Map<String, Object> result = new HashMap<>();
		
		// id 중복 확인
		User idUser = userBO.getUserByLoginId(loginId);
		if(idUser != null) {
			result.put("code", 300);
			result.put("errorMessage", "이미 존재하는 아이디 입니다.");
			
			return result;
		}
		// email 중복 확인
		List<User> emailUser = userBO.getUserListByEmail(email);
		if(emailUser != null) {
			for(User user : emailUser) {
				if(user.getKakaoUser() == kakaoUser) {
					result.put("code", 301);
					result.put("errorMessage", "이미 존재하는 이메일 입니다.");
					
					return result;
				}
			}
		}
		
		// password 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// db insert
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword(hashedPassword);
		user.setName(name);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setKakaoUser(kakaoUser);

		int rowCount = userBO.addUser(user);
		
		// 장바구니 생성
		int userId = user.getId();
		basketBO.addBasket(userId);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "회원가입이 완료되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패했습니다. 관리자에게 문의해주세요.");
		}
		
		// point 적립
		pointBO.addPoint(userId, 2000, "회원가입 적립", 2000);
		
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
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userEmail", user.getEmail());
			session.setAttribute("userPhoneNumber", user.getPhoneNumber());
		} else {
			result.put("code", 300);
			result.put("errorMessage", "입력하신 아이디와 비밀번호는 등록되어 있지 않습니다.");
		}
		
		return result;
	}
	
	/**
	 * 아이디 찾기 API
	 * @param name
	 * @param email
	 * @return
	 */
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
	
	/**
	 * 비밀번호 찾기 API + 메일 보내기
	 * @param loginId
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/find_pw")
	public Map<String, Object> findPW(
			@RequestParam("loginId") String loginId,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		// 존재하는 계정인지 확인
		User user = userBO.getUserByLoginIdNameEmail(loginId, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			if(user.getLoginId().startsWith("kakao@")) {
				result.put("code", 301);
				result.put("errorMessage", "카카오 계정입니다. 카카오 계정으로 로그인 해주세요.");
				
				return result;
			}
			
			//존재하는 계정이면 등록된 이메일로 메일 보냄
			Mail mail = mailBO.createMailAndChangePassword(user.getLoginId(), user.getEmail(), user.getName());
			mailBO.mailSend(mail);
			
			result.put("code", 1);
			result.put("result", "회원가입 시 등록하신 이메일로 임시 비밀번호를 전송하였습니다.\n MYTRNED > 회원정보 에서 비밀번호를 변경해주시길 바랍니다.");
		} else {
			result.put("code", 300);
			result.put("errorMessage", "존재하지 않는 계정입니다.");
		}
		
		return result;
	}
	
	/**
	 * 이름 변경 API
	 * @param name
	 * @param session
	 * @return
	 */
	@PostMapping("/update_name")
	public Map<String, Object> updateName(
			@RequestParam("name") String name,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		int rowCount = userBO.updateNamerByUserId(name, userId);		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "이름 변경에 성공하였습니다.");
			
			session.setAttribute("userName", name);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "이름 변경에 실패하였습니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 전화번호 변경 API
	 * @param phoneNumber
	 * @param session
	 * @return
	 */
	@PostMapping("/update_phonenumber")
	public Map<String, Object> updatePhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		int rowCount = userBO.updatePhoneNumber(phoneNumber, userId);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "휴대폰 번호 변경에 성공하였습니다.");
			
			session.setAttribute("userPhoneNumber", phoneNumber);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "휴대폰 번호 변경에 실패하였습니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 비밀번호 변경 API
	 * @param nowPassword
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/update_password")
	public Map<String, Object> updatePassword(
			@RequestParam("nowPassword") String nowPassword,
			@RequestParam("password") String password,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		String hashednowPW = EncryptUtils.md5(nowPassword);
		String hashedPW = EncryptUtils.md5(password);
		
		User user = userBO.getUserByLoginIdPassword(userLoginId, hashednowPW);
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			//비밀번호 변경
			int rowCount = userBO.updatePWByUserId(hashedPW, userId);
			if(rowCount > 0) {
				result.put("code", 1);
				result.put("result", "비밀번호 변경에 성공하였습니다.");
			} else {
				result.put("code", 500);
				result.put("result", "비밀번호 변경에 실패하였습니다. 관리자에게 문의 바랍니다.");
				
				return result;
			}
		} else {
			result.put("code", 300);
			result.put("result", "현재 비밀번호가 일치하지 않습니다.");
		}
		
		return result;
	}
}
