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
 * Servlet implementation class PublishActivity
 */
@WebServlet("/PublishActivity")
public class PublishActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String back=null;
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		Object user_role1 = session.getAttribute("user_role");
		if(user_role1 == null){
			back ="你没有登录";
		}else{
			Integer user_role = Integer.parseInt(String.valueOf(user_role1));
			if(user_role != 5){
				back = "你没有权限";
			}else{
				String activity_id=String.valueOf(session.getAttribute("activity_id"));
				IndexService indexService=new IndexService();
				try {
					boolean flag=indexService.publishActivity(Integer.parseInt(activity_id));
					if(flag){
						back="ok";
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().write(back);
			}
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
