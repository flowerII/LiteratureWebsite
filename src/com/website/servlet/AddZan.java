package com.website.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.enty.Zan;
import com.website.service.ZanService;

/**
 * Servlet implementation class AddZan
 */
@WebServlet("/AddZan")
public class AddZan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddZan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String comment_id=request.getParameter("comment_id");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        HttpSession session=request.getSession();
        String back="false";
        if(session.getAttribute("user_id")==null){
        	back="对不起，你尚未登录，请登录！！";
        }else{
        	int user_id=(int)session.getAttribute("user_id");
        	System.out.println(comment_id);
        	System.out.println(user_id);
        	ZanService zanService=new ZanService();
        	boolean f=false;
        	try {
				f=zanService.findZan(user_id, Integer.parseInt(comment_id));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(!f){
				try {
					boolean flag=zanService.addZan(user_id,Integer.parseInt(comment_id));
					if(flag==true){
						back="ok";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
