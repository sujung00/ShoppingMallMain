package com.shoppingmall.productOption.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.productOption.model.ProductOption;

@Service
public class ProductOptionAdminBO {

	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Transactional
	public int addProductOption(int productId, String color, String size, int stock) {
		return productOptionBO.addProductOption(productId, color, size, stock);
	}
	
	@Transactional
	public void deleteProductOptionByProductId(int productId) {
		productOptionBO.deleteProductOptionByProductId(productId);
	}
	
	public List<ProductOption> getProductOptionListByProductId(int productId){
		return productOptionBO.getProductOptionListByProductId(productId);
	}
	
	public ProductOption getProductOptionByProductOptionId(int productOptionId) {
		return productOptionBO.getProductOptionByProductOptionId(productOptionId);
	}
	
	@Transactional
	public int updateStockByProductOptionId(int productOptionId, int stock) {
		return productOptionBO.updateStockByProductOptionId(productOptionId, stock);
	}
	
	@Transactional
	public int deleteProductOptionByProductOptionId(int productOptionId) {
		return productOptionBO.deleteProductOptionByProductOptionId(productOptionId);
	}
}
