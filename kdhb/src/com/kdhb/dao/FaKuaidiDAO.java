package com.kdhb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kdhb.model.FaKuaidiOrder;
import com.kdhb.model.ShouKuaidiOrder;
import com.kdhb.util.Global;
import com.kdhb.util.TableLoader;


public class FaKuaidiDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String createFaKuaidiOrder = "insert into fa_kuaidi_order(user_id, order_id, book_address) values (?, ?, ?)";
	private static final String queryFaKuaidiOrder = "select * from fa_kuaidi_order where user_id=?";
	public FaKuaidiDAO() {
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
	
	public boolean createFaKuaidiOrder(int fa_id, String order_id, String book_address) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(createFaKuaidiOrder);

			pstmt.setInt(1, fa_id);
			pstmt.setString(2, order_id);
			pstmt.setString(3, book_address);
			
			int res = pstmt.executeUpdate();
			if(res > 0) 
				return true;
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
	
	public List<FaKuaidiOrder> getFaKuaidiOrder(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<FaKuaidiOrder> orders = new ArrayList<FaKuaidiOrder>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(queryFaKuaidiOrder);
			pstmt.setInt(1, user_id);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				FaKuaidiOrder order = TableLoader.loadFaKuaidiOrder(rst);
				orders.add(order);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return orders;
	}
	
}


