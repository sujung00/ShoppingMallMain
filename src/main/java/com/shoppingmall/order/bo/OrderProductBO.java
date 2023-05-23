package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.OrderProductMapper;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;

@Service
public class OrderProductBO {

	@Autowired
	private OrderProductMapper orderProductMapper;
	
	@Autowired
	private ProductOptionBO productOptionBO;
	
	public void addOrderProduct(int orderId, int productId, int optionId, int count, String state) {
		orderProductMapper.insertOrderProduct(orderId, productId, optionId, count, state);
	}
	
	public List<OrderProduct> getOrderProductListByOrderId(int orderId){
		return orderProductMapper.selectOrderProductListByOrderId(orderId);
	}
	
	public OrderProduct getOrderProductByOrderProductId(int orderProductId) {
		return orderProductMapper.selectOrderProductByOrderProductId(orderProductId);
	}
	
	public List<OrderProduct> getOrderProductList(){
		return orderProductMapper.selectOrderProductList();
	}
	
	public int updateStateByOrderProductId(String state, int orderProductId) {
		return orderProductMapper.updateStateByOrderProductId(state, orderProductId);
	}
	
	public void updateOptionCountByOrderProductId(int orderProductId, String color, String size,
			String originColor, String originSize, int originCount) {
		// orderProduct 의 optionId 변경
		OrderProduct orderProduct = getOrderProductByOrderProductId(orderProductId);
		// 변경하려는 productOption
		ProductOption productOption = productOptionBO.getProductOptionByProductId(orderProduct.getProductId(), color, size);
		updateOptionIdByOrderProductId(orderProductId, productOption.getId());
		
		// productOption의 stock 변경
		productOptionBO.updateStockByProductOptionId(productOption.getId(), productOption.getStock()-originCount);
		
		ProductOption originProductOption = productOptionBO.getProductOptionByProductId(orderProduct.getProductId(), originColor, originSize);
		productOptionBO.updateStockByProductOptionId(originProductOption.getId(), originProductOption.getStock()+originCount);
	}
	
	public void updateOptionIdByOrderProductId(int orderProductId, int optionId) {
		orderProductMapper.updateOptionIdByOrderProductId(orderProductId, optionId);
	}
}
