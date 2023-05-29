package com.shoppingmall.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.address.bo.AddressBO;
import com.shoppingmall.address.model.Address;
import com.shoppingmall.order.bo.OrderBO;
import com.shoppingmall.order.bo.OrderProductBO;
import com.shoppingmall.order.model.IamportAPI;
import com.shoppingmall.order.model.Order;
import com.shoppingmall.order.model.OrderProduct;
import com.shoppingmall.product.bo.ProductBO;
import com.shoppingmall.product.model.Product;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RequestMapping("/order")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private UserBO userBO;

	@Autowired
	private AddressBO addressBO;
	
	@Autowired
	private ProductBO productBO;

	private IamportClient iamportClient;

	private IamportAPI iamportAPI;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("userId") int userId,
			@RequestParam("addressId") int addressId,
			@RequestParam("basketId") int basketId,
			@RequestParam("orderRequest") String orderRequest,
			@RequestParam(value="point", required=false) Integer usePoint,
			@RequestParam("payType") String payType,
			@RequestParam("totalPay") int totalPay) {
		// order db insert
		int orderId = orderBO.generateOrder(userId, addressId, basketId, orderRequest, payType, totalPay, usePoint);
		Order order = orderBO.getOrderByOrderId(orderId);
		
		User user = userBO.getUserByUserId(order.getUserId());
		
		Address address = addressBO.getAddressByAddressId(order.getAddressId());
		
		List<OrderProduct> orderProductList = orderProductBO.getOrderProductListByOrderId(orderId);
		Product product = productBO.getProductByProductId(orderProductList.get(0).getProductId());
		
		String orderProductName = null;
		if(orderProductList.size() == 1) {
			orderProductName = product.getName();
		} else {
			orderProductName = product.getName() + "외 " + String.valueOf(orderProductList.size()-1) + "상품";
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("orderId", orderId);
		result.put("user", user);
		result.put("address", address);
		result.put("order", order);
		result.put("orderProductName", orderProductName);
		result.put("result", "주문이 완료되었습니다.");
		
		return result;
	}
	
	@PostMapping("/order_product_update")
	public Map<String, Object> orderProductUpdate(
			@RequestParam("orderProductId") int orderProductId,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("originColor") String originColor,
			@RequestParam("originSize") String originSize,
			@RequestParam("originCount") int originCount){
		// orderProduct update
		orderProductBO.updateOptionCountByOrderProductId(orderProductId, color, size, originColor, originSize, originCount);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "옵션을 수정하였습니다.");
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("orderId") int orderId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		
		// order delete
		int rowCount = orderBO.deleteOrderByOrderIdUserId(orderId, userId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "구매를 취소하였습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "구매를 취소하지 못했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/verify/{imp_uid}")
	public IamportResponse<Payment> verifyByImpUid(@PathVariable(value="imp_uid") String imp_uid) throws IamportResponseException, IOException{
		iamportAPI = new IamportAPI();
		
		String IAMPORT_API = iamportAPI.getApi();
		String IAMPORT_API_SECRET = iamportAPI.getApiSecret();
		this.iamportClient = new IamportClient(IAMPORT_API, IAMPORT_API_SECRET);
		
		return iamportClient.paymentByImpUid(imp_uid);
	}
	
	@PostMapping("/succeed")
	public Map<String, Object> updateStatus(
			@RequestParam("imp_uid") String imp_uid,
			@RequestParam("merchant_uid") String merchant_uid){
		int orderId = Integer.parseInt(merchant_uid);
		
		orderProductBO.updateStateByOrderId(orderId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "결제 및 검증이 완료되었습니다.");
		
		return result;
	}
	
}
