package com.website.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.enty.Activity;
import com.website.service.IndexService;

/**
 * Servlet implementation class AddActivity
 */
@WebServlet("/AddActivity")
public class AddActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		String b=null;
		Object user_role=session.getAttribute("user_role");
		Integer i=0;
		if(user_role!=null){
			i=Integer.parseInt(String.valueOf(user_role));
		}
		if(i!=5){
			b="您没有此权限！！";			
		}else{
			String activity_name=request.getParameter("activity_name");
			String activity_starttime=request.getParameter("activity_starttime");
			String activity_endtime=request.getParameter("activity_endtime");
			String activity_description=request.getParameter("activity_description");
			Activity a=new Activity();
			a.setActivity_name(activity_name);
			a.setActivity_starttime(activity_starttime);
			a.setActivity_endtime(activity_endtime);
			Date time = new Date();
			@SuppressWarnings("deprecation")
			String str = String.valueOf(time.getYear()+1900);
			a.setActivity_year(str);
			a.setActivity_description(activity_description);
			IndexService indexservice=new IndexService();
			try {
				indexservice.inserActivity(a);
			    b="ok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		response.getWriter().write(b);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
