package com.kdhb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.FindPwdDAO;
import com.kdhb.dao.RegisterDAO;
import com.kdhb.util.EmailClient;
import com.kdhb.util.EmailClient.HOST;
import com.kdhb.util.Global;

/**
 * Servlet implementation class FindPassword
 */
@WebServlet("/FindPassword")
public class FindPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPassword() {
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
		
		RegisterDAO dao = new RegisterDAO();
		
		if(!dao.existUserName(username)) { // username do not exist 
			out.println("0");
			System.out.println("用户名"+username+"不存在");
		} else {
			String pwd = new FindPwdDAO().getPwd(username);
			if(pwd.equals("")) {
				out.println("1");
				System.out.println("用户名"+username+"存在,但没有注册，请先注册");
			} else {
				if(dao.isActive(username)) {  // username exist , and has actived
					EmailClient client = new EmailClient(HOST._163,
							Global.sendEmail, Global.sendEmailPwd);

					// 输入接收方的邮箱，主题和内容
					client.sendFindEmail(username + "@tongji.edu.cn", "您在使用找回密码，以下为您的密码信息",
							"账号:" + username + "\n密码:" + pwd); // 发送邮件到同济大学邮箱

					out.println("3");
					System.out.println(username + "正在找回密码，邮件已经发送到邮箱");
				} else {		// username exist, send an email to your email box 
					out.println("2");
					System.out.println("用户名"+username+"仍未激活，请先到邮箱激活");
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
