package com.msg.service;

import java.util.List;

import com.msg.entity.User;

public interface UserService {

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
	 * 执行新增用户
	 * @param user
	 */
	int addUser(User user);

	/**
	 * 根据用户名统计用户，避免注册同名用户
	 * @param username
	 * @return
	 */
	int countUserByName(String username);

}
