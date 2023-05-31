package com.shoppingmall.basket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basket.bo.BasketProductBO;

@RequestMapping("/basket")
@RestController
public class BasketRestController {
	
	@Autowired
	private BasketProductBO basketProductBO;

	/**
	 * 장바구니 제품 추가 API
	 * @param userId
	 * @param productId
	 * @param color
	 * @param size
	 * @param count
	 * @return
	 */
	@PostMapping("/add_basket")
	public Map<String, Object> addBasket(
			@RequestParam("userId") int userId,
			@RequestParam("productId") int productId,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("count") int count) {
		// 장바구니에 제품 담기
		basketProductBO.addBasketProduct(userId, productId, color, size, count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "장바구니에 제품이 담겼습니다.");
		return result;
	}
}
