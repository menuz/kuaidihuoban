package com.kdhb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.RegisterDAO;
import com.kdhb.util.EmailClient;
import com.kdhb.util.EmailClient.HOST;
import com.kdhb.util.MD5Util;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json;charset=utf8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		
		RegisterDAO dao = new RegisterDAO();
		
		// phone & code confirm
	
		if(!dao.existUserName(username)) { // username do not exist 
			out.println("0");
			System.out.println("用户名"+username+"不存在");
		} else {
			if(dao.isActive(username)) {  // username exist , and has actived
				out.println("1");
				System.out.println("用户名"+username+"已经激活");
			} else {		// username exist, send an email to your email box 
				
				if(dao.phoneCodeConfirm(phone, code)) {
					dao.regist(username, password, phone);
					out.println("2");  
					// 输入发送方服务器，您的邮箱和密码
					EmailClient client = new EmailClient(HOST._163, "kuaidihuoban@163.com",
							"kdhb2014");

					// 输入接收方的邮箱，主题和内容
					String key=MD5Util.MD5(MD5Util.pwd+username);
					client.sendRegistEmail(username+"@tongji.edu.cn", "请激活您的账户",username+"&key="+key);  //发送邮件到同济大学邮箱
					System.out.println(username+"注册成功，等待激活，邮件已经发送到邮箱");
				} else {
					out.println("3");
					System.out.println("手机号码跟验证码不匹配");
				}
			}
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
