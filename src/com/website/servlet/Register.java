package com.website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.enty.User;
import com.website.service.RegisterService;
import com.website.util.MD5;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String st=null;
		String Validcode=String.valueOf(session.getAttribute("Validcode"));
		//System.out.println(Validcode);
		String code=request.getParameter("code");
		//System.out.println(code);
		if(!code.equals(Validcode)){
			st="err2";
		}else{
			String user_account=request.getParameter("user_account");
			String user_password2=request.getParameter("user_password");
			String user_password=MD5.getMd5(user_password2);

			User user=new User();
			user.setUser_account(user_account);
			user.setUser_password(user_password);
			
			
			RegisterService registerService=new RegisterService();
			User user2=null;
			boolean flag=false;
			
			try {
				user2=registerService.register_find_user(user);

				if(user2.getUser_account()==null){
					flag=registerService.create(user);
					if(flag==false){
						st="false";
					}else{
						st="ok";
					}
				}else{
					st="err1";
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
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
