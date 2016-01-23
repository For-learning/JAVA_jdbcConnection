package com.cll.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtial {
	// 连接字符串
	private static String url = "jdbc:mysql://localhost:3306/world";
	private static String user = "root";
	private static String password = "root";

	/**
	 * url格式： JDBC:子协议:子名称//主机名:端口/数据库名?属性名=属性值&...
	 * 当然，用户名和密码也可以以属性名=属性值的方法写在url协议中
	 * 其他常用参数：useUnicode=true&characterEncoding=GBK
	 * */

	// 由于不让实例化这个类，因此将其构造方法私有化
	private JDBCUtial() {
	}

	// 注册驱动，只需要做一次，只在类加载的时候做一次就可以了。
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * 第二种注册驱动的方法 
			 * DriverManager.registerDriver(com.mysql.jdbc.Driver);
			 * 但是这种方法会造成DriverManager中产生两个一样的驱动，并会对具体的驱动类型产生依赖。 
			 * 第三种注册驱动的方法
			 * System.setProperty("jdbc.drivers","driver1:driver2");
			 * 这种方法虽然不会对具体驱动类型产生依赖，但是注册不太方便，很少使用。
			 * */
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}

	// 返回连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	// 释放资源
	public static void free(ResultSet rs, Statement st, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
	}
}
