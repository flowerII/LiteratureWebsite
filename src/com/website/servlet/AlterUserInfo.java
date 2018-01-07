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
 * Servlet implementation class AlterUserInfo
 */
@WebServlet("/AlterUserInfo")
public class AlterUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterUserInfo() {
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
		HttpSession session=request.getSession();
		Object user_id=session.getAttribute("user_id");
		String back=null;
		if(user_id!=null){
			String user_name=request.getParameter("user_name");
			String user_sno=request.getParameter("user_sno");
			String user_description=request.getParameter("user_description");
			UserService userService=new UserService();
			boolean flag=false;
			try {
				flag=userService.alteruserinfo(user_name, user_description,Integer.parseInt(user_sno),Integer.parseInt(String.valueOf(user_id)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag){
				back="ok";
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
