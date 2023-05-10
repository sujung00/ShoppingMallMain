package com.shoppingmall.productOption.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionMapper {

	public int insertProductOption(
			@Param("productId") int productId,
			@Param("color") String color,
			@Param("size") String size,
			@Param("stock") int stock);
}
