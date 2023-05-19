package com.shoppingmall.review.model;

import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.model.ProductOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrittenReviewView {
	private Review review;
	
	private OrderProduct orderProduct;
	
	private Product product;
	
	private ProductOption productOption;
}
