package com.shoppingmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.user.model.User;

@Repository
public interface UserMapper {

	public List<User> selectUserListById(int id);
	
	public User selectUserByLoginId(String loginId);
	
	public int insertUser(User user);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User selectUserByEmail(String email);
	
	public String selectLoginIdByNameEmail(
			@Param("name") String name,
			@Param("email") String email);
	
	public User selectUserByLoginIdNameEmail(
			@Param("loginId") String loginId,
			@Param("name") String name,
			@Param("email") String email);
	
	public void updateUserPassword(
			@Param("userId") int userId,
			@Param("password") String password);
	
	public int updateLoginIdByUserId(
			@Param("loginId") String loginId,
			@Param("userId") int userId);
	
	public int updateNamerByUserId(
			@Param("name") String name,
			@Param("userId") int userId);
	
	public int updateEmailByUserId(
			@Param("email") String email,
			@Param("userId") int userId);
	
	public int updatePhoneNumber(
			@Param("phoneNumber") String phoneNumber,
			@Param("userId") int userId);
	
	public int updatePWByUserId(
			@Param("password") String password,
			@Param("userId") int userId);
	
	public User selectUserByUserId(int userId);
}
