package com.kdhb.servlet.shou;

import java.io.IOException;
import java.util.ArrayList;
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
		
		HashMap<String, String> kuaidi = new HashMap<String, String>();
		kuaidi.put("1", "圆通");
		kuaidi.put("2", "申通");
		kuaidi.put("3", "国通");
		kuaidi.put("4", "ems");
		kuaidi.put("5", "京东");
		kuaidi.put("6", "天天快递");
		
		HashMap<String, String> timeInterval = new HashMap<String, String>();
		timeInterval.put("1", "11:30~12:30");
		timeInterval.put("2", "15:00~15:30");
		
		HashMap<String, String> start = new HashMap<String, String>();
		start.put("1", "F楼");
		start.put("2", "邮局");
		start.put("3", "申通快递店");
		
		HashMap<String, String> dest = new HashMap<String, String>();
		dest.put("1", "电信大楼");
		dest.put("2", "汽车工程学院");
		dest.put("3", "机械与材料学院");
		dest.put("4", "寝室楼1");
		dest.put("5", "寝室楼2");
		dest.put("6", "寝室楼3");
		dest.put("7", "寝室楼4");
		dest.put("8", "寝室楼5");
		dest.put("9", "寝室楼6");
		dest.put("10", "寝室楼7");
		dest.put("11", "寝室楼8");
		dest.put("12", "寝室楼9");
		dest.put("13", "寝室楼10");
		dest.put("14", "寝室楼11");
		dest.put("15", "寝室楼12");
		dest.put("16", "寝室楼13");
		dest.put("17", "寝室楼14");
		dest.put("18", "寝室楼15");
		dest.put("19", "寝室楼16");
		dest.put("20", "寝室楼17");
		dest.put("21", "寝室楼18");
		dest.put("22", "寝室楼19");
		dest.put("23", "寝室楼20");
		
		HashMap<String, String> size = new HashMap<String, String>();
		size.put("1", "小");
		size.put("2", "中");
		size.put("3", "大");
		
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(start);
		list.add(kuaidi);
		list.add(timeInterval);
		list.add(dest);
		list.add(size);
		
		String userInfo = new Gson().toJson(list);
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
