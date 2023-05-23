package com.shoppingmall.inquiry.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inquiry {
	private int id;
	private int userId;
	private int orderId;
	private String subject;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
