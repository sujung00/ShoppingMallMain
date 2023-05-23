package com.shoppingmall.inquiry.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
	private int id;
	private int inquiryId;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
