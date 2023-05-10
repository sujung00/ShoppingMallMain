package com.shoppingmall.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.product.dao.ProductMapper;
import com.shoppingmall.product.model.Product;

@Service
public class ProductBO {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManagerService fileManager;

	public int addProduct(String name, String information, int price,
			MultipartFile mainImage, String detailedInfo, String gender) {
		
		String mainImgaePath = null;
		if (mainImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			mainImgaePath = fileManager.saveFile(name, mainImage);
		}
		
		return productMapper.insertProduct(name, information, price, mainImgaePath, detailedInfo, gender);
	}
	
	public List<Product> getProductList(){
		return productMapper.selectProductList();
	}
	
	public Product getProductByProductId(int productId) {
		return productMapper.selectProductByProductId(productId);
	}
}
