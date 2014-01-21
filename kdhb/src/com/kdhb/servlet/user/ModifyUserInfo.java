package com.kdhb.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.ModifyUserInfoDAO;
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

		int status = 0;
		if (request.getParameter("userId") != null) {
			try {
				int user_id = Integer.parseInt(request.getParameter("userId"));
				//String username = request.getParameter("username");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String admit_time = request.getParameter("admit_time");
				String college = request.getParameter("college");
				String major_name = request.getParameter("major_name");
				int graduate;
				String floor = request.getParameter("floor");
				String lab = request.getParameter("lab");
				ModifyUserInfoDAO modifyUserInfoDao = new ModifyUserInfoDAO();
				UserBean user = modifyUserInfoDao.getUserInfoByUserId(user_id);
				if (password == null)
					password = user.getPassword();
				if (name == null)
					name = user.getName();
				if (phone == null)
					phone = user.getPhone();
				if (admit_time == null)
					admit_time = user.getAdmit_time();
				if (college == null)
					college = user.getCollege();
				if (major_name == null)
					major_name = user.getMajor_name();
				if (floor == null)
					floor = user.getFloor();
				if (lab == null)
					lab = user.getLab();
				if (request.getParameter("graduate") == null)
					graduate = user.getGraduate();
				else
					graduate = Integer.parseInt(request.getParameter("graduate"));
				if (modifyUserInfoDao.updateUserInfo(password, name, phone, admit_time, college, major_name, graduate, floor, lab))
					status = 3;
				else
					status = 2;
			} catch (Exception e) {
				e.printStackTrace();
				status = 2;
			}
		}

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
