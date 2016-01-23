package com.cll.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtial {
	// �����ַ���
	private static String url = "jdbc:mysql://localhost:3306/world";
	private static String user = "root";
	private static String password = "root";

	/**
	 * url��ʽ�� JDBC:��Э��:������//������:�˿�/���ݿ���?������=����ֵ&...
	 * ��Ȼ���û���������Ҳ������������=����ֵ�ķ���д��urlЭ����
	 * �������ò�����useUnicode=true&characterEncoding=GBK
	 * */

	// ���ڲ���ʵ��������࣬��˽��乹�췽��˽�л�
	private JDBCUtial() {
	}

	// ע��������ֻ��Ҫ��һ�Σ�ֻ������ص�ʱ����һ�ξͿ����ˡ�
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * �ڶ���ע�������ķ��� 
			 * DriverManager.registerDriver(com.mysql.jdbc.Driver);
			 * �������ַ��������DriverManager�в�������һ��������������Ծ�����������Ͳ��������� 
			 * ������ע�������ķ���
			 * System.setProperty("jdbc.drivers","driver1:driver2");
			 * ���ַ�����Ȼ����Ծ����������Ͳ�������������ע�᲻̫���㣬����ʹ�á�
			 * */
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}

	// ��������
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	// �ͷ���Դ
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
