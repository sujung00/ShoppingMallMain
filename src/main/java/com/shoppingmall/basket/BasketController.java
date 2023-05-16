package com.shoppingmall.basket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.bo.BasketProductBO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketProduct;
import com.shoppingmall.basket.model.BasketView;

@RequestMapping("/basket")
@Controller
public class BasketController {
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private BasketProductBO basketProductBO;

	@RequestMapping("/basket_view")
	public String basketView(Model model, HttpSession session) {
		// 유저 정보
		int userId = (int)session.getAttribute("userId");

		Basket basket = basketBO.getBasketByUserId(userId);
		List<BasketView> basketViewList = basketProductBO.generateBasket(userId);
		
		model.addAttribute("basket", basket);
		model.addAttribute("basketViewList", basketViewList);
		model.addAttribute("view", "/basket/basketView");
		return "template/layout";
	}
}
