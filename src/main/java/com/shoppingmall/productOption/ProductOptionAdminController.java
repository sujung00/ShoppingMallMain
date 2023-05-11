package com.shoppingmall.productOption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductAdminBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionAdminBO;
import com.shoppingmall.productOption.model.ProductOption;

@RequestMapping("/product_option")
@Controller
public class ProductOptionAdminController {

	@Autowired
	private ProductAdminBO productAdminBO;
	
	@Autowired
	private ProductOptionAdminBO poaBO;

	@RequestMapping("/create_view")
	public String createView(
			@RequestParam("productId") int productId,
			Model model) {
		Product product = productAdminBO.getProductByProductId(productId);
		List<ProductOption> productOptionList = poaBO.getProductOptionListByProductId(productId);
		
		model.addAttribute("poList", productOptionList);
		model.addAttribute("product", product);
		model.addAttribute("view", "/admin/productOption/createView");
		return "template/layout";
	}
	
	@PostMapping("/update_view")
	public String updateView(
			@RequestParam("productoptionId") int productOptionId,
			@RequestParam("productId") int productId,
			Model model) {
		Product product = productAdminBO.getProductByProductId(productId);
		ProductOption productOption = poaBO.getProductOptionByProductOptionId(productOptionId);
	
		model.addAttribute("productOption", productOption);
		model.addAttribute("product", product);
		model.addAttribute("view", "/admin/productOption/updateView");
		return "template/layout";
	}
}
