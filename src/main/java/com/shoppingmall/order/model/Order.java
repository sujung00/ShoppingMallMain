package com.shoppingmall.order.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private int id;
	private int userId;
	private int addressId;
	private String orderRequest;
	private String payType;
	private int totalPay;
	private Date createdAt;
	private Date updatedAt;
}
