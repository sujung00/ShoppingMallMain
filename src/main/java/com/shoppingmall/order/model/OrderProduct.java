package com.shoppingmall.order.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProduct {
	private int id;
	private int orderId;
	private int productId;
	private int optionId;
	private int count;
	private String state;
	private Date createdAt;
	private Date updatedAt;
}
