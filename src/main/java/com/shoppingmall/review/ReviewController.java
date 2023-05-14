package com.shoppingmall.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;

@RequestMapping("/review")
@Controller
public class ReviewController {

	@Autowired
	private ProductBO productBO;
	
	@PostMapping("/create")
	public String reviewCreate(
			@RequestParam("productId") int productId,
			Model model) {
		// 주문 가져오기
		Product product = productBO.getProductByProductId(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("view", "/review/reviewCreate");
		return "template/layout";
	}
	
	@PostMapping("/update")
	public String reviewUpdate(
			@RequestParam("productId") int productId,
			Model model) {
		// 주문 가져오기
		Product product = productBO.getProductByProductId(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("view", "/review/reviewUpdate");
		return "template/layout";
	}
}
