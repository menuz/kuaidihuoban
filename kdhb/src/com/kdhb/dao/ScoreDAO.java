package com.kdhb.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

import com.kdhb.model.FaKuaidiOrder;
import com.kdhb.util.Global;
import com.kdhb.util.TableLoader;

/**
 * Servlet implementation class ScoreDAO
 */
@WebServlet("/ScoreDAO")
public class ScoreDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String minusScore = "update user set score = score - ? where id = ?";
	private static final String addScore = "update user set score = score + ? where id = ?";
	
	
	public ScoreDAO() {
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

	
	public boolean scoreCalculate(String release_user_id, String accept_user_id, String score) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(minusScore);
			pstmt.setString(1, score);
			pstmt.setString(2, release_user_id);
			int res = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(addScore);
			pstmt.setString(1, score);
			pstmt.setString(2, accept_user_id);
			int res1 = pstmt.executeUpdate();
			if(res > 0 && res1 > 0)
				return true;
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
}
