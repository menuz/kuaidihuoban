package com.kdhb.servlet.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		System.out.println("id:" + id);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request size constraint
			upload.setSizeMax(1024 * 400);

			// 每个表单域中数据会封装到一个对应的FileItem对象上
			try {
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				System.out.println("size:" + items.size());
				// 区分表单域
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					// isFormField为true，表示这不是文件上传表单域
					if (!item.isFormField()) {
						// 获取存放文件的物理路径
						String path = servletContext.getRealPath("/upload");
						System.out.println("path:" + path);
						// 获得文件名
						String fileName = item.getName();
						System.out.println(fileName);
						// 返回路径文件名
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
						File file = new File(path + "\\" + id + ".jpg");
						if (!file.exists()) {
							item.write(file);
							
							System.out.println("ok");
							out.print("ok");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
