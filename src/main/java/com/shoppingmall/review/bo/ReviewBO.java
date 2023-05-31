package com.shoppingmall.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
	
	@Transactional
	public int addReview(int userId, int orderProductId, int orderId, String subject,
			String content, MultipartFile file, String loginId) {
		
		String reviewImagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			reviewImagePath = fileManager.saveFile(loginId + "(review)", file);
		}
		
		OrderProduct orderProduct = orderProductBO.getOrderProductByOrderProductId(orderProductId);
		int productId = orderProduct.getProductId();
		
		return reviewMapper.insertReview(userId, productId, orderProductId, orderId, subject, content, reviewImagePath);
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
	
	@Transactional
	public int updateReview(int userId, String loginId, int reviewId,
			String subject, String content, MultipartFile file) {
		
		// 기존 리뷰를 가져온다(이미지 교체 시 기존 이미지 제거를 위해)
		Review review = getReviewByReviewId(reviewId);
		if(review == null) {
			logger.warn("[update review] review is null. reviewId:{}, userId:{}", reviewId, userId);
		}
		
		// 업로드한 이미지가 있으면 서버 업로드 => imagePath 받아옴 => 업로드 성공 시 기존 이미지 제거
		String reviewImagePath =  null;
		if(file != null) {
			// 업로드 
			reviewImagePath = fileManager.saveFile(loginId + "(review)", file);
			
			// 성공 여부 체크 후 기존 이미지 제거
			// -- reviewImagePath가 null이 아닐 때(성공) 그리고 기존 이미지가 있을 때 => 기존 이미지 삭제
			if(reviewImagePath != null && review.getReviewImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(review.getReviewImagePath());
			}
		}
		
		return reviewMapper.updateReview(userId, reviewId, subject, content, reviewImagePath);
	}
	
	public List<Review> getReviewListByProductId(int productId){
		return reviewMapper.selectReviewListByProductId(productId);
	}
}
