package com.kdhb.servlet.shou;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.ShouKuaidiDAO;
import com.kdhb.util.DateUtil;

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

		// 取当天所有订单，然后根据时间段，快递公司，目的地进行筛选
		String date = DateUtil.getDatetimeWithPattern(DateUtil.YMDPattern);
		String time = DateUtil.getDatetimeWithPattern(DateUtil.HMPattern);

		// [00:00~11:30)  0
		// [11:30~15:00)  1
		// [15:00~24:00)  2
		
		// 去快递时间段  11:30 ~ 12:30     15:00 ~ 16:00
		
		int currentTimeInterval = 0;
		if (time.compareTo("11:30") < 0) {
			currentTimeInterval = 0;
		} else if (time.compareTo("15:00") < 0) {
			currentTimeInterval = 1;
		} else {
			currentTimeInterval = 2;
		}

		int status = 0;

		try {
			int user_id = Integer.parseInt(request.getParameter("userid"));
			String username = request.getParameter("username");
			int sign_timeinterval = Integer.parseInt(request
					.getParameter("sign_timeinterval"));

			if (sign_timeinterval > currentTimeInterval) {

				int company = Integer.parseInt(request.getParameter("company"));
				int size = Integer.parseInt(request.getParameter("size"));
				int start = Integer.parseInt(request.getParameter("start"));
				int dest = Integer.parseInt(request.getParameter("dest"));
				int money = Integer.parseInt(request.getParameter("money"));
				String dest_time = request.getParameter("to_dest_time");
				// 例如取件时间3:30-4:30，发布一条找人取件的记录，这条记录在一般在3:30之前有效。
				String order_valid_time = request
						.getParameter("order_valid_time");

				String order_id = "";

				ShouKuaidiDAO dao = new ShouKuaidiDAO();
				if(dao.createShouKuaidiOrder(user_id, order_id,
						sign_timeinterval, company, size, start, dest, money,
						dest_time, order_valid_time)) {
					status = 1;
				} else {
					status = 0;
				}
			} else {
				status = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 0;
		}

		if (status == 0) {
			out.println("0");
			System.out.println("生成发订单失败");
		} else if(status == 1){
			out.println("1");
			System.out.println("生成发订单成功");
		} else {
			out.println("2");
			System.out.println("接受快递时间已经过时");
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
