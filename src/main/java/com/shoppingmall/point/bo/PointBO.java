package com.shoppingmall.point.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.bo.OrderBO;
import com.shoppingmall.point.dao.PointMapper;
import com.shoppingmall.point.model.Point;

@Service
public class PointBO {

	@Autowired
	private PointMapper pointMapeMapper;
	
	public List<Point> getPointListByUserId(int userId){
		return pointMapeMapper.selectPointListByUserId(userId);
	}
	
	public int getTotalPointByUserId(int userId) {
		return pointMapeMapper.selectTotalPointByUserId(userId);
	}
	
	@Transactional
	public void addPoint(int userId, int changePoint, String changeDetail, int totalPoint) {
		pointMapeMapper.insertPoint(userId, changePoint, changeDetail, totalPoint);
	}
}
