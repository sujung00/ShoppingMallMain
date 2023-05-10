package com.shoppingmall.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.product.model.Product;


@Service
public class ProductAdminBO {
	
	@Autowired
	private ProductBO productBO;
	
	public int addProduct(String name, String information, int price,
			MultipartFile mainImage, String detailedInfo, String gender) {
		
		return productBO.addProduct(name, information, price, mainImage, detailedInfo, gender);
	}
	
	public List<Product> getProductList(){
		return productBO.getProductList();
	}
	
	public Product getProductByProductId(int productId) {
		return productBO.getProductByProductId(productId);
	}
}
