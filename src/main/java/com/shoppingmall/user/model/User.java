package com.shoppingmall.user.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private int id;
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private boolean kakaoUser;
	private Date createdAt;
	private Date updatedAt;
	
	public boolean getKakaoUser() {
		return this.kakaoUser;
	}
}