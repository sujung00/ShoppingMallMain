package com.shoppingmall.point.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingmall.point.model.Point;

@Repository
public interface PointMapper {

	public List<Point> selectPointListByUserId(int userId);
	
	public int selectTotalPointByUserId(int userId);
}
