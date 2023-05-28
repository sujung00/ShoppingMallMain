package com.shoppingmall.kakaopay;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/kakaopay")
@RestController
public class KakaoPayRestController {

	@PostMapping("/pay")
	public void kakaoPay(
			@RequestParam("orderId") int orderId,
			@RequestParam("itemName") String itemName,
			){
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection serverConnect = (HttpURLConnection) url.openConnection();
			serverConnect.setRequestMethod("POST");
			serverConnect.setRequestProperty("Authorization", "KakaoAK e656a124d7555962e110089e966397eb")
			serverConnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			serverConnect.setDoOutput(true);
			
			// 카카오가 요구한 결제요청request값을 담아줍니다. 
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			parameters.add("cid", "TC0ONETIME");
			parameters.add("partner_order_id", String.valueOf(orderId));
			parameters.add("partner_user_id", "inflearn");
			parameters.add("item_name", itemName);
			parameters.add("quantity", String.valueOf(carts.size()));
			parameters.add("total_amount", String.valueOf(totalAmount));
			parameters.add("tax_free_amount", "0");
			parameters.add("approval_url", "http://localhost/order/pay/completed"); // 결제승인시 넘어갈 url
			parameters.add("cancel_url", "http://localhost/order/pay/cancel"); // 결제취소시 넘어갈 url
			parameters.add("fail_url", "http://localhost/order/pay/fail"); // 결제 실패시 넘어갈 url
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
