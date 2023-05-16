package com.shoppingmall.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
@Controller
public class OrderController {

	@RequestMapping("/order_view")
	public String orderView(Model model) {
		model.addAttribute("view", "/order/orderView");
		return "template/layout";
	}
}
