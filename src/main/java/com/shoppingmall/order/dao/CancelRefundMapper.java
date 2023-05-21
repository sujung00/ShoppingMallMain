package com.shoppingmall.order.dao;

import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.CancelRefund;

@Repository
public interface CancelRefundMapper {

	public CancelRefund selectCancelRefundByOrderProductId(int orderProductId);
}
