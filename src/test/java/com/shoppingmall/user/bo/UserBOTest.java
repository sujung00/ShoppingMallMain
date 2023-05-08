package com.shoppingmall.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.user.model.User;

@SpringBootTest
class UserBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserBO userBO;
	
	@Transactional // insert 후 다시 rollback
	//@Test
	void 회원가입() {
		logger.info("#### 회원가입");
		userBO.addUser("test456", "aaaa", "test456", "test@google.com", "010000000000");
	}
	
	@Test
	void 유저가져오기() {
		User user = userBO.getUserByLoginId("susu");
		assertNotNull(user);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
