package com.hand.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtilExcute {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String username = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/sakila?characterEncoding=utf-8";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection(url, username, password);
	}
	public static List<Object> executeQuery (String sql, List<Object> values)
			throws SQLException {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Object> objects=new ArrayList<Object>();
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			set = ps.executeQuery();
			ResultSetMetaData metaData = set.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (set.next()) {
				Map<String, Object> map = new HashMap<String,Object>();
				for(int i = 1 ; i <= columnCount ; i++){
					map.put(metaData.getColumnName(i), set.getObject(i));
				}
				objects.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, set);
		}
		return objects;
	}

	
	public static int insertOrUpdateOrDelete(String sql,List<Object> values) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn = null;
		int res=-1;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					stmt.setObject(i + 1, values.get(i));
				}
			}
			res = stmt.executeUpdate();
			return res;
		}finally {
			close(conn, stmt, null);
		}
	}
	public static void close(Connection con, Statement st, ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
