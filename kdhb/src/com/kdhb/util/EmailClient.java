package com.kdhb.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 发邮件时有用
 * @author Administrator
 *
 */
public class EmailClient {

	private int port = 465;
	private boolean auth = true;
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;

	private String host = "smtp.qq.com"; // 如果你的邮箱是QQ，设置为smtp.qq.com，如果是163，设置为smtp.163.com，其它依次类推
	private String from = "570331957@qq.com"; // 你的邮箱
	private String username = "570331957@qq.com"; // 你的邮箱
	private String password = "your password"; // 你的密码

	/**
	 * 协议
	 */
	public enum Protocol {
		SMTP, SMTPS, TLS
	}

	/**
	 * 发送方服务器
	 */
	public enum HOST {
		_QQ("smtp.qq.com"), _163("smtp.163.com");
		// 其它服务器...
		private String value;

		private HOST(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 
	 * @param host
	 *            发送方服务器
	 * @param username
	 *            发送方用户名
	 * @param password
	 *            发送方密码
	 */
	public EmailClient(HOST host, String username, String password) {
		this.host = host.getValue();
		this.username = username;
		this.from = username;
		this.password = password;
	}

	/**
	 * 发送找回密码邮件
	 * @param to  收件人的email 如570331957@qq.com
	 * @param subject
	 * @param name  用户名
	 */
	public void sendFindEmail(String to, String subject, String name) {

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch (protocol) {
		case SMTPS:
			props.put("mail.smtp.ssl.enable", true);
			break;
		case TLS:
			props.put("mail.smtp.starttls", true);
			break;
		default:
			break;
		}

		Authenticator authenticator = null;
		if (auth) {
			props.put("mail.smtp.auth", true);
			authenticator = new Authenticator() {
				private PasswordAuthentication pa = new PasswordAuthentication(
						username, password);

				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}

		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(subject);
			message.setSentDate(new Date());
			//message.setText(body);

			Multipart multipart = new MimeMultipart("alternative");

			MimeBodyPart textPart = new MimeBodyPart();
			String textContent = "Hi, 你好,欢迎使用同济大学班车抢座系统";
			textPart.setText(textContent);

			MimeBodyPart htmlPart = new MimeBodyPart();
			String htmlContent = name;
			//System.out.println("htmlContent------>" + htmlContent);
			htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");

			multipart.addBodyPart(textPart);
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 发送激活邮件
	 * @param to
	 * @param subject
	 * @param name
	 */
	public void sendRegistEmail(String to, String subject, String name) {

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch (protocol) {
		case SMTPS:
			props.put("mail.smtp.ssl.enable", true);
			break;
		case TLS:
			props.put("mail.smtp.starttls", true);
			break;
		default:
			break;
		}

		Authenticator authenticator = null;
		if (auth) {
			props.put("mail.smtp.auth", true);
			authenticator = new Authenticator() {
				private PasswordAuthentication pa = new PasswordAuthentication(
						username, password);

				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}

		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(subject);
			message.setSentDate(new Date());
			// message.setText(body);

			Multipart multipart = new MimeMultipart("alternative");

			MimeBodyPart textPart = new MimeBodyPart();
			String textContent = "亲,欢迎使用同济快递伙伴!!";
			textPart.setText(textContent);

			MimeBodyPart htmlPart = new MimeBodyPart();
			String htmlContent = "<html><a href='http://"+Global.server_ip+":"+Global.server_port+"/"+Global.project_name+"/ActiveUser?username="
					+ name + "'" + ">欢迎使用同济快递伙伴,请点击此处激活您的账户</a></html>";
			//System.out.println("htmlContent------>" + htmlContent);
			htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");

			multipart.addBodyPart(textPart);
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

}
