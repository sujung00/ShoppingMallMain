package com.shoppingmall.basket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/basket")
@Controller
public class BasketController {

	@RequestMapping("/basket_view")
	public String basketView(Model model) {
		model.addAttribute("view", "/basket/basketView");
		return "template/layout";
	}
}
