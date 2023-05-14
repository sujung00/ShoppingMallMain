package com.shoppingmall.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.product.dao.ProductImageMapper;
import com.shoppingmall.product.model.ProductImage;

@Service
public class ProductImageBO {

	@Autowired
	private FileManagerService fileManager;

	@Autowired
	private ProductImageMapper productImageMapper;

	public void addProductImage(int productId, String productName, MultipartFile productImage) {

		String imagePath = null;
		if (productImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			imagePath = fileManager.saveFile(productName + "(detail)", productImage);
		}

		productImageMapper.insertProductImage(productId, imagePath);
	}

	public List<ProductImage> getProductImageList(int productId) {
		return productImageMapper.selectProductImageList(productId);
	}

	public void updateProductImage(int productId, String productName, MultipartFile productImage) {

		String imagePath = null;
		if (productImage != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			imagePath = fileManager.saveFile(productName + "(detail)", productImage);
		}

		productImageMapper.insertProductImage(productId, imagePath);
	}

	public void deleteProductImage(int productId) {
		// 기존 상세 이미지 전부 제거
		List<ProductImage> productImageList = getProductImageList(productId);
		if (!productImageList.isEmpty()) {
			for (int i = 0; i < productImageList.size(); i++) {
				fileManager.deleteFile(productImageList.get(i).getImagePath());
			}
		}
		
		productImageMapper.deleteProductImage(productId);
	}
}
