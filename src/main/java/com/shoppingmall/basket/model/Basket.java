package com.shoppingmall.basket.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Basket {
	private int id;
	private int userId;
	private Date createdAt;
	private Date updatedAt;
}
