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
 * Servlet implementation class QueryShouKuaidi
 */
@WebServlet("/QueryShouKuaidi")
public class QueryShouKuaidi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryShouKuaidi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取当天所有订单，然后根据时间段，快递公司，目的地进行筛选
		String date = DateUtil.getDatetimeWithPattern(DateUtil.YMDPattern);
		String time = DateUtil.getDatetimeWithPattern(DateUtil.HMPattern);
		int timeinterval = Integer.parseInt(request.getParameter("timeinterval"));
		int company = Integer.parseInt(request.getParameter("company"));
		int dest = Integer.parseInt(request.getParameter("dest"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		int currentTimeInterval = 0;
		if(time.compareTo("12:30") < 0) {
			currentTimeInterval = 0;
		} else if(time.compareTo("16:00") < 0) {
			currentTimeInterval = 1;
		} else {
			currentTimeInterval = 2;
		}

		String sql = "select * from shou_kuaidi_order as sko, user as u where sko.release_user_id = u.id and date(ct) = curdate() and status = 0 and pack_sign_timeinterval > " + currentTimeInterval + " ";

		String suffix = "";
		if(timeinterval != -1) {
			suffix += (" and timeinterval = " + timeinterval);
		}
		if(company != -1) {
				suffix += (" and pack_company = " + company);
		}
		if(dest != -1) {
				suffix += (" and pack_dest = " + dest);
		}
		
		suffix += (" and release_user_id != " + userid);
		sql += suffix;
		
		System.out.println("sql = " + sql);
		
		ShouKuaidiDAO dao = new ShouKuaidiDAO();
		List<ShouKuaidiOrder> orders = dao.getShouKuaiOrder(sql);
		
		response.setContentType("text/html; charset=utf-8");  
		response.setCharacterEncoding("utf8"); 
		PrintWriter pw = response.getWriter();
		if(orders.size() == 0) {
			pw.write("0");
			System.out.println("没有符合要求的记录");
		} else {
			String orderInfo = new Gson().toJson(orders);
			pw.write(orderInfo);
			System.out.println("找出符合");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
