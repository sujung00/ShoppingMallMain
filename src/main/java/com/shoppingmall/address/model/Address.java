package com.shoppingmall.address.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	private int id;
	private int userId;
	private String name;
	private String phoneNumber;
	private String extraPhoneNumber;
	private int postcode;
	private String address;
	private String detailedAddress;
	private boolean defaultAddress;
	private Date createdAt;
	private Date updatedAt;
	
	public boolean getDefaulAddress() {
		return this.defaultAddress;
	}
}
