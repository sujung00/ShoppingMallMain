package com.shoppingmall.productOption;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.productOption.bo.ProductOptionAdminBO;

@RequestMapping("/product_option")
@RestController
public class ProductOptionAdminRestController {

	@Autowired
	private ProductOptionAdminBO pdaBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("productId") int productId,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("stock") int stock) {
		// db insert
		int rowCount = pdaBO.addProductOption(productId, color, size, stock);
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "제품 옵션 추가에 성공했습니다.");
		} else {
			result.put("code", 300);
			result.put("errorMessage", "제품 옵션 추가에 실패했습니다.");
		}
		
		
		
		return result;
	}
}
