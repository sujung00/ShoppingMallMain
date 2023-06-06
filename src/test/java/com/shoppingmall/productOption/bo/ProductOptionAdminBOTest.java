package com.shoppingmall.productOption.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.productOption.bo.ProductOptionAdminBO;
import com.shoppingmall.productOption.model.ProductOption;

@SpringBootTest
class ProductOptionAdminBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductOptionAdminBO productOptionAdminBO;
	
	@Transactional
	@Test
	void 제품옵션추가하기() {
		logger.info("#### 제품옵션추가하기");
		int rowCount = productOptionAdminBO.addProductOption(30, "black", "S", 100);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 제품아이디로제품옵션삭제하기() {
		logger.info("#### 제품아이디로제품옵션삭제하기");
		productOptionAdminBO.deleteProductOptionByProductId(30);
	}
	
	@Test
	void 제품옵션리스트가져오기() {
		logger.info("#### 제품옵션리스트가져오기");
		List<ProductOption> productOptionList = productOptionAdminBO.getProductOptionListByProductId(30);
		assertNotNull(productOptionList);
	}
	
	@Test
	void 제품옵션가져오기() {
		logger.info("#### 제품옵션가져오기");
		ProductOption productOption = productOptionAdminBO.getProductOptionByProductOptionId(13);
		assertNotNull(productOption);
	}
	
	@Transactional
	@Test
	void 제품옵션재고수정하기() {
		logger.info("#### 제품옵션재고수정하기");
		int rowCount = productOptionAdminBO.updateStockByProductOptionId(13, 100);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 제품옵션삭제하기() {
		logger.info("#### 제품옵션삭제하기");
		int rowCount = productOptionAdminBO.deleteProductOptionByProductOptionId(13);
		assertNotNull(rowCount);
	}
}
