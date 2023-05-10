package com.shoppingmall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
