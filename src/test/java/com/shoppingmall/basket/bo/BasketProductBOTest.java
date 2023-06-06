package com.shoppingmall.basket.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.basket.model.BasketProduct;
import com.shoppingmall.basket.model.BasketView;

@SpringBootTest
class BasketProductBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BasketProductBO basketProductBO;
	
	@Transactional
	@Test
	void 장바구니담기() {
		logger.info("#### 장바구니담기");
		basketProductBO.addBasketProduct(13, 23, "black", "s", 2);
	}
	
	@Transactional
	@Test
	void 장바구니제품수량수정하기() {
		logger.info("#### 장바구니제품수량수정하기");
		basketProductBO.updateBasketProduct(15, 5);
	}
	
	@Test
	void 장바구니아이디로장바구니제품가져오기() {
		logger.info("#### 장바구니아이디로장바구니제품가져오기");
		BasketProduct basketProduct = basketProductBO.getBasketProductByBasketId(5, 23, 45);
		assertNotNull(basketProduct);
	}
	
	@Test
	void 장바구니제품리스트가져오기() {
		logger.info("#### 장바구니제품리스트가져오기");
		List<BasketProduct> basketProductList = basketProductBO.getBasketListByBasketId(4);
		assertNotNull(basketProductList);
	}
	
	@Test
	void 장바구니화면만들기() {
		logger.info("#### 장바구니화면만들기");
		List<BasketView> basketViewList = basketProductBO.generateBasket(11);
		assertNotNull(basketViewList);
	}
	
	@Transactional
	@Test
	void 장바구니제품삭제하기() {
		logger.info("#### 장바구니제품삭제하기");
		int rowCount = basketProductBO.deleteBasketProductByBasketProductId(13, 5, 78);
		assertNotNull(rowCount);
	}
	
	@Test
	void 장바구니제품가져오기() {
		logger.info("#### 장바구니제품가져오기");
		BasketProduct basketProduct = basketProductBO.getBasketProductByBasketProductId(78);
		assertNotNull(basketProduct);
	}
	
	@Test
	void 장바구니제품들삭제() {
		logger.info("#### 장바구니제품들삭제");
		basketProductBO.deleteBasketProductByBasketId(4);
	}
}
