package com.shoppingmall.productOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductAdminBO;
import com.shoppingmall.product.model.Product;

@RequestMapping("/product_option")
@Controller
public class ProductOptionAdminController {

	@Autowired
	private ProductAdminBO productAdminBO;

	@RequestMapping("/create_view")
	public String createView(
			@RequestParam("productId") int productId,
			Model model) {
		Product product = productAdminBO.getProductByProductId(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("view", "/admin/productOption/createView");
		return "template/layout";
	}
}
