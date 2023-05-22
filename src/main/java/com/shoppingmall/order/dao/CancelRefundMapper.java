package com.shoppingmall.order.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.CancelRefund;

@Repository
public interface CancelRefundMapper {

	public CancelRefund selectCancelRefundByOrderProductId(int orderProductId);
	
	public int insertCancelRefund(
			@Param("orderProductId") int orderProductId,
			@Param("reason") String reason,
			@Param("state") String state);
	
	public int deleteCancelRefund(int cancelRefundId);
	
	public int updateReasonByCancelRefundId(
			@Param("cancelRefundId") int cancelRefundId,
			@Param("reason") String reason);
}
