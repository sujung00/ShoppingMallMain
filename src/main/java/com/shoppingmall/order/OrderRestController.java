package com.shoppingmall.order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
	
	/**
	 * 주문서 생성 API
	 * @param userId
	 * @param addressId
	 * @param basketId
	 * @param orderRequest
	 * @param usePoint
	 * @param payType
	 * @param totalPay
	 * @return
	 */
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
			// ex) 미니멀 크롭 브이넥 가디건 외 2개 상품
			orderProductName = product.getName() + " 외 " + String.valueOf(orderProductList.size()-1) + "개 상품";
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
	
	/**
	 * 주문서 상품 옵션 수정 API
	 * @param orderProductId
	 * @param color
	 * @param size
	 * @param originColor
	 * @param originSize
	 * @param originCount
	 * @return
	 */
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
	
	/**
	 * 주문 취소 API
	 * @param orderId
	 * @param session
	 * @return
	 */
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("orderId") int orderId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		
		// order delete
		Order order = orderBO.getOrderByOrderId(orderId);
		int totalPay = order.getTotalPay();
		orderBO.deleteOrderByOrderIdUserId(orderId, userId, totalPay);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "구매를 취소하였습니다.");
		result.put("orderId", orderId);
		result.put("cancelPrice", order.getTotalPay());
		
		return result;
	}
	
	/**
	 * 포트원 - 주문 결제 검증 API
	 * @param imp_uid
	 * @return
	 * @throws IamportResponseException
	 * @throws IOException
	 */
	@PostMapping("/verify/{imp_uid}")
	public IamportResponse<Payment> verifyByImpUid(@PathVariable(value="imp_uid") String imp_uid) throws IamportResponseException, IOException{
		iamportAPI = new IamportAPI();
		
		String IAMPORT_API = iamportAPI.getApi();
		String IAMPORT_API_SECRET = iamportAPI.getApiSecret();
		this.iamportClient = new IamportClient(IAMPORT_API, IAMPORT_API_SECRET);
		
		return iamportClient.paymentByImpUid(imp_uid);
	}
	
	/**
	 * 포트원 - 주문 결제 완료 처리 API
	 * @param imp_uid
	 * @param merchant_uid
	 * @return
	 */
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
	
	/**
	 * 포트원 - 주문/결제 취소 API
	 * @param merchantUid
	 * @param amount
	 * @param reason
	 * @throws IOException
	 */
	@PostMapping("/cancel")
	public void orderCancel(
			@RequestParam("merchant_uid") int merchantUid,
			@RequestParam("cancel_request_amount") int amount,
			@RequestParam("reason") String reason) throws IOException {
		// access token 발급
		String access_token = getToken();
		
		// 포트원 REST API로 결제 환불 요청
		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");
 
		conn = (HttpsURLConnection) url.openConnection();
 
		conn.setRequestMethod("POST");
 
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", access_token);
 
		conn.setDoOutput(true);
		
		JsonObject json = new JsonObject();
 
		json.addProperty("reason", reason);
		json.addProperty("merchant_uid", merchantUid);
		json.addProperty("checksum", amount);
 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		 
		bw.write(json.toString());
		bw.flush();
		bw.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		
		br.close();
		conn.disconnect();
	}
	
	/**
	 * 포트원 - 액세스 토큰 발급 API
	 * @return
	 * @throws IOException
	 */
	public String getToken() throws IOException {

		HttpsURLConnection conn = null;

		URL url = new URL("https://api.iamport.kr/users/getToken");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		JsonObject json = new JsonObject();

		iamportAPI = new IamportAPI();
		
		String IAMPORT_API = iamportAPI.getApi();
		String IAMPORT_API_SECRET = iamportAPI.getApiSecret();
		
		json.addProperty("imp_key", IAMPORT_API);
		json.addProperty("imp_secret", IAMPORT_API_SECRET);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		Gson gson = new Gson();

		String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
		
		String token = gson.fromJson(response, Map.class).get("access_token").toString();

		br.close();
		conn.disconnect();

		return token;
	}
}
