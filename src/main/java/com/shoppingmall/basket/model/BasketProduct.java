package com.shoppingmall.basket.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketProduct {
	private int id;
	private int userId;
	private int basketId;
	private int productId;
	private int optionId;
	private int count;
	private Date createdAt;
	private Date updatedAt;
}
