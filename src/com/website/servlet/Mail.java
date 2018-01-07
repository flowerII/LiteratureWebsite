package com.website.servlet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/Mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		  String email=request.getParameter("account");
		  int a = (int)(Math.random()*(9999-1000+1))+1000;
		  String s=String.valueOf(a);
		  //System.out.println(s);
		  HttpSession session2=request.getSession();
		  session2.setAttribute("Validcode", s);
		  
		  try {
			      String from = "1479676948@qq.com";
			      String host = "smtp.qq.com";  
			      Properties properties = System.getProperties();
			      properties.setProperty("mail.smtp.host", host);
			      properties.put("mail.smtp.auth", "true");			      MailSSLSocketFactory sf;
				try {
					sf = new MailSSLSocketFactory();
					sf.setTrustAllHosts(true);
				      properties.put("mail.smtp.ssl.enable", "true");
				      properties.put("mail.smtp.ssl.socketFactory", sf);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			      			      
				Session session = Session.getInstance(properties,new Authenticator(){
				    public PasswordAuthentication getPasswordAuthentication()
				    {
				         return new PasswordAuthentication("1479676948@qq.com", "nbfkjcrkculajgbb"); //�������ʼ��û���������
				    }
				});
			    try{
			         MimeMessage message = new MimeMessage(session);
			         message.setFrom(new InternetAddress(from));
			         message.addRecipient(Message.RecipientType.TO,
			                                  new InternetAddress(email));
			         message.setSubject("您正在注册五邑大学文学交流网站！！！");
			         message.setText("验证码："+s+",请勿泄漏，如非本人操作请忽略！！");
			         Transport.send(message);
			         System.out.println("Sent message successfully....from w3cschool.cc");
			      }catch (MessagingException mex) {
			         mex.printStackTrace();
			      }
			} catch (Exception e1) {
			e1.printStackTrace();
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
