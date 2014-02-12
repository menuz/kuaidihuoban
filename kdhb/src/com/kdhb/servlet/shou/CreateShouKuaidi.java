package com.kdhb.servlet.shou;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.ShouKuaidiDAO;

/**
 * Servlet implementation class CreateShouKuaidi
 */
@WebServlet("/CreateShouKuaidi")
public class CreateShouKuaidi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateShouKuaidi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		response.setContentType("text/json;charset=utf8");
		PrintWriter out = response.getWriter();

		boolean flag = false;

		try {
			int user_id = Integer.parseInt(request.getParameter("userid"));
			String username = request.getParameter("username");
			int sign_timeinterval = Integer.parseInt(request.getParameter("sign_timeinterval"));
			int company = Integer.parseInt(request.getParameter("company"));
			int size = Integer.parseInt(request.getParameter("size"));
			int start = Integer.parseInt(request.getParameter("start"));
			int dest = Integer.parseInt(request.getParameter("dest"));
			int money = Integer.parseInt(request.getParameter("money"));
			String dest_time = request.getParameter("to_dest_time");
			// 例如取件时间3:30-4:30，发布一条找人取件的记录，这条记录在一般在3:30之前有效。
			String order_valid_time = request.getParameter("order_valid_time");

			String order_id = "";

			ShouKuaidiDAO dao = new ShouKuaidiDAO();
			flag = dao.createShouKuaidiOrder(user_id, order_id,
					sign_timeinterval, company, size, start, dest, money, dest_time,
					order_valid_time);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} 

		if (flag) {
			out.println("1");
			System.out.println("生成发订单成功");
		} else {
			out.println("0");
			System.out.println("生成发订单失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
