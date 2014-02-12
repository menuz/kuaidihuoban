package com.kdhb.servlet.shou;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kdhb.dao.ShouKuaidiDAO;
import com.kdhb.model.ShouKuaidiOrder;
import com.kdhb.util.DateUtil;

/**
 * Servlet implementation class QueryWithReleaser
 */
@WebServlet("/QueryWithReleaser")
public class QueryWithReleaser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryWithReleaser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String release_user_id = request.getParameter("userid");

		System.out.println("release_user_id = " + release_user_id);
		
		ShouKuaidiDAO dao = new ShouKuaidiDAO();

		PrintWriter pw = response.getWriter();
		if (dao.existUser(release_user_id.trim())) {
			List<ShouKuaidiOrder> orders = dao
					.queryReleaserOrderWithId(release_user_id);
			
			String orderInfo = new Gson().toJson(orders);
			pw.write(orderInfo);
			System.out.println("找出release_user_id="+release_user_id+"的订单");
		} else {
			pw.write("0");
			System.out.println("該用戶不存在");
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
