package com.shoppingmall.address;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.address.bo.AddressBO;
import com.shoppingmall.address.model.Address;
import com.shoppingmall.order.bo.OrderBO;

@RequestMapping("/address")
@RestController
public class AddressRestController {
	
	@Autowired
	private AddressBO addressBO; 
	
	@Autowired
	private OrderBO orderBO;

	/**
	 * 배송지 생성 API
	 * @param name
	 * @param phoneNumber
	 * @param extraPhoneNumber
	 * @param postcode
	 * @param address
	 * @param datailedAddress
	 * @param defaultAddress
	 * @param session
	 * @return
	 */
	@PostMapping("/address_create")
	public Map<String, Object> addressCreate(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value="extraPhoneNumber", required=false) String extraPhoneNumber,
			@RequestParam("postcode") int postcode,
			@RequestParam("address") String address,
			@RequestParam("detailedAddress") String datailedAddress,
			@RequestParam("defaultAddress") boolean defaultAddress,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");

		// db insert
		// 만약 기본배송지로 되어있는게 있다면 update 후 insert***
		int rowCount = addressBO.addAddress(userId, name, phoneNumber, extraPhoneNumber, postcode, address, datailedAddress, defaultAddress);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "배송지 입력에 성공했습니다.");
		} else {
			result.put("code", 500);
			result.put("result", "배송지 입력에 실패했습니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 배송지 수정 API
	 * @param addressId
	 * @param name
	 * @param phoneNumber
	 * @param extraPhoneNumber
	 * @param postcode
	 * @param address
	 * @param datailedAddress
	 * @param defaultAddress
	 * @param session
	 * @return
	 */
	@PostMapping("/address_update")
	public Map<String, Object> addressUpdate(
			@RequestParam("addressId") int addressId,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value="extraPhoneNumber", required=false) String extraPhoneNumber,
			@RequestParam("postcode") int postcode,
			@RequestParam("address") String address,
			@RequestParam("detailedAddress") String datailedAddress,
			@RequestParam("defaultAddress") boolean defaultAddress,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");

		// db update
		// 만약 기본배송지로 되어있는게 있다면 update 후 insert***
		int rowCount = 0;
		if(defaultAddress == true) {
			Address ad = addressBO.getDefaultAddressByUserId(userId);
			if(ad == null) {
				// 바로 update
				rowCount = addressBO.updateAddressByAdderssId(addressId, name, phoneNumber, extraPhoneNumber, postcode, address, datailedAddress, defaultAddress);
			} else {
				// ad의 addressId => 기본배송지가 아닌걸로 update 후 배송지 수정
				addressBO.updateDefaultAddressByAddressId(ad.getId());
				
				// db update
				rowCount = addressBO.updateAddressByAdderssId(addressId, name, phoneNumber, extraPhoneNumber, postcode, address, datailedAddress, defaultAddress);
			}
		} else {
			rowCount = addressBO.updateAddressByAdderssId(addressId, name, phoneNumber, extraPhoneNumber, postcode, address, datailedAddress, defaultAddress);
		}
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "배송지 정보 수정에 성공했습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "배송지 정보 수정에 실패했습니다. 관리자에게 문의해주세요.");
		}
		
		
		return result;
	}
	
	/**
	 * 주문서의 배송지 정보 수정 API
	 * @param orderId
	 * @param name
	 * @param phoneNumber
	 * @param extraPhoneNumber
	 * @param postcode
	 * @param address
	 * @param datailedAddress
	 * @param defaultAddress
	 * @param session
	 * @return
	 */
	@PostMapping("/order_update_address_add")
	public Map<String, Object> orderUpdateAddressAdd(
			@RequestParam("orderId") int orderId,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value="extraPhoneNumber", required=false) String extraPhoneNumber,
			@RequestParam("postcode") int postcode,
			@RequestParam("address") String address,
			@RequestParam("detailedAddress") String datailedAddress,
			@RequestParam("defaultAddress") boolean defaultAddress,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		
		Address newAddress = new Address();
		newAddress.setUserId(userId);
		newAddress.setName(name);
		newAddress.setPhoneNumber(phoneNumber);
		newAddress.setExtraPhoneNumber(extraPhoneNumber);
		newAddress.setPostcode(postcode);
		newAddress.setAddress(address);
		newAddress.setDetailedAddress(datailedAddress);
		newAddress.setDefaultAddress(defaultAddress);
		
		// address insert
		// 만약 기본배송지로 되어있는게 있다면 update 후 insert***
		addressBO.addNewAddress(newAddress);
		
		int addressId = newAddress.getId();
		
		// order addressId update
		int rowCount = orderBO.updateAddressIdByOrderId(orderId, addressId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "배송지 정보가 변경되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "배송지 정보 변경에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/address_delete")
	public Map<String, Object> addressDelete(
			@RequestParam("addressId") int addressId) {
		// db delete
		int rowCount = addressBO.deleteAddressByAddressId(addressId);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "배송지가 삭제되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "배송지를 삭제하지 못했습니다.");
		}
		
		
		return result;
	}
}
