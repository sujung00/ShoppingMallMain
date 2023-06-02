package com.shoppingmall.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.user.dao.UserMapper;
import com.shoppingmall.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	@Transactional
	public int addUser(User user) {
		return userMapper.insertUser(user);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}
	
	public List<User> getUserListByEmail(String email) {
		return userMapper.selectUserListByEmail(email);
	}
	
	public String getLoginIdByNameEmail(String name, String email) {
		return userMapper.selectLoginIdByNameEmail(name, email);
	}
	
	public User getUserByLoginIdNameEmail(String loginId, String name, String email) {
		return userMapper.selectUserByLoginIdNameEmail(loginId, name, email);
	}
	
	@Transactional
	public int updateNamerByUserId(String name, int userId) {
		return userMapper.updateNamerByUserId(name, userId);
	}
	
	@Transactional
	public int updatePhoneNumber(String phoneNumber, int userId) {
		return userMapper.updatePhoneNumber(phoneNumber, userId);
	}
	
	@Transactional
	public int updatePWByUserId(String password, int userId) {
		return userMapper.updatePWByUserId(password, userId);
	}
	
	public User getUserByUserId(int userId) {
		return userMapper.selectUserByUserId(userId);
	}
	
	public User getUserByLoginIdEmail(String loginId, String email) {
		return userMapper.selectUserByLoginIdEmail(loginId, email);
	}
}
