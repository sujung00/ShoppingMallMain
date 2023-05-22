package com.shoppingmall.order.model;

import java.util.List;

import com.shoppingmall.address.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailView {
	
	// order 1개
	private Order order;
	
	// order 1개에 대한 orderProduct
	private List<OrderAdminView> orderAdminViewList;
	
	private Address address;
	
}
