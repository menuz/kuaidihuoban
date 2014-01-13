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

public class FindPwdDAO extends BaseDAO{

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String check = "select * from user where username=?"; 
	private static final String getNameWithUsername = "select name from user where username = ?";
	
	public FindPwdDAO() {
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
	
	
	public String getPwd(String username) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		String password = "";
		try {
			conn = dataSource.getConnection();
			pstmt = conn
					.prepareStatement("select password from user where username=?");

			pstmt.setString(1, username);
			rst = pstmt.executeQuery();

			while (rst.next()) {

				password = rst.getString("password");

			}
			rst.close();
			pstmt.close();
			return password;
		} catch (Exception se) {
			return password;
		} finally {
			try {
				conn.close();
			} catch (SQLException se) {
			}

		}
	}	
	
}


