package com.shoppingmall.review.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
	private int id;
	private int userId;
	private int orderProductId;
	private int orderId;
	private String subject;
	private String content;
	private String reviewImagePath;
	private Date createdAt;
	private Date updatedAt;
}
