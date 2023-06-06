package com.shoppingmall.product.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.product.model.ProductImage;

@SpringBootTest
class ProductImageBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductImageBO productImageBO;
	
	@Test
	void 제품이미지가져오기() {
		logger.info("#### 제품이미지가져오기");
		List<ProductImage> productImageList = productImageBO.getProductImageList(30);
		assertNotNull(productImageList);
	}
	
	@Transactional
	@Test
	void 제품이미지제거() {
		logger.info("#### 제품이미지제거");
		productImageBO.deleteProductImage(30);
	}
}
