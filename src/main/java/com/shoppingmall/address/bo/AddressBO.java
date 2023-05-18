package com.shoppingmall.address.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.address.dao.AddressMapper;
import com.shoppingmall.address.model.Address;

@Service
public class AddressBO {
	
	@Autowired
	private AddressMapper addressMapper;

	public int addAddress(int userId, String name, String phoneNumber, String extraPhoneNumber,
			int postcode, String address, String detailedAddress, boolean defaultAddress) {
		if(defaultAddress) {
			Address defaultAd = getDefaultAddressByUserId(userId);
			if(defaultAd != null) {
				updateDefaultAddressByAddressId(defaultAd.getId());
			}
		}
		
		return addressMapper.insertAddress(userId, name, phoneNumber, extraPhoneNumber, postcode, address, detailedAddress, defaultAddress);
	}
	
	public List<Address> getAddressListByUserId(int userId){
		return addressMapper.selectAddressListByUserId(userId);
	}
	
	public List<Address> getAddressByAddressId(int addressId) {
		return addressMapper.selectAddressByAddressId(addressId);
	}
	
	public Address getDefaultAddressByUserId(int userId) {
		return addressMapper.selectDefaultAddressByUserId(userId);
	}
	
	public void updateDefaultAddressByAddressId(int addressId) {
		addressMapper.updateDefaultAddressByAddressId(addressId);
	}
	
	public int updateAddressByAdderssId(int addressId, String name, String phoneNumber, 
			String extraPhoneNumber, int postcode, String address, String detailedAddress, boolean defaultAddress) {
		return addressMapper.updateAddressByAdderssId(addressId, name, phoneNumber, extraPhoneNumber, postcode, address, detailedAddress, defaultAddress);
	}

}
