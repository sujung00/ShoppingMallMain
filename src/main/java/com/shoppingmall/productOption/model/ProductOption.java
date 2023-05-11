package com.shoppingmall.productOption.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOption {
	private int id;
	private int productId;
	private String color;
	private String size;
	private int stock;
	private Date createdAt;
	private Date updatedAt;
}
