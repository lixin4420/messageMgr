package com.msg.service.impl;

import com.msg.dao.UserMapper;
import com.msg.entity.User;
import com.msg.service.UserService;
import com.msg.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

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
		/*//创建连接对象
		Connection conn = DataBaseUtil.getConnection();
		//创建dao层对象
		UserDao userDao = new UserDaoImpl(conn);
		List<User> userList = userDao.getUserList(username);
		//关闭连接
		DataBaseUtil.closeAll(conn, null, null);
		return userList;*/
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		List<User> list = sqlSession.getMapper(UserMapper.class).getUserList(username);
		MyBatisUtil.closeSqlSession(sqlSession);
		return list;
	}

	@Override
	public int addUser(User user) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		int num = sqlSession.getMapper(UserMapper.class).addUser(user);
		sqlSession.commit();
		return num;
	}

	@Override
	public int countUserByName(String username) {
		SqlSession sqlSession = MyBatisUtil.createSqlSession();
		int num = sqlSession.getMapper(UserMapper.class).countUserByName(username);
		MyBatisUtil.closeSqlSession(sqlSession);
		return num;
	}
}
