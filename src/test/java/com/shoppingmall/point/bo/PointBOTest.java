package com.shoppingmall.point.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.point.model.Point;

@SpringBootTest
class PointBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PointBO pointBO;
	
	@Test
	void 포인트이용내역가져오기() {
		logger.info("#### 포인트이용내역가져오기");
		List<Point> pointList = pointBO.getPointListByUserId(13);
		assertNotNull(pointList);
	}
	
	@Test
	void 사용가능한포인트가져오기() {
		logger.info("#### 사용가능한포인트가져오기");
		int totalPoint = pointBO.getTotalPointByUserId(13);
		assertNotNull(totalPoint);
	}
	
	@Transactional
	@Test
	void 포인트추가하기() {
		logger.info("#### 포인트추가하기");
		pointBO.addPoint(13, -200, "결제 시 사용", 3293);
	}
}
