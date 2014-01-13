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

public class UtilDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String getNameWithUsername = "select name from user where username = ?";
	
	public UtilDAO() {
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
	
	
	public String getNameByUsername(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getNameWithUsername);

			pstmt.setString(1, username);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				return rst.getString("name");
			}
			
		} catch (Exception se) {
			
			se.printStackTrace();
			
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return "";
	}
	
	

}


