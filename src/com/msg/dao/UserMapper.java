package com.msg.dao;

import com.msg.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 用户登录
     * @param
     * @return
     */
    User doLogin(User user);

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
    int addUser(User user);

    /**
     * 根据用户名统计用户数量，避免注册同名用户
     * @param username
     * @return
     */
    int countUserByName(String username);
}
