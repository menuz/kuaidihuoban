package com.kdhb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * 获得ip
 * @author Administrator
 *
 */
public class Listip {
	public static String getWebIp(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url
			.openConnection().getInputStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			webContent = sb.toString();
			System.out.println(webContent);
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			webContent = webContent.substring(start, end);

			return webContent;

		} catch (Exception e) {
			e.printStackTrace();
			return "error open url:" + strUrl;

		}
	}
}
