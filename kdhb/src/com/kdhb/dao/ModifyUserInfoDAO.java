package com.kdhb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kdhb.model.ShouKuaidiOrder;
import com.kdhb.util.Global;
import com.kdhb.util.TableLoader;
import com.kdhb.model.UserBean;

public class ModifyUserInfoDAO  extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String getUserInfoByUserId = "select * from user where id = ?";
	private static final String updateUserInfo = "update user set password = ?, name = ?, phone = ?, admit_time = ?, college = ?, major_name = ?, graduate = ?, floor = ?, lab = ?";
	
	public ModifyUserInfoDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context
					.lookup(Global.datasource_url);
		} catch (NamingException e2) {
		}
	}
	
	public UserBean getUserInfoByUserId(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getUserInfoByUserId);
			pstmt.setInt(1, id);
			rst = pstmt.executeQuery();
			if (rst.next())
				return TableLoader.loadUser(rst);
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		return null;
	}
	
	
	public boolean updateUserInfo(String password, String name, String phone, String admit_time, String college, String major_name, int graduate, String floor, String lab) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateUserInfo);

			pstmt.setString(1, password);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, admit_time);
			pstmt.setString(5, college);
			pstmt.setString(6, major_name);
			pstmt.setInt(7, graduate);
			pstmt.setString(8, floor);
			pstmt.setString(9, lab);
			
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
	
}
