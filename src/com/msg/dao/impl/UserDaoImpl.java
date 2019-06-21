package com.msg.dao.impl;

import com.msg.dao.BaseDao;
import com.msg.dao.UserDao;
import com.msg.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

	public UserDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public User doLogin(User loginUser) {
		User user = null;
		String sql = "SELECT id, username, PASSWORD, email FROM USER WHERE username = ? AND PASSWORD = ?";
		Object[] params = {loginUser.getUsername(), loginUser.getPassword()};
		ResultSet rs = this.executeQuery(sql, params);
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("email");
				//易错点！！！
				user = new User();
				user.setId(id);
				user.setUsername(username);
				//user.setPassword(password);
				user.setEmail(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> getUserList(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT id, username FROM USER WHERE username != ?";
		Object[] params = {username};
		ResultSet rs = this.executeQuery(sql, params);
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("username");
				User user = new User();
				user.setId(id);
				user.setUsername(name);
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO USER (username,PASSWORD, email) VALUES (?,?,?);";
		Object[] params = {user.getUsername(), user.getPassword(), user.getEmail()};
		this.executeUpdate(sql, params);
	}

	@Override
	public int countUserByName(String username) {
		String sql = "SELECT COUNT(1) FROM USER WHERE username = ? ";
		Object[] params = {username};
		int x = 0 ;
		ResultSet rs = this.executeQuery(sql, params);
		try {
			while(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

}
