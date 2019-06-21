package com.msg.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.msg.dao.BaseDao;

/**
 * 数据库工具类DataBase Util
 * 读取配置
 * 加载驱动，获取连接对象
 * 关闭所有资源
 * @author WebDev
 *
 */
public class DataBaseUtil {
	//数据库驱动名
	private static String driver;
	//数据库连接地址
	private static String url;
	//数据库连接用户名
	private static String user;
	//数据库用户对应的密码
	private static String password;
	//数据库连接对象
	private static Connection conn = null;
	
	//定义静态代码块，调用初始化配置方法
	static {
		init();
	}
	/**
	 * 初始化配置
	 * 驱动driver、连接地址url、数据库用户名user、数据库密码password
	 * 目标：在BaseDao加载时，就读取配置文件,把配置文件database.properties中的内容读取到Java代码中
	 * init初始化
	 */
	public static void init() {
		Properties properties = new Properties();
		//设置配置文件
		String configFile = "database.properties"; 
		//加载配置文件到输入流中
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		
		//从输入流中读取到配置的属性
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//根据属性名读取属性值
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
	}
	
	
	/**
	 * 获取连接对象
	 * @return
	 */
	public static Connection getConnection() {
		try {
			//通过类加载器，加载驱动
			Class.forName(driver);
			//实例化
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭所有资源
	 * @param conn 连接对象
	 * @param stmt Statement对象
	 * @param rs 结果集对象
	 */
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) {//如果结果集不为空，关闭结果集
				rs.close();
			}
			if(stmt!=null) {//如果Statement对象不为空，关闭Statement
				stmt.close();
			}
			if(conn!=null) {//如果连接对象不为空，关闭连接对象
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
