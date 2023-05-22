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
	
	public int addCancelRefund(int orderProductId, String reason, String state) {
		return cancelRefundMapper.insertCancelRefund(orderProductId, reason, state);
	}
	
	public int deleteCancelRefund(int cancelRefundId) {
		return cancelRefundMapper.deleteCancelRefund(cancelRefundId);
	}
	
	public int updateReasonByCancelRefundId(int cancelRefundId, String reason) {
		return cancelRefundMapper.updateReasonByCancelRefundId(cancelRefundId, reason);
	}
}
