package com.kdhb.dao;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kdhb.util.Global;

public class ImageDAO extends BaseDAO {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String storeImage = "INSERT INTO user(image) VALUES (?)";
	private static final String getImage = "select image from user where id=?";

	public ImageDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context.lookup(Global.datasource_url);
		} catch (NamingException e2) {
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
		}
		return conn;
	}

	public boolean storeImg(String strFile) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		boolean written = false;

		File file = new File(strFile);
		FileInputStream fis = new FileInputStream(file);

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(storeImage);
			ps.setBinaryStream(1, fis, (int) file.length());
			ps.executeUpdate();

			written = true;
		} catch (SQLException e) {
			written = false;
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} finally {
			fis.close();
			// close db con
			releaseSource(conn, pstmt, rst);
		}

		return written;
	}

	public Image getImageFile(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		boolean written = false;

		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(getImage);
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("Image not found");
			return null;
		}
		Blob blob = (Blob) rs.getBlob("image");
		BufferedInputStream inputImage = new BufferedInputStream(blob.getBinaryStream());
		BufferedImage image = null;
		image = ImageIO.read(inputImage);
		inputImage.close();
		return image;
		
		

	}

}
