package com.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 基础数据访问层对象
 * Base  Dao
 * 定义了：增删改方法（查询方法需要在实现类中自己写）
 * @author WebDev
 *
 */
public class BaseDao {

	//数据库连接对象
	private Connection conn = null;
	
	/**
	 * 构造方法，要求必须提供连接对象
	 * @param connection
	 */
	public BaseDao(Connection connection) {
		this.conn = connection;
	}
	
	/**
	 * 增删改方法
	 * @param sql 要执行的SQL
	 * @param params SQL要放入的参数
	 * @return
	 */
	public int executeUpdate(String sql, Object[] params) {
		PreparedStatement pstmt = null;
		int num = 0;//影响数据库数据行数
		try {
			//声明对象实例化
			pstmt = conn.prepareStatement(sql);
			//循环设置参数
			//先判断是否有参数
			if(params!=null) {
				for(int i=0; i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			//通过声明对象执行SQL语句 
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return num;
	}	
	
	/**
	 * 执行查询，得到结果集
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql, Object[] params) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//循环设置参数
			//先判断是否有参数
			if(params!=null) {
				for(int i=0; i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			//通过声明对象执行SQL语句 
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}