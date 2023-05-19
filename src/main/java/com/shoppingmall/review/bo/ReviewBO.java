package com.shoppingmall.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.order.bo.OrderProductBO;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.review.dao.ReviewMapper;
import com.shoppingmall.review.model.Review;
import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.review.model.WrittenReviewView;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	public Review getReviewByOrderIdProductId(int orderId, int orderProductId) {
		return reviewMapper.selectReviewByOrderIdProductId(orderId, orderProductId);
	}
	
	public List<ReviewView> generateReviewViewList(int userId, Order order) {
		List<ReviewView> reviewViewList = new ArrayList<>();
		
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductListByOrderId(order.getId());
		// 리뷰가 없는 주문제품 중
		for(OrderProduct orderProduct : orderProductList) {
			Review review = getReviewByOrderIdProductId(order.getId(), orderProduct.getId());
			if(review == null && orderProduct.getState().equals("배송완료")) {
				ReviewView reviewView = new ReviewView();
				
				reviewView.setUserId(userId);
				
				reviewView.setOrderProduct(orderProduct);
				
				Product product = productBO.getProductByProductId(orderProduct.getProductId());
				reviewView.setProduct(product);
				
				ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(orderProduct.getOptionId());
				reviewView.setProductOption(productOption);
				
				reviewViewList.add(reviewView);
			}
		}
		return reviewViewList;
	}
	
	public ReviewView generateRevieView(int userId, int orderProductId) {
		ReviewView reviewView = new ReviewView();
		
		reviewView.setUserId(userId);
		
		OrderProduct orderProduct = orderProductBO.getOrderProductByOrderProductId(orderProductId);
		reviewView.setOrderProduct(orderProduct);
		
		Product product = productBO.getProductByProductId(orderProduct.getProductId());
		reviewView.setProduct(product);
		
		ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(orderProduct.getOptionId());
		reviewView.setProductOption(productOption);
		
		return reviewView;
	}
	
	public int addReview(int userId, int orderProductId, int orderId, String subject,
			String content, MultipartFile file, String loginId) {
		
		String reviewImagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			reviewImagePath = fileManager.saveFile(loginId + "(review)", file);
		}
		
		return reviewMapper.insertReview(userId, orderProductId, orderId, subject, content, reviewImagePath);
	}
	
	public List<Review> getReviewListByUserId(int userId){
		return reviewMapper.selectReviewListByUserId(userId);
	}
	
	public List<WrittenReviewView> generateWrittenReviewView(int userId){
		List<WrittenReviewView> writtenReviewViewList = new ArrayList<>();
		
		List<Review> reviewList = getReviewListByUserId(userId);
		for(Review review : reviewList) {
			WrittenReviewView writtenReviewView = new WrittenReviewView();
			writtenReviewView.setReview(review);
			
			OrderProduct orderProduct = orderProductBO.getOrderProductByOrderProductId(review.getOrderProductId());
			writtenReviewView.setOrderProduct(orderProduct);

			Product product = productBO.getProductByProductId(orderProduct.getProductId());
			writtenReviewView.setProduct(product);
			
			ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(orderProduct.getOptionId());
			writtenReviewView.setProductOption(productOption);
			
			writtenReviewViewList.add(writtenReviewView);
		}
		
		return writtenReviewViewList;
	}
	
	public int deleteReviewByReviewId(int reviewId) {
		return reviewMapper.deleteReviewByReviewId(reviewId);
	}
	
	public Review getReviewByReviewId(int reviewId) {
		return reviewMapper.selectReviewByReviewId(reviewId);
	}
}
