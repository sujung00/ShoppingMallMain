package com.shoppingmall.order;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.order.bo.OrderBO;
import com.shoppingmall.order.bo.OrderProductBO;

@RequestMapping("/order")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private OrderProductBO orderProductBO;

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
	
	@PostMapping("/order_product_update")
	public Map<String, Object> orderProductUpdate(
			@RequestParam("orderProductId") int orderProductId,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("originColor") String originColor,
			@RequestParam("originSize") String originSize,
			@RequestParam("originCount") int originCount){
		// orderProduct update
		orderProductBO.updateOptionCountByOrderProductId(orderProductId, color, size, originColor, originSize, originCount);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "옵션을 수정하였습니다.");
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("orderId") int orderId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		
		// order delete
		int rowCount = orderBO.deleteOrderByOrderIdUserId(orderId, userId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "구매를 취소하였습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "구매를 취소하지 못했습니다.");
		}
		
		return result;
	}
}
