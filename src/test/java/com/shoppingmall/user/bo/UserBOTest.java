package com.shoppingmall.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.common.EncryptUtils;
import com.shoppingmall.user.model.User;

@SpringBootTest
class UserBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserBO userBO;
	
	@Transactional // insert 후 다시 rollback
	@Test
	void 회원가입() {
		logger.info("#### 회원가입");
		User user = new User();
		user.setLoginId("test123");
		user.setPassword("aaaa");
		user.setName("test123");
		user.setEmail("test@gmail.com");
		user.setPhoneNumber("01012345678");
		user.setKakaoUser(false);
		userBO.addUser(user);
	}
	
	@Test
	void 로그인아이디로유저가져오기() {
		logger.info("#### 로그인아이디로유저가져오기");
		User user = userBO.getUserByLoginId("susu");
		assertNotNull(user);
	}
	
	@Test
	void 로그인아이디와비밀번호로유저가져오기() {
		logger.info("#### 로그인아이디와비밀번호로유저가져오기");
		String hashedPassword = EncryptUtils.md5("aaaa");
		User user = userBO.getUserByLoginIdPassword("susu", hashedPassword);
		assertNotNull(user);
	}
	
	@Test
	void 이메일로유저리스트가져오기() {
		logger.info("#### 이메일로유저리스트가져오기");
		List<User> userList = userBO.getUserListByEmail("rlatnwjdirin@gmail.com");
		assertNotNull(userList);
	}
	
	@Test
	void 이름이메일로로그인아이디가져오기() {
		logger.info("#### 이름이메일로로그인아이디가져오기");
		String name = "김수정";
		String email = "rlatnwjdirin@gmail.com";
		String loginId = userBO.getLoginIdByNameEmail(name, email);
		assertNotNull(loginId);
	}
	
	@Test
	void 로그인아이디이름이메일로유저가져오기() {
		logger.info("#### 로그인아이디이름이메일로유저가져오기");
		String loginId = "sujung";
		String name = "김수정";
		String email = "rlatnwjdirin@gmail.com";
		User user = userBO.getUserByLoginIdNameEmail(loginId, name, email);
		assertNotNull(user);
	}
	
	@Transactional
	@Test
	void 유저비밀번호변경() {
		int userId = 13;
		String password = "aaaaa";
		String hashedPassword = EncryptUtils.md5(password);
		userBO.updatePWByUserId(hashedPassword, userId);
	}
	
	@Transactional
	@Test
	void 전화번호변경() {
		int userId = 13;
		String phoneNumber = "01077778888";
		userBO.updatePhoneNumber(phoneNumber, userId);
	}
	
	@Test
	void 유저아이디로유저가져오기()	{
		int userId = 13;
		User user = userBO.getUserByUserId(userId);
		assertNotNull(user);
	}
	
	@Test
	void 로그인아이디이메일로유저가져오기() {
		String loginId = "sujung";
		String email = "rlatnwjdirin@gmail.com";
		User user = userBO.getUserByLoginIdEmail(loginId, email);
		assertNotNull(user);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
