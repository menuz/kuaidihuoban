package com.kdhb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.RegisterDAO;
import com.kdhb.util.MD5Util;

/**
 * Servlet implementation class ActiveUser
 */
@WebServlet("/ActiveUser")
public class ActiveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json;charset=gbk");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String key=request.getParameter("key");        //用户输入的key
		
		String truekey=MD5Util.MD5(MD5Util.pwd+username);  //正确的key
		
		if(truekey.equals(key)){
			new RegisterDAO().active(username);
			out.println("恭喜您，账户已经激活，可以使用快递伙伴了");
			System.out.println(username+"账户已经激活");
		}else{
			out.println("请不要恶意注册他人账号");
			System.out.println("有人在恶意注册"+username+"账户");
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
