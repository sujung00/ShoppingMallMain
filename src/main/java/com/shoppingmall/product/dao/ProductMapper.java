package com.shoppingmall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.product.model.Product;

@Repository
public interface ProductMapper {

	public int insertProduct(
			@Param("name") String name,
			@Param("information") String information,
			@Param("price") int price,
			@Param("mainImagePath") String mainImagePath,
			@Param("detailedInfo") String detailedInfo,
			@Param("gender") String gender);
	
	public List<Product> selectProductList();
	
	public Product selectProductByProductId(int productId);
	
	public int updateProductByproductId(
			@Param("productId") int productId,
			@Param("name") String name,
			@Param("information") String information,
			@Param("price") int price,
			@Param("mainImagePath") String mainImagePath,
			@Param("detailedInfo") String detailedInfo,
			@Param("gender") String gender);
	
	public int deleteProductByProductId(int productId);
}
