package com.shoppingmall.address.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.address.model.Address;

@Repository
public interface AddressMapper {

	public int insertAddress(
			@Param("userId") int userId,
			@Param("name") String name,
			@Param("phoneNumber") String phoneNumber,
			@Param("extraPhoneNumber") String extraPhoneNumber,
			@Param("postcode") int postcode,
			@Param("address") String address,
			@Param("detailedAddress") String detailedAddress,
			@Param("defaultAddress") boolean defaultAddress);
	
	public List<Address> selectAddressListByUserId(int userId);
	
	public Address selectAddressByAddressId(int addressId);
	
	public Address selectDefaultAddressByUserId(int userId);
	
	public void updateDefaultAddressByAddressId(int addressId);
	
	public int updateAddressByAdderssId(
			@Param("addressId") int addressId,
			@Param("name") String name,
			@Param("phoneNumber") String phoneNumber,
			@Param("extraPhoneNumber") String extraPhoneNumber,
			@Param("postcode") int postcode,
			@Param("address") String address,
			@Param("detailedAddress") String detailedAddress,
			@Param("defaultAddress") boolean defaultAddress);
	
	public void insertNewAddress(Address address);
	
}
