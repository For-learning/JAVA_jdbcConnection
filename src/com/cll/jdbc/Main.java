package com.cll.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {
		execut();
	}

	private static void execut() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ��������
			conn = JDBCUtial.getConnection();
			// ��ʼ����
			conn.setAutoCommit(false);
			// ִ�в�ѯ
			// �����в�����
			String sql = "select * from city";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			// ��������Ļع���
			conn.setSavepoint();
			// ���в�����
			/**
			 * String sqlParamete = "select * from table where name =?";
			 * PreparedStatement ps = conn.prepareStatement(sqlParamete);
			 * ps.setString(0, "xiaoming"); ps.executeQuery();
			 */
			/**
			 * ������Ҫ���ز�ѯ�����������select��st.executeQuery(sql);
			 * ����ֻ��Ҫ֪���Ƿ�ִ�гɹ���������insert��update��delete�������st.executeUpdate(sql);
			 * */
			// ������
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
			// �������
			conn.commit();

		} catch (SQLException e) {
			// ����ع�
			if (conn != null) {
				conn.rollback();
			}
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, st, conn);
		}
	}

}
