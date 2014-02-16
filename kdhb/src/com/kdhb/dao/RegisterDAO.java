package com.kdhb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kdhb.util.Global;

public class RegisterDAO extends BaseDAO{

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String check = "select * from user where username=?"; // 验证该学号是否已经被注册过
	private static final String regist = "update user set password = ?, phone = ? where username = ?";
	private static final String active = "update user set tag=1 where username=?";
	private static final String getNameWithUsername = "select name from user where username = ?";
	
	public RegisterDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context
					.lookup(Global.datasource_url);
		} catch (NamingException e2) {
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
		}
		return conn;
	}
	
	public boolean phoneCodeConfirm(String phone, String code) {
		return true;
	}

	/**
	 * 检测是否存在用户
	 * 
	 * @param username
	 * @return
	 */
	public boolean existUserName(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(check);

			pstmt.setString(1, username);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				return true;
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);

		}
		return false;
	}

	public boolean isActive(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from user where username = ? and tag = 1");

			pstmt.setString(1, username);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				return true;
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);

		}
		return false;
	}
	
	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param tag
	 */
	public void regist(String username, String password, String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(regist);

			pstmt.setString(1, password);
			pstmt.setString(2, phone);
			pstmt.setString(3, username);
			
			pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception se) {

		} finally {
			try {
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException se) {
			}
		}
		return;

	}

	/**
	 * 激活用户
	 * 
	 * @param username
	 */
	public void active(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(active);

			pstmt.setString(1, username);
			pstmt.executeUpdate();

			pstmt.close();
			return;
		} catch (Exception se) {

		} finally {
			try {
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException se) {
			}
		}
		return;
	}
	
	
//	public String getNameByUsername(String username) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		ArrayList<BusBean> list = new ArrayList<BusBean>();
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(getNameWithUsername);
//
//			pstmt.setString(1, username);
//			rst = pstmt.executeQuery();
//
//			if (rst.next()) {
//				return rst.getString("name");
//			}
//			
//		} catch (Exception se) {
//			
//			se.printStackTrace();
//			
//		} finally {
//			releaseSource(conn, pstmt, rst);
//		}
//		
//		return "";
//	}

	
	
}


