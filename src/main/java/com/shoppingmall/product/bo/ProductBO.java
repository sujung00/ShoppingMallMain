package com.shoppingmall.product.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.product.dao.ProductMapper;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.product.model.ProductImage;

@Service
public class ProductBO {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductImageBO productImageBO;
	
	@Autowired
	private FileManagerService fileManager;

	@Transactional
	public int addProduct(Product product, MultipartFile mainImage) {
		
		String mainImgaePath = null;
		if (mainImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			mainImgaePath = fileManager.saveFile(product.getName()+"(main)", mainImage);
		}
		
		return productMapper.insertProduct(product, mainImgaePath);
	}
	
	public List<Product> getProductList(){
		return productMapper.selectProductList();
	}
	
	public Product getProductByProductId(int productId) {
		return productMapper.selectProductByProductId(productId);
	}
	
	@Transactional
	public int updateProductByproductId(int productId, String name, String informaiton,
			int price, MultipartFile mainImage, String detailedInfo, String gender, List<MultipartFile> files) {
		Product product = getProductByProductId(productId);
		if(product == null) {
			logger.warn("[update product] product is null. productId:{}", productId);
		}
		
		String mainImgaePath = null;
		if (mainImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			mainImgaePath = fileManager.saveFile(name+"(main)", mainImage);
			
			// 기존 이미지 제거
			if (mainImgaePath != null && product.getMainImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(product.getMainImagePath());
			}
		}
		if(files != null) {
			productImageBO.deleteProductImage(productId);
			for(int i = 0; i < files.size(); i++) {
				productImageBO.updateProductImage(productId, name, files.get(i));
			}
		}
		
		return productMapper.updateProductByproductId(productId, name, informaiton, price, mainImgaePath, detailedInfo, gender);
	}
	
	@Transactional
	public int deleteProductByProductId(int productId) {
		return productMapper.deleteProductByProductId(productId);
	}
	
	public List<Product> getNewProductList(){
		return productMapper.selectNewProductList();
	}
	
	public List<Product> getWomanProductList(){
		return productMapper.selectWomanProductList();
	}
	
	public List<Product> getManProductList(){
		return productMapper.selectManProductList();
	}
}
