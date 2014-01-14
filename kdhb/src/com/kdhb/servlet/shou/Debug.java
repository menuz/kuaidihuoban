package com.kdhb.servlet.shou;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Debug
 */
@WebServlet("/Debug")
public class Debug extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Debug() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/json;charset=utf8");
		
		HashMap<String, String> debug = new HashMap<String, String>();
		debug.put("1", "圆通");
		debug.put("2", "申通");
		debug.put("3", "国通");
		debug.put("4", "ems");
		debug.put("5", "京东");
		debug.put("6", "天天快递");
		
		debug.put("100", "11:30~12:30");
		debug.put("101", "15:00~15:30");
		
		debug.put("200", "电信大楼");
		debug.put("201", "汽车工程学院");
		debug.put("202", "机械与材料学院");
		debug.put("210", "寝室楼1");
		debug.put("211", "寝室楼2");
		debug.put("212", "寝室楼3");
		debug.put("213", "寝室楼4");
		debug.put("214", "寝室楼5");
		debug.put("215", "寝室楼6");
		debug.put("216", "寝室楼7");
		debug.put("217", "寝室楼8");
		debug.put("218", "寝室楼9");
		debug.put("219", "寝室楼10");
		debug.put("220", "寝室楼11");
		debug.put("221", "寝室楼12");
		debug.put("222", "寝室楼13");
		debug.put("223", "寝室楼14");
		debug.put("224", "寝室楼15");
		debug.put("225", "寝室楼16");
		debug.put("226", "寝室楼17");
		debug.put("227", "寝室楼18");
		debug.put("228", "寝室楼19");
		debug.put("229", "寝室楼20");
		
		debug.put("300", "小");
		debug.put("301", "中");
		debug.put("302", "大");
		
		String userInfo = new Gson().toJson(debug);
		response.getWriter().write(userInfo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
