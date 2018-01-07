package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.service.IndexService;

/**
 * Servlet implementation class DeleteActivity
 */
@WebServlet("/DeleteActivity")
public class DeleteActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		Object user_role=session.getAttribute("user_role");
		System.out.println(user_role);
		Integer i=0;
		if(user_role!=null){
			i=Integer.parseInt(String.valueOf(user_role));
		}		
		String back="";
		if(i!=5){
			back="你没有权限删除！！";
			System.out.println(back);			
		}else{
			 IndexService indexService=new IndexService();
			 int id=Integer.parseInt(request.getParameter("activity_id"));
			 try {
				indexService.deleteActivity(id);
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
