package com.shoppingmall.product.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.product.dao.ProductMapper;
import com.shoppingmall.product.model.Product;

@Service
public class ProductBO {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	public int updateProductByproductId(int productId, String name, String informaiton,
			int price, MultipartFile mainImage, String detailedInfo, String gender) {
		Product product = getProductByProductId(productId);
		if(product == null) {
			logger.warn("[update product] product is null. productId:{}", productId);
		}
		
		String mainImgaePath = null;
		if (mainImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			mainImgaePath = fileManager.saveFile(name, mainImage);
			
			// 기존 이미지 제거
			if (mainImgaePath != null && product.getMainImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(product.getMainImagePath());
			}
		}
		
		return productMapper.updateProductByproductId(productId, name, informaiton, price, mainImgaePath, detailedInfo, gender);
	}
	
	public int deleteProductByProductId(int productId) {
		return productMapper.deleteProductByProductId(productId);
	}
}
