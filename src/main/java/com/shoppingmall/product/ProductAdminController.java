package com.shoppingmall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductAdminBO;
import com.shoppingmall.product.model.Product;
 
@RequestMapping("/product_admin")
@Controller
public class ProductAdminController {

	@Autowired
	private ProductAdminBO productAdminBO;

	/**
	 * ADMIN - 제품 목록 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/product_list_view")
	public String productListView(Model model) {
		List<Product> productList = productAdminBO.getProductList();
		
		model.addAttribute("productList", productList);
		model.addAttribute("view", "/admin/product/productListView");
		return "template/layout";
	}
	
	/**
	 * ADMIN - 제품 등록 페이지
	 * @param model
	 * @return
	 */
	@RequestMapping("/product_create_view")
	public String productCreateView(Model model) {
		model.addAttribute("view", "/admin/product/productCreateView");
		return "template/layout";
	}
	
	/**
	 * ADMIN - 제품 수정 페이지
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/product_update_view")
	public String productUpdateView(
			@RequestParam("productId") int productId,
			Model model) {
		Product product = productAdminBO.getProductByProductId(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("view", "/admin/product/productUpdateView");
		return "template/layout";
	}
}
