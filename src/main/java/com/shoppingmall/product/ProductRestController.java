package com.shoppingmall.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.productOption.bo.ProductOptionBO;

@RequestMapping("/product")
@RestController
public class ProductRestController {

	@Autowired
	private ProductOptionBO optionBO;
	
	/**
	 * COLOR에 따른 SIZE 가져오는 API
	 * @param productId
	 * @param color
	 * @return
	 */
	@PostMapping("/get_size")
	public Map<String, Object> getSize(
			@RequestParam("productId") int productId,
			@RequestParam("color") String color) {
		// size list 가져오기
		List<String> sizeList = optionBO.getSizeByProductIdColor(productId, color);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("sizeList", sizeList);
		result.put("result", "성공");
		return result;
	}
}
