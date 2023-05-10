package com.shoppingmall.product.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private int id;
	private String name;
	private String information;
	private int price;
	private String mainImagePath;
	private String detailedInfo;
	private String gender;
	private Date createdAt;
	private Date updatedAt;
}
