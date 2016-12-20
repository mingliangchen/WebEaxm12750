package com.hand.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BaseDao {
	private static String url;
	private static String user;
	private static String password;
	private static String driver;

	private BaseDao() {
		
	}

	static {
		try {
			Properties db = new Properties();
			db.load(BaseDao.class.getResourceAsStream("db.properties"));//ע��·��
			url = db.getProperty("url");
			user = db.getProperty("user");
			password = db.getProperty("password");
			driver = db.getProperty("driver");
			System.out.println(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			rs=null;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				st=null;
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					conn=null;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Connection con=null;
		String sql = "select * from customer";
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			con = BaseDao.getConn();
			ps= con.prepareStatement(sql);
			rs= ps.executeQuery();
			JdbcTemplate.printResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.free(rs,ps,con);
		}	
	}
}
