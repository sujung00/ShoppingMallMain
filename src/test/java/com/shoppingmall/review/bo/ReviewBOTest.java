package com.shoppingmall.review.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.model.Order;
import com.shoppingmall.review.model.Review;
import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.review.model.WrittenReviewView;

@SpringBootTest
class ReviewBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReviewBO reviewBO;
	
	@Test
	void 주문아이디제품아이디로리뷰가져오기() {
		logger.info("#### 주문아이디제품아이디로리뷰가져오기");
		Review review = reviewBO.getReviewByOrderIdProductId(3, 1);
		assertNotNull(review);
	}
	
	@Transactional
	@Test
	void 리뷰화면만들기() {
		logger.info("#### 리뷰화면만들기");
		Order order = new Order();
		order.setUserId(13);
		order.setAddressId(7);
		order.setOrderRequest(null);
		order.setPayType("신용카드");
		order.setTotalPay(30000);
		List<ReviewView> reviewViewList = reviewBO.generateReviewViewList(13, order);
		assertNotNull(reviewViewList);
	}
	
	@Transactional
	@Test
	void 리뷰뷰만들기() {
		logger.info("#### 리뷰뷰만들기");
		ReviewView reviewView = reviewBO.generateRevieView(13, 6);
		assertNotNull(reviewView);
	}
	
	@Test
	void 유저리뷰가져오기() {
		logger.info("#### 유저리뷰가져오기");
		List<Review> reviewList = reviewBO.getReviewListByUserId(2);
		assertNotNull(reviewList);
	}
	
	@Test
	void 작성한리뷰화면만들기() {
		logger.info("#### 작성한리뷰화면만들기");
		List<WrittenReviewView> writtenReviewViewList = reviewBO.generateWrittenReviewView(2);
		assertNotNull(writtenReviewViewList);
	}
	
	@Transactional
	@Test
	void 리뷰삭제하기() {
		logger.info("#### 리뷰삭제하기");
		int rowCount = reviewBO.deleteReviewByReviewId(3);
		assertNotNull(rowCount);
	}
	
	@Test
	void 리뷰가져오기() {
		logger.info("#### 리뷰가져오기");
		Review review = reviewBO.getReviewByReviewId(3);
		assertNotNull(review);
	}
	
	@Test
	void 제품리뷰리스트가져오기() {
		logger.info("#### 제품리뷰리스트가져오기");
		List<Review> revewList = reviewBO.getReviewListByProductId(27);
		assertNotNull(revewList);
	}
}
