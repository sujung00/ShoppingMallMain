package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.order.dao.CancelRefundMapper;
import com.shoppingmall.order.model.CancelRefund;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.point.bo.PointBO;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;

@Service
public class CancelRefundBO {

	@Autowired
	private CancelRefundMapper cancelRefundMapper;
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private PointBO pointBO;
	
	@Autowired
	private ProductOptionBO productOptionBO;
	
	public CancelRefund getCancelRefundByOrderProductId(int orderProductId) {
		return cancelRefundMapper.selectCancelRefundByOrderProductId(orderProductId);
	}
	
	@Transactional
	public void addCancelRefund(int orderProductId, String reason, String state, int userId) {
		int orderId = orderProductBO.getOrderProductByOrderProductId(orderProductId).getOrderId();
		int totalPoint = pointBO.getTotalPointByUserId(userId);
		// 포인트 다시 적립
		Order order = orderBO.getOrderByOrderId(orderId);
		pointBO.addPoint(userId, (int)(order.getTotalPay()*0.01), "결제 취소", totalPoint+(int)(order.getTotalPay()*0.01));
		
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductListByOrderId(orderId);
		for(OrderProduct orderProduct : orderProductList) {
			orderProductBO.updateStateByOrderProductId("주문취소", orderProduct.getId());
			cancelRefundMapper.insertCancelRefund(orderProductId, reason, state);
			
			// 제품 옵션 재고 다시 올리기
			int count = orderProduct.getCount();
			ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(orderProduct.getOptionId());
				
			productOptionBO.updateStockByProductOptionId(productOption.getId(), productOption.getStock() + count);
		}
	}
	
	public int deleteCancelRefund(int cancelRefundId) {
		return cancelRefundMapper.deleteCancelRefund(cancelRefundId);
	}
	
	@Transactional
	public int updateReasonByCancelRefundId(int cancelRefundId, String reason) {
		return cancelRefundMapper.updateReasonByCancelRefundId(cancelRefundId, reason);
	}
}
