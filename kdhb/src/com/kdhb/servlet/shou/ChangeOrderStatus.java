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

/**
 * Servlet implementation class ChangeOrderStatus
 */
@WebServlet("/ChangeOrderStatus")
public class ChangeOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeOrderStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShouKuaidiDAO dao = new ShouKuaidiDAO();
		PrintWriter pw = response.getWriter();
		
		//  attention 1   问题：API可以被其他人知道。每次交互都要进行toke交互，只有token跟用户匹配之后才能进行api的访问。谨防他人直接调用api进行操作。
		//  attention 2   这里需要对不同情况进行反映，例如当自己尝试接受自己订单，那就不行，需要提示用户非法操作。又例如当订单已经被接受，你还去接受，
		//                这个当然不行，如果API是给第三方使用的话，这些情况都要处理，现在是自己处理，知道如何ok，如何不行，所以问题不是很大。
		
		String orderId = request.getParameter("orderId");
		String status = request.getParameter("status");
		String accept_user_id = request.getParameter("accept_user_id");
		String release_user_id = request.getParameter("release_user_id");
		System.out.println("status = " + status);
		
		int oldstatus = dao.getStatus(orderId);
		
		if(dao.existUser(accept_user_id) && dao.existUser(release_user_id) && dao.existOrder(orderId) 
				&& valid(status, oldstatus)) {
			
			if(status.trim().equals("1")) {
				int res = dao.acceptOrder(orderId, release_user_id, accept_user_id, status);
				
				System.out.println("res = " + res);
				
				if(res <= 0) {
					pw.write("1");
					System.out.println("接受任务失败");
				} else {
					pw.write("2");
					System.out.println("接受任务成功");
				}
			} else if(status.trim().equals("2") || status.trim().equals("3")) {
				int res = dao.finishOrder(orderId, release_user_id, accept_user_id, status);
				
				System.out.println("res = " + res);
				
				if (res <= 0) {
					pw.write("3");
					System.out.println("完成任务状态失败");
				} else {
					pw.write("4");
					System.out.println("完成任务状态成功");
				}
				
			} 
		} else {
			pw.write("0");
			System.out.println("参数有误");
		}
		
	}
	
	public boolean valid(String status, int oldstatus) {
		if(status != null) {
			if(status.equals("1") || status.equals("2") || status.equals("3")) {
				if(status.equals("1") && oldstatus == 0 ||
						status.equals("2") && oldstatus == 1 || 
							status.equals("3") && oldstatus == 1) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
