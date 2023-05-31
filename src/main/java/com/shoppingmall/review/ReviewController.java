package com.shoppingmall.review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.order.bo.OrderBO;
import com.shoppingmall.order.bo.OrderProductBO;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.review.bo.ReviewBO;
import com.shoppingmall.review.model.Review;
import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.review.model.WrittenReviewView;

@RequestMapping("/review")
@Controller
public class ReviewController {

	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private ReviewBO reviewBO;
	
	/**
	 * MY TREND > 리뷰 화면
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/review_view")
	public String reviewCreate(Model model, HttpSession session) {
		// state가 배송완료인 orderProductList 가져오기
		int userId = (int)session.getAttribute("userId");
		
		List<Order> newestOrderList = orderBO.getNewestOrderListByUserId(userId);
		// 작성 가능한 리뷰
		List<ReviewView> reviewViewList = new ArrayList<>();
		for(Order order : newestOrderList) {
			List<ReviewView> reviewViews =  reviewBO.generateReviewViewList(userId, order);
			for(ReviewView reviewView : reviewViews) {
				reviewViewList.add(reviewView);  
			}
		}
		// 작성한 리뷰
		List<WrittenReviewView> writtenReviewList = reviewBO.generateWrittenReviewView(userId);
		
		model.addAttribute("writtenReviewList", writtenReviewList);
		model.addAttribute("reviewViewList", reviewViewList);
		model.addAttribute("view", "/review/reviewView");
		return "template/layout";
	}
	
	/**
	 * 리뷰 화면(작성 가능한 리뷰) > 리뷰 작성 화면
	 * @param orderProductId
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/review_create")
	public String reviewCreate(
			@RequestParam("orderProductId") int orderProductId,
			Model model,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		ReviewView reviewView = reviewBO.generateRevieView(userId, orderProductId);
		
		model.addAttribute("reviewView", reviewView);
		model.addAttribute("view", "/review/reviewCreate");
		return "template/layout";
	}
	
	/**
	 * 리뷰 화면(작성한 리뷰) > 리뷰 수정 화면
	 * @param reviewId
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/review_update")
	public String reviewUpdate(
			@RequestParam("reviewId") int reviewId,
			Model model,
			HttpSession session) {
		// 주문 가져오기
		int userId = (int)session.getAttribute("userId");
		
		Review review = reviewBO.getReviewByReviewId(reviewId);
		int orderProductId = review.getOrderProductId();
		ReviewView reviewView = reviewBO.generateRevieView(userId, orderProductId);
		
		model.addAttribute("review", review);
		model.addAttribute("reviewView", reviewView);
		model.addAttribute("view", "/review/reviewUpdate");
		return "template/layout";
	}
}
