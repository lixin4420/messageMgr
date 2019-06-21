package com.msg.dao;

import java.util.List;

import com.msg.entity.User;

public interface UserDao {

	/**
	 * 用户登录
	 * @param loginUser
	 * @return
	 */
	User doLogin(User loginUser);

	/**
	 * 查询用户列表，除了当前用户
	 * @param username
	 * @return
	 */
	List<User> getUserList(String username);

	/**
	 * 执行新增
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 根据用户名统计用户数量，避免注册同名用户
	 * @param username
	 * @return
	 */
	int countUserByName(String username);

}
