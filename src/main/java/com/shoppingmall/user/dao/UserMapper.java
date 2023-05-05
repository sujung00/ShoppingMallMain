package com.shoppingmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.user.model.User;

@Repository
public interface UserMapper {

	public List<User> selectUserListById(int id);
	
	public User selectUserByLoginId(String loginId);
	
	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email,
			@Param("phoneNumber") String phoneNumber);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User selectUserByEmail(String email);
	
	public String selectLoginIdByNameEmail(
			@Param("name") String name,
			@Param("email") String email);
}
