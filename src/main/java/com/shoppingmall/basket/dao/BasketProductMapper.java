package com.shoppingmall.basket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.basket.model.BasketProduct;

@Repository
public interface BasketProductMapper {

	public BasketProduct selectBasketProductByBasketId(
			@Param("basketId") int basketId,
			@Param("productId") int productId,
			@Param("optionId") int productOptionId);
	
	public void insertBasketProduct(
			@Param("userId") int userId,
			@Param("basketId") int basketId,
			@Param("productId") int productId,
			@Param("optionId") int optionId,
			@Param("count") int count);
	
	public void updateBasketProduct(
			@Param("basketProductId") int basketProductId,
			@Param("count") int count);
	
	public List<BasketProduct> selectBasketListByBasketId(int basketId);
	
	public int deleteBasketProductByBasketProductId(int basketProductId);
	
	public BasketProduct selectBasketProductByBasketProductId(int basketProductId);
}
