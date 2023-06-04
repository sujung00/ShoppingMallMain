package com.shoppingmall.basket.bo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.basket.model.Basket;

@SpringBootTest
class BasketBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BasketBO basketBO;
	
	@Transactional
	@Test
	void 장바구니생성() {
		logger.info("#### 장바구니생성");
		basketBO.addBasket(13);
	}
	
	@Test
	void 장바구니가져오기() {
		logger.info("#### 장바구니가져오기");
		Basket basket = basketBO.getBasketByUserId(13);
		assertNotNull(basket);
	}
	
	@Transactional
	@Test
	void 총금액수정하기() {
		logger.info("#### 총금액수정하기");
		basketBO.updateTotalPrice(13, 100000);
	}
	
	@Test
	void 장바구니아이디로장바구니가져오기() {
		logger.info("#### 장바구니아이디로장바구니가져오기");
		Basket basket = basketBO.getBasketByBasketId(2);
		assertNotNull(basket);
	}
	
}
