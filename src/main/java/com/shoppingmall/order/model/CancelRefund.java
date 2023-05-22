package com.shoppingmall.order.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelRefund {
	private int id;
	private int orderProductId;
	private String reason;
	private String state;
	private Date createdAt;
	private Date updatedAt;
}
