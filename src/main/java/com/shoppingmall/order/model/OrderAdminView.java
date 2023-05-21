package com.shoppingmall.order.model;

import com.shoppingmall.address.model.Address;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderAdminView {

	// orderProduct
	private OrderProduct orderProduct;

	private Order order;
	
	private ProductOption productOption;
	
	private Product product;
	
	private Address address;
	
	private User user;
	
	private CancelRefund cancelRefund;
}
