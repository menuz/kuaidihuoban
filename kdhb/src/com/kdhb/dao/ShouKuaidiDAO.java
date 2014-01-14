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

import com.kdhb.model.ShouKuaidiOrder;
import com.kdhb.util.Global;
import com.kdhb.util.TableLoader;

/**
 * 
 * 发快递，主语是同学
 * @author: dmnrei@gmail.com 
 * @version: V1.0
 */
public class ShouKuaidiDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String createShouKuaidOrder = "insert into shou_kuaidi_order(id, release_user_id, order_id, " +
			"ct, pack_sign_timeinterval, pack_company, pack_size, pack_dest, pack_money, pack_to_dest_time, order_valid_time," +
			"status) values (null, ?, ?, null, ?, ?, ?, ?, ?,?,?,0)";
	
	public ShouKuaidiDAO() {
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
	
	public boolean createShouKuaidiOrder(int release_user_id, String order_id, int pack_sign_timeinterval, 
			int company, int size, int dest, int money, String dest_time, String order_valid_time) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(createShouKuaidOrder);

			pstmt.setInt(1, release_user_id);
			pstmt.setString(2, order_id);
			pstmt.setInt(3, pack_sign_timeinterval);
			pstmt.setInt(4, company);
			pstmt.setInt(5, size);
			pstmt.setInt(6, dest);
			pstmt.setInt(7, money);
			pstmt.setString(8, dest_time);
			pstmt.setString(9, order_valid_time);
			
			int res = pstmt.executeUpdate();
			if(res > 0) return true;
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
	
	public List<ShouKuaidiOrder> getShouKuaiOrder(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<ShouKuaidiOrder> orders = new ArrayList<ShouKuaidiOrder>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rst = pstmt.executeQuery();
			while(rst.next()) {
				ShouKuaidiOrder order = TableLoader.loadShouKuaidiOrder(rst);
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


