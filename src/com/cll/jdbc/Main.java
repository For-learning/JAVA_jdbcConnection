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
			// 建立连接
			conn = JDBCUtial.getConnection();
			// 开始事物
			conn.setAutoCommit(false);
			// 执行查询
			// 不带有参数的
			String sql = "select * from city";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			// 设置事物的回滚点
			conn.setSavepoint();
			// 带有参数的
			/**
			 * String sqlParamete = "select * from table where name =?";
			 * PreparedStatement ps = conn.prepareStatement(sqlParamete);
			 * ps.setString(0, "xiaoming"); ps.executeQuery();
			 */
			/**
			 * 对于需要返回查询结果的语句比如select用st.executeQuery(sql);
			 * 对于只需要知道是否执行成功的语句比如insert，update，delete语句则用st.executeUpdate(sql);
			 * */
			// 处理结果
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
			// 事物结束
			conn.commit();

		} catch (SQLException e) {
			// 事物回滚
			if (conn != null) {
				conn.rollback();
			}
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, st, conn);
		}
	}

}
