package com.shoppingmall.address.bo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.address.model.Address;

@SpringBootTest
class AddressBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AddressBO addressBO;
	
	@Transactional
	@Test
	void 주소추가() {
		logger.info("#### 주소추가");
		// 추가 연락처 X & 기본 배송지로 설정
		int rowCount1 = addressBO.addAddress(1, "김수정", "01012345678", null, 123456, "서울시 성북구", "101-101", true);
		assertNotNull(rowCount1);
		// 추가 연락처 O & 기본 배송지로 설정 X
		int rowCount2 = addressBO.addAddress(1, "김수정", "01012345678", "01011112222", 123456, "서울시 성북구", "101-101", false);
		assertNotNull(rowCount2);
	}
	
	@Transactional
	@Test
	void 새로운주소추가() {
		logger.info("#### 새로운주소추가");
		Address address = new Address();
		address.setUserId(1);
		address.setName("김수정");
		address.setPhoneNumber("01012345678");
		address.setExtraPhoneNumber("01011112222");
		address.setPostcode(123456);
		address.setAddress("서울시 성북구");
		address.setDetailedAddress("101-101");
		address.setDefaultAddress(true);
		addressBO.addNewAddress(address);
	}
	
	@Test
	void 유저주소리스트가져오기() {
		logger.info("#### 유저주소리스트가져오기");
		List<Address> addressList = addressBO.getAddressListByUserId(13);
		assertNotNull(addressList);
	}
	
	@Test
	void 주소가져오기() {
		logger.info("#### 주소가져오기");
		Address address = addressBO.getAddressByAddressId(2);
		assertNotNull(address);
	}
	
	@Test
	void 기본배송지가져오기() {
		logger.info("#### 기본배송지가져오기");
		Address address = addressBO.getDefaultAddressByUserId(13);
		assertNotNull(address);
	}
	
	
	@Transactional
	@Test
	void 기본배송지로변경() {
		logger.info("#### 기본배송지로변경");
		addressBO.updateDefaultAddressByAddressId(2);
	}
	
	@Transactional
	@Test
	void 주소수정하기() {
		logger.info("#### 주소수정하기");
		int rowCount = addressBO.updateAddressByAdderssId(2, "김수정", "01033334444", "01022223333", 123456, "서울시 성북구", "101-101", true);
		assertNotNull(rowCount);
	}
}
