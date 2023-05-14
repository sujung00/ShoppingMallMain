package com.shoppingmall.productOption.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.productOption.model.ProductOption;

@Repository
public interface ProductOptionMapper {

	public int insertProductOption(
			@Param("productId") int productId,
			@Param("color") String color,
			@Param("size") String size,
			@Param("stock") int stock);
	
	public void deleteProductOptionByProductId(int productId);
	
	public List<ProductOption> selectProductOptionListByProductId(int productId);

	public ProductOption selectProductOptionByProductOptionId(int productOptionId);
	
	public ProductOption selectProductOptionByProductId(
			@Param("productId") int productId,
			@Param("color") String color,
			@Param("size") String size);
	
	public int updateStockByProductOptionId(
			@Param("productOptionId") int productOptionId,
			@Param("stock") int stock);
	
	public int deleteProductOptionByProductOptionId(int productOptionId);
	
	public List<String> selectColorByProductId(int productId);
	
	public List<String> selectSizeByProductIdColor(
			@Param("productId") int productId,
			@Param("color") String color);
}
