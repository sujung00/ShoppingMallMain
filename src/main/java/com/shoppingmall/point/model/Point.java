package com.shoppingmall.point.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
	private int id;
	private int userId;
	private int changePoint;
	private String changeDetail;
	private int totalPoint;
	private Date createdAt;
	private Date updatedAt;
}
