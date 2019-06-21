package com.msg.service.impl;


import com.msg.dao.UserDao;
import com.msg.dao.UserMapper;
import com.msg.dao.impl.UserDaoImpl;
import com.msg.entity.User;
import com.msg.service.UserService;
import com.msg.util.DataBaseUtil;
import com.msg.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Override
	public User doLogin(User loginUser) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		User LoginUser = sqlSession.getMapper(UserMapper.class).doLogin(loginUser);
		MyBatisUtil.closeSqlSession(sqlSession);
		return LoginUser;
	}
	@Override
	public List<User> getUserList(String username) {
		//创建连接对象
		Connection conn = DataBaseUtil.getConnection();
		//创建dao层对象
		UserDao userDao = new UserDaoImpl(conn);
		List<User> userList = userDao.getUserList(username);
		//关闭连接
		DataBaseUtil.closeAll(conn, null, null);
		return userList;
	}

	@Override
	public void addUser(User user) {
		//创建连接对象
		Connection conn = DataBaseUtil.getConnection();
		//创建dao层对象
		UserDao userDao = new UserDaoImpl(conn);
		userDao.addUser(user);
		//关闭连接
		DataBaseUtil.closeAll(conn, null, null);
		
	}

	@Override
	public int countUserByName(String username) {
		//创建连接对象
		Connection conn = DataBaseUtil.getConnection();
		//创建dao层对象
		UserDao userDao = new UserDaoImpl(conn);
		int x = userDao.countUserByName(username);
		//关闭连接
		DataBaseUtil.closeAll(conn, null, null);
		return x;
	}

}
