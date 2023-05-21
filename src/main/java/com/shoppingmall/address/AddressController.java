package com.shoppingmall.address;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.address.bo.AddressBO;
import com.shoppingmall.address.model.Address;

@RequestMapping("/address")
@Controller
public class AddressController {

	@Autowired
	private AddressBO addressBO;
	
	@RequestMapping("/address_view")
	public String addressView(Model model, HttpSession session) {
		// 기본 배송지 db select
		int userId = (int)session.getAttribute("userId");
		Address defaultAddress = addressBO.getDefaultAddressByUserId(userId);
		
		// 배송지 목록 db select
		List<Address> addressList = addressBO.getAddressListByUserId(userId);

		model.addAttribute("defualAddress", defaultAddress);
		model.addAttribute("addressList", addressList);
		model.addAttribute("view", "/address/addressView");
		return "template/layout";
	}
	
	@RequestMapping("/address_update_view")
	public String addressUpdateView(
			@RequestParam("addressId") int addressId,
			Model model) {
		// db select
		Address address = addressBO.getAddressByAddressId(addressId);
		
		model.addAttribute("address", address);
		model.addAttribute("view", "/address/addressUpdateView");
		return "template/layout";
	}
}
