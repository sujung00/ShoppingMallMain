package com.shoppingmall.productOption.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.productOption.model.ProductOption;

@SpringBootTest
class ProductOptionBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductOptionBO productOptionBO;
	
	@Transactional
	@Test
	void 제품옵션추가하기() {
		logger.info("#### 제품옵션추가하기");
		int rowCount = productOptionBO.addProductOption(30, "black", "S", 100);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 제품아이디로제품옵션삭제하기() {
		logger.info("#### 제품아이디로제품옵션삭제하기");
		productOptionBO.deleteProductOptionByProductId(30);
	}
	
	@Test
	void 제품옵션리스트가져오기() {
		logger.info("#### 제품옵션리스트가져오기");
		List<ProductOption> productOptionList = productOptionBO.getProductOptionListByProductId(30);
		assertNotNull(productOptionList);
	}
	
	@Test
	void 제품옵션가져오기() {
		logger.info("#### 제품옵션가져오기");
		ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(13);
		assertNotNull(productOption);
	}
	
	@Test
	void 제품아이디색상사이즈로제품옵션가져오기() {
		logger.info("#### 제품아이디색상사이즈로제품옵션가져오기");
		ProductOption productOption = productOptionBO.getProductOptionByProductId(30, "white", "S");
		assertNotNull(productOption);
	}
	
	@Transactional
	@Test
	void 제품옵션재고수정하기() {
		logger.info("#### 제품옵션재고수정하기");
		int rowCount = productOptionBO.updateStockByProductOptionId(13, 100);
		assertNotNull(rowCount);
	}
	
	@Transactional
	@Test
	void 제품옵션삭제하기() {
		logger.info("#### 제품옵션삭제하기");
		int rowCount = productOptionBO.deleteProductOptionByProductOptionId(13);
		assertNotNull(rowCount);
	}
	
	@Test
	void 제품색상리스트가져오기() {
		logger.info("#### 제품색상리스트가져오기");
		List<String> colorList = productOptionBO.getColorByProductId(30);
		assertNotNull(colorList);
	}
	
	@Test
	void 제품사이즈리스트가져오기() {
		logger.info("#### 제품사이즈리스트가져오기");
		List<String> sizeList = productOptionBO.getSizeByProductIdColor(30, "white");
		assertNotNull(sizeList);
	}
}
