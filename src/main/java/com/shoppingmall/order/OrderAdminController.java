package com.shoppingmall.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.order.bo.OrderAdminBO;
import com.shoppingmall.order.model.OrderAdminView;

@RequestMapping("/order_admin")
@Controller
public class OrderAdminController {

	@Autowired
	private OrderAdminBO orderAdminBO;
	
	@RequestMapping("/order_list_view")
	public String orderListView(Model model) {
		List<OrderAdminView> orderAdminViewList = orderAdminBO.generateOrderAdminView();
		
		model.addAttribute("orderAdminViewList", orderAdminViewList);
		model.addAttribute("view", "/admin/order/orderListView");
		return "template/layout";
	}
}
