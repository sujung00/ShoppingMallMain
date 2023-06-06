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
class ProductAdminBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductAdminBO productAdminBO;
	
	@Test
	void 제품리스트가져오기() {
		logger.info("#### 제품리스트가져오기");
		List<Product> productList = productAdminBO.getProductList();
		assertNotNull(productList);
	}
	
	@Test
	void 제품가져오기() {
		logger.info("#### 제품가져오기");
		Product product = productAdminBO.getProductByProductId(30);
		assertNotNull(product);
	}
	
	@Transactional
	@Test
	void 제품삭제하기() {
		logger.info("#### 제품삭제하기");
		int rowCount = productAdminBO.deleteProductByProductId(30);
		assertNotNull(rowCount);
	}
}
