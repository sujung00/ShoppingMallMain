package com.shoppingmall.productOption.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.productOption.dao.ProductOptionMapper;

@Service
public class ProductOptionBO {
	
	@Autowired
	private ProductOptionMapper productOptionMapper;

	public int addProductOption(int productId, String color, String size, int stock) {
		return productOptionMapper.insertProductOption(productId, color, size, stock);
	}
}
