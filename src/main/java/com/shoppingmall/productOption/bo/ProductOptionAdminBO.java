package com.shoppingmall.productOption.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionAdminBO {

	@Autowired
	private ProductOptionBO productOptionBO;
	
	public int addProductOption(int productId, String color, String size, int stock) {
		return productOptionBO.addProductOption(productId, color, size, stock);
	}
}
