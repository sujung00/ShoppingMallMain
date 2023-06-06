package com.shoppingmall.order.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.address.bo.AddressBO;
import com.shoppingmall.address.model.Address;
import com.shoppingmall.order.model.CancelRefund;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderAdminView;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.productOption.bo.ProductOptionBO;
import com.shoppingmall.productOption.model.ProductOption;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class OrderAdminBO {

	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private ProductOptionBO productOptionBO;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private AddressBO addressBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CancelRefundBO cancelRefundBO;
	
	@Transactional
	public List<OrderAdminView> generateOrderAdminView(){
		List<OrderAdminView> adminViewList = new ArrayList<>();
		
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductList();
		for(OrderProduct orderProduct : orderProductList) {
			OrderAdminView orderAdminView = new OrderAdminView();
			
			orderAdminView.setOrderProduct(orderProduct);
			
			Order order = orderBO.getOrderByOrderId(orderProduct.getOrderId());
			orderAdminView.setOrder(order);
			
			ProductOption productOption = productOptionBO.getProductOptionByProductOptionId(orderProduct.getOptionId());
			orderAdminView.setProductOption(productOption);
			
			Product product = productBO.getProductByProductId(orderProduct.getProductId());
			orderAdminView.setProduct(product);
			
			Address address = addressBO.getAddressByAddressId(order.getAddressId());
			orderAdminView.setAddress(address);
			
			User user = userBO.getUserByUserId(order.getUserId());
			orderAdminView.setUser(user);
			
			CancelRefund cancelRefund = cancelRefundBO.getCancelRefundByOrderProductId(orderProduct.getId());
			orderAdminView.setCancelRefund(cancelRefund);
			
			adminViewList.add(orderAdminView);
		}
		
		return adminViewList;
	}
}
