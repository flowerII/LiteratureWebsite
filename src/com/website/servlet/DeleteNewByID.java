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
 * Servlet implementation class DeletNewByID
 */
@WebServlet("/DeletNewByID")
public class DeleteNewByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String new_id = request.getParameter("new_id");
		System.out.println(new_id);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String back = null;
		HttpSession httpSession = request.getSession();
		Object user_role1 = httpSession.getAttribute("user_role");
		if(user_role1 == null){
			back ="你没有登录";
		}
		else{
			Integer user_role = Integer.parseInt(String.valueOf(user_role1));
			if(user_role != 5){
				back = "你没有权限";
			}
			else{
				IndexService indexService =new IndexService();
				Boolean flag = false;
				try {
					flag=indexService.deleteNewByID(Integer.parseInt(new_id));
					if(flag){
						back = "ok";
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		response.getWriter().write(back.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
