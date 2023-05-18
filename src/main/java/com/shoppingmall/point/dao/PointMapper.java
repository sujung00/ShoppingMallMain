package com.shoppingmall.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.point.model.Point;

@Repository
public interface PointMapper {

	public List<Point> selectPointListByUserId(int userId);
	
	public int selectTotalPointByUserId(int userId);
	
	public void insertPoint(
			@Param("userId") int userId,
			@Param("changePoint") int changePoint,
			@Param("changeDetail") String changeDetail,
			@Param("totalPoint") int totalPoint);
}
