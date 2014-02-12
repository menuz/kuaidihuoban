package com.kdhb.servlet.fa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kdhb.dao.FaKuaidiDAO;
import com.kdhb.model.FaKuaidiOrder;

/**
 * Servlet implementation class QueryFaKuaidi
 */
@WebServlet("/QueryFaKuaidi")
public class QueryFaKuaidi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryFaKuaidi() {
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
		if (request.getParameter("Id") != null) {
			status = 1;
			try {
				//这里要注意的是对含中文的元素，要进行转换
				int id = Integer.parseInt(request.getParameter("Id"));
				//调用dao
				FaKuaidiDAO dao = new FaKuaidiDAO();
				List<FaKuaidiOrder> orders = dao.getFaKuaidiOrder(id);
				//test
				System.out.println("id:" + id);
				if (orders.size() == 0) {
					status = 3;
					out.println("3");
					System.out.println("没有符合要求的记录");
				} else {
					String orderInfo = new Gson().toJson(orders);
					out.println("1");
					out.write(orderInfo);
					System.out.println("找出符合");
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 2;
			}
		}
		if (status == 0) {
			out.println("0");
			System.out.println("未登陆");
		} else if (status == 2) {
			out.println("2");
			System.out.println("出错啦");
		}
		

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
