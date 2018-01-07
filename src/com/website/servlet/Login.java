package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.website.enty.User;
import com.website.service.RegisterService;
import com.website.util.MD5;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		String user_account=request.getParameter("user_account");
		String user_password2=request.getParameter("user_password");
		String user_password=MD5.getMd5(user_password2);
		HttpSession session=request.getSession();
		
		User user=new User();
		user.setUser_account(user_account);
		user.setUser_password(user_password);
		
		RegisterService registerService=new RegisterService();
		User user2=new User();
		JsonObject st=new JsonObject();
		try {
			user2=registerService.find(user);
			
			if(user2.getUser_name()!=null){
				st.addProperty("user_name", user2.getUser_name());
				st.addProperty("user_role", user2.getUser_role());
				session.setAttribute("user_name", user2.getUser_name());
				session.setAttribute("user_id", user2.getUser_id());
				session.setAttribute("user_role", user2.getUser_role());
			}else{
				st.addProperty("back", "err1");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(st.toString());
		response.getWriter().write(st.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
