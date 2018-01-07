package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.enty.News;
import com.website.service.IndexService;

/**
 * Servlet implementation class AddNews
 */
@WebServlet("/AddNews")
public class AddNews extends HttpServlet {
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
		if(user_role!=null){
			i=Integer.parseInt(String.valueOf(user_role));
		}		
		String back="";
		if(i!=5){
			back="你没有权限！！";
		}else if (i==5){
			String new_name=request.getParameter("new_name");
			String new_time=request.getParameter("new_time");
			String new_content=request.getParameter("new_content");
			News a=new News();
			a.setNew_name(new_name);
			a.setNew_time(new_time);
			a.setNew_content(new_content);
			IndexService indexservice=new IndexService();
			try {
				indexservice.inserNews(a);
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
