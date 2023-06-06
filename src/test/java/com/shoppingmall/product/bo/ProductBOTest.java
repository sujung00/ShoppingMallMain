package com.shoppingmall.product.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.product.model.Product;

@SpringBootTest
class ProductBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductBO productBO;
	
	@Test
	void 전제품가져오기() {
		logger.info("#### 전제품가져오기");
		List<Product> productList = productBO.getProductList();
		assertNotNull(productList);
	}
	
	@Test
	void 상품가져오기() {
		logger.info("#### 상품가져오기");
		Product product = productBO.getProductByProductId(30);
		assertNotNull(product);
	}
	
	@Transactional
	@Test
	void 제품삭제하기() {
		logger.info("#### 제품삭제하기");
		int rowCount = productBO.deleteProductByProductId(30);
		assertNotNull(rowCount);
	}
	
	@Test
	void 신제품가져오기() {
		logger.info("#### 신제품가져오기");
		List<Product> productList = productBO.getNewProductList();
		assertNotNull(productList);
	}
	
	@Test
	void 여성제품가져오기() {
		logger.info("#### 여성제품가져오기");
		List<Product> productList = productBO.getWomanProductList();
		assertNotNull(productList);
	}
	
	@Test
	void 남성제품가져오기() {
		logger.info("#### 남성제품가져오기");
		List<Product> productList = productBO.getManProductList();
		assertNotNull(productList);
	}
}
