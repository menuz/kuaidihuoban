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
 * @author: dmnrei@gmail.com 
 * @version: V1.0
 */
public class ShouKuaidiDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String createShouKuaidOrder = "insert into shou_kuaidi_order(id, release_user_id, order_id, " +
			"ct, pack_sign_timeinterval, pack_company, pack_size, pack_start, pack_dest, pack_money, pack_to_dest_time, order_valid_time," +
			"status) values (null, ?, ?, null, ?, ?, ?, ?,?, ?,?,?,0)";
	
	private static final String queryReleaseOrder = "select * from shou_kuaidi_order as sko, user as u where sko.accept_user_id = u.id and release_user_id = ? order by ct desc";
	private static final String queryAcceptOrder = "select * from shou_kuaidi_order as sko, user as u where sko.release_user_id = u.id and accept_user_id = ? order by ct desc";
	private static final String acceptOrder = "update shou_kuaidi_order set accept_user_id = ? , status = ? where id = ? and release_user_id = ?";
	private static final String finishOrder = "update shou_kuaidi_order set status = ? where id = ? and release_user_id = ? and accept_user_id = ?";
	private static final String existUser = "select * from user where id = ?";
	
	private static final String getStatus = "select status from shou_kuaidi_order where id = ?";
	
	private static final String existOrder = "select * from shou_kuaidi_order where id = ?";
	
	
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
			int company, int size, int start, int dest, int money, String dest_time, String order_valid_time) {
		
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
			pstmt.setInt(6, start);
			pstmt.setInt(7, dest);
			pstmt.setInt(8, money);
			pstmt.setString(9, dest_time);
			pstmt.setString(10, order_valid_time);
			
			int res = pstmt.executeUpdate();
			if(res > 0) return true;
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
	public boolean existUser(String release_user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(existUser);
			pstmt.setString(1, release_user_id);
			
			rst = pstmt.executeQuery();
			if(rst.next()) {
				return true;
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
	public boolean existOrder(String orderId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(existOrder);
			pstmt.setString(1, orderId);
			
			rst = pstmt.executeQuery();
			if(rst.next()) {
				return true;
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return false;
	}
	
	public List<ShouKuaidiOrder> queryReleaserOrderWithId(String release_user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<ShouKuaidiOrder> orders = new ArrayList<ShouKuaidiOrder>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(queryReleaseOrder);
			pstmt.setString(1, release_user_id);
			
			rst = pstmt.executeQuery();
			while(rst.next()) {
				ShouKuaidiOrder order = TableLoader.loadReleaseShouKuaidiOrder(rst);
				orders.add(order);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
		return orders;
	}
	
	public List<ShouKuaidiOrder> queryAccepterOrderWithId(String accept_user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<ShouKuaidiOrder> orders = new ArrayList<ShouKuaidiOrder>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(queryAcceptOrder);
			pstmt.setString(1, accept_user_id);
			
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
	
	public int acceptOrder(String id, String release_user_id, String accept_user_id, String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(acceptOrder);
			pstmt.setString(1, accept_user_id);
			pstmt.setString(2, status);
			pstmt.setString(3, id);
			pstmt.setString(4, release_user_id);
			
			return res = pstmt.executeUpdate();
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}
	
	public int finishOrder(String id, String release_user_id, String accept_user_id, String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(finishOrder);
			pstmt.setString(1, status);
			pstmt.setString(2, id);
			pstmt.setString(3, release_user_id);
			pstmt.setString(4, accept_user_id);
			
			return res = pstmt.executeUpdate();
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}
	
	
	public int getStatus(String orderId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getStatus);
			pstmt.setString(1, orderId);
			
			rst = pstmt.executeQuery();
			if(rst.next()) {
				return res = rst.getInt("status");
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}
	
}


