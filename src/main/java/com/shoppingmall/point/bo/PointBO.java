package com.shoppingmall.point.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.point.dao.PointMapper;
import com.shoppingmall.point.model.Point;

@Service
public class PointBO {

	@Autowired
	private PointMapper pointMapeMapper;
	
	public List<Point> getPointListByUserId(int userId){
		return pointMapeMapper.selectPointListByUserId(userId);
	}
}
