package com.kdhb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kdhb.model.UserBean;
import com.kdhb.util.TableLoader;

public class LoginDAO extends BaseDAO {
	
	private static InitialContext context = null;
	private DataSource dataSource = null;
	
	private static final String login="select * from user where username=? and password=? and tag='1'";

	public LoginDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/kuaidibao");
		} catch (NamingException e2) {
			e2.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(!conn.isClosed()) {
				System.out.println("ping success");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

		

	/**
	 * 如果数据库中有这个已激活的用户，则返回true，登录成功
	 * @param username
	 * @param password
	 * @return
	 */
	public UserBean login(String username,String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		boolean b=false;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(login);

			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rst = pstmt.executeQuery();

			if(rst.next()){
				return TableLoader.loadUser(rst);
			} 
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return null;

	}
}


