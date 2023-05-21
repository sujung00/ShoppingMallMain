package com.shoppingmall.order.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.CancelRefundMapper;
import com.shoppingmall.order.model.CancelRefund;

@Service
public class CancelRefundBO {

	@Autowired
	private CancelRefundMapper cancelRefundMapper;
	
	public CancelRefund getCancelRefundByOrderProductId(int orderProductId) {
		return cancelRefundMapper.selectCancelRefundByOrderProductId(orderProductId);
	}
}
