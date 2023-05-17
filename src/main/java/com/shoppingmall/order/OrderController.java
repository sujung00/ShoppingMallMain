package com.shoppingmall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.order.bo.OrderServiceBO;
import com.shoppingmall.order.model.OrderView;

@RequestMapping("/order")
@Controller
public class OrderController {
	
	@Autowired
	private OrderServiceBO orderServiceBO;

	@PostMapping("/order_view")
	public String orderView(
			@RequestParam("basketId") int basketId,
			Model model) {
		OrderView orderView = orderServiceBO.generateOrderView(basketId);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("view", "/order/orderView");
		return "template/layout";
	}
}
