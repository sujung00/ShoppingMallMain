package com.shoppingmall.product.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImage {
	private int id;
	private int productId;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
}
