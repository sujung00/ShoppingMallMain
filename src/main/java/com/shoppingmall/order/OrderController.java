package com.shoppingmall.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.order.bo.OrderServiceBO;
import com.shoppingmall.order.model.OrderDetailView;
import com.shoppingmall.order.model.OrderView;

@RequestMapping("/order")
@Controller
public class OrderController {
	
	@Autowired
	private OrderServiceBO orderServiceBO;

	/**
	 * 주문서 화면
	 * @param basketId
	 * @param model
	 * @return
	 */
	@PostMapping("/order_view")
	public String orderView(
			@RequestParam("basketId") int basketId,
			Model model) {
		OrderView orderView = orderServiceBO.generateOrderView(basketId);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("view", "/order/orderView");
		return "template/layout";
	}
	
	/**
	 * 주문/배송 화면
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/order_deliver_view")
	public String orderDeliverView(Model model, HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		List<OrderDetailView> orderDetailViewList = orderServiceBO.generateOrderDetailListView(userId);
		
		model.addAttribute("orderDetailViewList", orderDetailViewList);
		model.addAttribute("view", "/order/orderDeliverView");
		return "template/layout";
	}
	
	@PostMapping("/order_detail_view")
	public String orderDetailView(
			@RequestParam("orderId") int orderId,
			Model model) {
		OrderDetailView orderDetailView = orderServiceBO.generateOrderDetailView(orderId);
		
		model.addAttribute("orderDetail", orderDetailView);
		model.addAttribute("view", "/order/orderDetailView");
		return "template/layout";
	}
}
