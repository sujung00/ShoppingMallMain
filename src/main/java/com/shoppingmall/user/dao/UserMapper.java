package com.shoppingmall.user.dao;

import java.util.List;

import com.shoppingmall.user.model.User;

public interface UserMapper {

	public List<User> selectUserListById(int id);
}
