package com.shoppingmall.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.review.bo.ReviewBO;

@RequestMapping("/review")
@RestController
public class ReviewRestController {

	@Autowired
	private ReviewBO reviewBO;
	
	@PostMapping("/create")
	public Map<String, Object> reviewCreate(
			@RequestParam("userId") int userId,
			@RequestParam("orderProductId") int orderProductId,
			@RequestParam("orderId") int orderId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required=false) MultipartFile reviewImage,
			HttpSession session){
		String loginId = (String)session.getAttribute("userLoginId");
		// db insert
		int rowCount = reviewBO.addReview(userId, orderProductId, orderId, subject, content, reviewImage, loginId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "리뷰가 등록되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰가 등록되지 않았습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> reviewDelete(
			@RequestParam("reviewId") int reviewId){
		// db delete
		int rowCount = reviewBO.deleteReviewByReviewId(reviewId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "리뷰가 삭제되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰 삭제에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> reviewUpdate(
			@RequestParam("userId") int userId,
			@RequestParam("reviewId") int reviewId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required=false) MultipartFile reviewImage,
			HttpSession session){
		String loginId = (String)session.getAttribute("userLoginId");
		
		int rowCount = reviewBO.updateReview(userId, loginId, reviewId, subject, content, reviewImage);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "리뷰를 수정했습니다.");
		return result;
	}
}
