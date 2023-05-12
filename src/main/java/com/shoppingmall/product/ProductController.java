package com.shoppingmall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductBO productBO;

	@RequestMapping("/new_product_view")
	public String newProductView(Model model) {
		// 신상품 list
		List<Product> newProductList = productBO.getNewProductList();
		
		model.addAttribute("newProductList", newProductList);
		model.addAttribute("view", "/product/newProductView");
		return "template/layout";
	}
	
	@RequestMapping("/product_detail_view")
	public String productDetailView(
			@RequestParam("productId") int productId,
			Model model) {
		// 상품 가져오기
		
		// 상품 옵션 가져오기
		
		model.addAttribute("view", "/product/detailView");
		return "template/layout";
	}
}
