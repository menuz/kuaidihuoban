package com.kdhb.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.UserInfoDAO;
import com.kdhb.model.UserBean;

/**
 * Servlet implementation class ModifyUserInfo
 */
@WebServlet("/ModifyUserInfo")
public class ModifyUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/json;charset=utf8");
		PrintWriter out = response.getWriter();
		/*
		 * 0表示未登录
		 * 1表示信息未修改
		 * 2表示修改失败，包含任何错误
		 * 3表示修改成功
		 */
		int status = 0;
		if (request.getParameter("userId") != null) {
			status = 1;
			try {
				//获得每个参数，判断是否为空以及是否与之前相同
				//这里要注意的是对含中文的元素，要进行转换
				int user_id = Integer.parseInt(request.getParameter("userId"));
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String admit_time = request.getParameter("admit_time");
				String college = request.getParameter("college");
				String major_name = request.getParameter("major_name");
				int graduate;
				String floor = request.getParameter("floor");
				String lab = request.getParameter("lab");
				//调用dao
				UserInfoDAO modifyUserInfoDao = new UserInfoDAO();
				UserBean user = modifyUserInfoDao.getUserInfoByUserId(user_id);
				if (password == null || password.equals(user.getPassword()))
					password = user.getPassword();
				else
					status = 3;
				if (name == null) {
					name = user.getName();
				} else {
					name = new String(name.getBytes("ISO-8859-1"), "utf-8");
					if (!name.equals(user.getName()))
						status = 3;
				}
				if (phone == null || phone.equals(user.getPhone()))
					phone = user.getPhone();
				else
					status = 3;
				if (admit_time == null || admit_time.equals(user.getAdmit_time()))
					admit_time = user.getAdmit_time();
				else {
					status = 3;
				}
				if (college == null || college.equals(user.getCollege()))
					college = user.getCollege();
				else 
					status = 3;
				if (major_name == null)
					major_name = user.getMajor_name();
				else {
					major_name = new String(major_name.getBytes("ISO-8859-1"), "utf-8");
					if (!major_name.equals(user.getMajor_name()))
						status = 3;
				}
				if (floor == null)
					floor = user.getFloor();
				else {
					floor = new String(floor.getBytes("ISO-8859-1"), "utf-8");
					if (!floor.equals(user.getFloor()))
						status = 3;
				}
				if (lab == null)
					lab = user.getLab();
				else {
					lab = new String(lab.getBytes("ISO-8859-1"), "utf-8");
					if (!lab.equals(user.getLab()))
						status = 3;
				}
				if (request.getParameter("graduate") == null || Integer.parseInt(request.getParameter("graduate")) == user.getGraduate())
					graduate = user.getGraduate();
				else {
					graduate = Integer.parseInt(request.getParameter("graduate"));
					status = 3;
				}
				//更新
				if (!modifyUserInfoDao.updateUserInfo(password, name, phone, admit_time, college, major_name, graduate, floor, lab, user_id))
					status = 2;
				//test
			} catch (Exception e) {
				e.printStackTrace();
				status = 2;
			}
		}
		//输出结果
		if (status == 0) {
			out.println("0");
			System.out.println("未登陆");
		} else if (status == 1) {
			out.println("1");
			System.out.println("信息未修改");
		} else if (status == 2) {
			out.println("2");
			System.out.println("信息修改失败");
		} else if (status == 3) {
			out.println("3");
			System.out.println("信息修改成功");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
