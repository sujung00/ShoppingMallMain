package com.shoppingmall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.product.model.ProductImage;

@Repository
public interface ProductImageMapper {

	public void insertProductImage(
			@Param("productId") int productId,
			@Param("productImagePath") String productImagePath);
	
	public List<ProductImage> selectProductImageList(int productId);
}
