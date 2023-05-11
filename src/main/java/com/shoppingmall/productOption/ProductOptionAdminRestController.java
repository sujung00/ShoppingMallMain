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
	private ProductOptionAdminBO poaBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("productId") int productId,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("stock") int stock) {
		// db insert
		int rowCount = poaBO.addProductOption(productId, color, size, stock);
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
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam("productOptionId") int productOptionId,
			@RequestParam("stock") int stock){
		// db update
		int rowCount = poaBO.updateStockByProductOptionId(productOptionId, stock);		
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "재고 수정에 성공했습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "옵션 수정에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("productOptionId") int productOptionId) {
		// db delete
		int rowCount = poaBO.deleteProductOptionByProductOptionId(productOptionId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "옵션 삭제에 성공했습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "옵션 삭제에 실패했습니다.");
		}
		
		return result;
	}
}
