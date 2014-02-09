/**
 * 文件名：ClassLoader.java
 *
 * 版本信息： version 1.0
 * 日期：2013-9-15
 * Copyright by menuz
 */
package com.kdhb.util;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kdhb.model.FaKuaidiOrder;
import com.kdhb.model.ShouKuaidiOrder;
import com.kdhb.model.UserBean;

public class TableLoader {
	public static UserBean loadUser(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String name = rs.getString("name");
		String phone = rs.getString("phone");
		String admit_time = rs.getString("admit_time");
		String college = rs.getString("college");
		String major_name = rs.getString("major_name");
		int graduate = rs.getInt("graduate");
		String floor = rs.getString("floor");
		String lab = rs.getString("lab");
		int tag = rs.getInt("tag");
		int release_task_no = rs.getInt("release_task_no");
		int accept_task_no = rs.getInt("accept_task_no");

		UserBean user = new UserBean(id, username, password, name, phone,
				admit_time, college, major_name, graduate, floor, lab, tag,
				release_task_no, accept_task_no);

		return user;
	}

	public static ShouKuaidiOrder loadShouKuaidiOrder(java.sql.ResultSet rs)
			throws SQLException, UnsupportedEncodingException {
		int id = rs.getInt("id");
		int release_user_id = rs.getInt("release_user_id");
		String order_id = rs.getString("order_id");
		Timestamp ct = rs.getTimestamp("ct");
		int pack_sign_timeinterval = rs.getInt("pack_sign_timeinterval");
		int pack_company = rs.getInt("pack_company");
		int pack_size = rs.getInt("pack_size");
		int pack_dest = rs.getInt("pack_dest");
		int pack_money = rs.getInt("pack_money");
		String pack_to_dest_time = rs.getString("pack_to_dest_time");
		String order_valid_time = rs.getString("order_valid_time");
		int accept_user_id = rs.getInt("accept_user_id");
		int status = rs.getInt("status");

		ShouKuaidiOrder order = new ShouKuaidiOrder(id, release_user_id,
				order_id, ct, pack_sign_timeinterval, pack_company, pack_size,
				pack_dest, pack_money, pack_to_dest_time, order_valid_time,
				accept_user_id, status);

		return order;
	}
	public static FaKuaidiOrder loadFaKuaidiOrder(java.sql.ResultSet rs)
			throws SQLException, UnsupportedEncodingException {
		int id = rs.getInt("id");
		int user_id = rs.getInt("user_id");
		String order_id = rs.getString("order_id");
		String receiver_name = rs.getString("receiver_name");
		String receiver_address = rs.getString("receiver_address");
		String book_time = rs.getString("book_time");
		String book_address = rs.getString("book_address");

		FaKuaidiOrder order = new FaKuaidiOrder(id, user_id,
				order_id, receiver_name, receiver_address, book_time, book_address);

		return order;
	}

}
