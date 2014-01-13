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

import com.kdhb.model.UserBean;

public class TableLoader {
	public static UserBean loadUser(java.sql.ResultSet rs)
			throws SQLException, UnsupportedEncodingException {
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

}
