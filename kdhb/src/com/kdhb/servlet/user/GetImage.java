package com.kdhb.servlet.user;

import java.awt.Image;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdhb.dao.ImageDAO;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ImageDAO imageDao = new ImageDAO();
		
		// try {
		// imageDao.storeImg("f:/ebay.jpg");
		// System.out.println("1");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// System.out.println("0");
		// }
		try {
			Image image = imageDao.getImageFile(id);
//			ServletOutputStream sos = response.getOutputStream();
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
//			encoder.encode(image);
			System.out.println("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("0");
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
