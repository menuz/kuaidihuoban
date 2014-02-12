package com.kdhb.servlet.fa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.FaKuaidiDAO;

/**
 * Servlet implementation class CreateFaKuaidi
 */
@WebServlet(description = "create the order of fakuaidi", urlPatterns = { "/CreateFaKuaidi" })
public class CreateFaKuaidi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateFaKuaidi() {
		super();
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
		/*
		 * 0表示未登录
		 * 1表示成功
		 * 2表示出现错误
		 */
		int status = 0;
		if (request.getParameter("Id") != null) {
			status = 1;
			try {
				//获得每个参数，判断是否为空以及是否与之前相同
				//这里要注意的是对含中文的元素，要进行转换
				int id = Integer.parseInt(request.getParameter("Id"));
				String order_id = "123242123";
				String book_address = request.getParameter("book_address");
				book_address = new String(book_address.getBytes("ISO-8859-1"), "utf-8");
				//调用dao
				FaKuaidiDAO dao = new FaKuaidiDAO();
				if (!dao.createFaKuaidiOrder(id, order_id, book_address))
					status = 2;
				//test
				System.out.println("id:" + id);
				System.out.println("book_address:" + book_address); 
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
			System.out.println("成功啦");
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
		doGet(request, response);
	}

}
