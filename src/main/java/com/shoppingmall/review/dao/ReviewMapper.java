package com.shoppingmall.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.review.model.Review;

@Repository
public interface ReviewMapper {

	public Review selectReviewByOrderIdProductId(
			@Param("orderId") int orderId,
			@Param("orderProductId") int orderProductId);
	
	public int insertReview(
			@Param("userId") int userId,
			@Param("productId") int productId,
			@Param("orderProductId") int orderProductId,
			@Param("orderId") int orderId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("reviewImagePath") String reviewImagePath);
	
	public List<Review> selectReviewListByUserId(int userId);
	
	public int deleteReviewByReviewId(int reviewId);
	
	public Review selectReviewByReviewId(int reviewId);
	
	public int updateReview(
			@Param("userId") int userId,
			@Param("reviewId") int reviewId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("reviewImagePath") String reviewImagePath);
	
	public List<Review> selectReviewListByProductId(int productId);
}
