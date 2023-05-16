package com.shoppingmall.basket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basket.bo.BasketProductBO;

@RequestMapping("/basket_product")
@RestController
public class BasketProductRestController {

	@Autowired
	private BasketProductBO basketProductBO;
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("basketId") int basketId,
			@RequestParam("basketProductId") int basketProductId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		// db delete
		int rowCount = basketProductBO.deleteBasketProductByBasketProductId(userId, basketId, basketProductId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "장바구니 제품이 삭제되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "장바구니 제품 삭제에 실패하였습니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
}
