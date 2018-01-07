package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.service.UserService;

/**
 * Servlet implementation class AlterUserRole
 */
@WebServlet("/AlterUserRole")
public class AlterUserRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
		Object user_role=session.getAttribute("user_role");
		System.out.println(user_role);
		Integer i=0;
		String back="";
		if(user_role!=null){
			i=Integer.parseInt(String.valueOf(user_role));
		}else{
			back="您没有此权限！！";
		}
		if(i==5){
			String user_id1=request.getParameter("user_id");
			String user_role1=request.getParameter("user_role");
			int user_id=Integer.parseInt(user_id1);
			int user_role2=Integer.parseInt(user_role1);
			com.website.enty.User user=new com.website.enty.User();
			user.setUser_id(user_id);
			user.setUser_role(user_role2);
			UserService userService=new UserService();
			try {
				userService.alteruserRole(user);
				back="ok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.getWriter().write(back);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
