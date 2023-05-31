package com.shoppingmall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.bo.ProductImageBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.product.model.ProductImage;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.review.bo.ReviewBO;
import com.shoppingmall.review.model.Review;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private ProductImageBO productImageBO;
	
	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Autowired
	private ReviewBO reviewBO;

	/**
	 * 신상품 목록 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/new_product_view")
	public String newProductView(Model model) {
		// 신상품 list
		List<Product> newProductList = productBO.getNewProductList();
		
		model.addAttribute("newProductList", newProductList);
		model.addAttribute("view", "/product/newProductView");
		return "template/layout";
	}
	
	/**
	 * 제품 상세 화면
	 * @param productId
	 * @param model
	 * @return
	 */
	@PostMapping("/product_detail_view")
	public String productDetailView(
			@RequestParam("productId") int productId,
			Model model) {
		// 상품 가져오기
		Product product = productBO.getProductByProductId(productId);
		
		// 상품 옵션(색상) 가져오기
		List<String> colorList = productOptionBO.getColorByProductId(productId);
		
		// 상품 상세 이미지 가져오기
		List<ProductImage> productImageList = productImageBO.getProductImageList(productId);
		
		// 제품 리뷰 가져오기
		List<Review> reviewList = reviewBO.getReviewListByProductId(productId);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("product", product);
		model.addAttribute("colorList", colorList);
		model.addAttribute("productImageList", productImageList);
		model.addAttribute("view", "/product/detailView");
		return "template/layout";
	}
	
	/**
	 * 여성 상품 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/woman_product_view")
	public String productWomanView(Model model) {
		// 여성 상품 list
		List<Product> womanProductList = productBO.getWomanProductList();
		
		model.addAttribute("womanProductList", womanProductList);
		model.addAttribute("view", "/product/womanProduct");
		return "template/layout";
	}
	
	/**
	 * 남성 상품 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/man_product_view")
	public String productManView(Model model) {
		// 남성 상품 list
		List<Product> manProductList = productBO.getManProductList();
		
		model.addAttribute("manProductList", manProductList);
		model.addAttribute("view", "/product/manProduct");
		return "template/layout";
	}
}
