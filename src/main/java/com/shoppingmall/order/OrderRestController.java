package com.shoppingmall.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.order.bo.OrderBO;

@RequestMapping("/order")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderBO orderBO;

	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("userId") int userId,
			@RequestParam("addressId") int addressId,
			@RequestParam("basketId") int basketId,
			@RequestParam("orderRequest") String orderRequest,
			@RequestParam(value="point", required=false) Integer usePoint,
			@RequestParam("payType") String payType,
			@RequestParam("totalPay") int totalPay) {
		// order db insert
		orderBO.generateOrder(userId, addressId, basketId, orderRequest, payType, totalPay, usePoint);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "주문이 완료되었습니다.");
		
		return result;
	}
}
