package com.shoppingmall.productOption.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.productOption.dao.ProductOptionMapper;
import com.shoppingmall.productOption.model.ProductOption;

@Service
public class ProductOptionBO {
	
	@Autowired
	private ProductOptionMapper productOptionMapper;

	public int addProductOption(int productId, String color, String size, int stock) {
		return productOptionMapper.insertProductOption(productId, color, size, stock);
	}
	
	public void deleteProductOptionByProductId(int productId) {
		productOptionMapper.deleteProductOptionByProductId(productId);
	}
	
	public List<ProductOption> getProductOptionListByProductId(int productId){
		return productOptionMapper.selectProductOptionListByProductId(productId);
	}
	
	public ProductOption getProductOptionByProductOptionId(int productOptionId) {
		return productOptionMapper.selectProductOptionByProductOptionId(productOptionId);
	}
	
	public ProductOption getProductOptionByProductId(int productId, String color, String size) {
		return productOptionMapper.selectProductOptionByProductId(productId, color, size);
	}
	
	public int updateStockByProductOptionId(int productOptionId, int stock) {
		return productOptionMapper.updateStockByProductOptionId(productOptionId, stock);
	}
	
	public int deleteProductOptionByProductOptionId(int productOptionId) {
		return productOptionMapper.deleteProductOptionByProductOptionId(productOptionId);
	}
	
	public List<String> getColorByProductId(int productId){
		return productOptionMapper.selectColorByProductId(productId);
	}
	
	public List<String> getSizeByProductIdColor(int productId, String color){
		return productOptionMapper.selectSizeByProductIdColor(productId, color);
	}
}
