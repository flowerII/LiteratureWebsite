package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.service.CommentService;
import com.website.service.ZanService;

/**
 * Servlet implementation class GetComment10
 */
@WebServlet("/GetComment10")
public class GetComment10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetComment10() {
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
		String comment_type=request.getParameter("comment_type");
		String all_id=request.getParameter("all_id");
		int ajaxnum=Integer.parseInt(request.getParameter("ajaxnum"));
		String method=request.getParameter("method");
		System.out.println(method);
		System.out.println(ajaxnum);
		System.out.println(comment_type);
		System.out.println(all_id);
		CommentService commentService=new CommentService();
		JsonObject json=new JsonObject();
		ZanService zanService=new ZanService(); 
		HttpSession session=request.getSession();
		int user_id=Integer.parseInt(String.valueOf(session.getAttribute("user_id")!=null?session.getAttribute("user_id"):"-1"));
		System.out.println(user_id);
		JsonArray zanNum=new JsonArray();
		try {
			zanNum=zanService.getZan(user_id);
			json.add("zans", zanNum);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonArray comments=new JsonArray();
		try {
			int num=commentService.getCommentsNum(Integer.parseInt(comment_type),Integer.parseInt(all_id));
			json.addProperty("num", num);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(method.equals("hot")){
			try {
				comments=commentService.getCommentsHot(ajaxnum,Integer.parseInt(comment_type), Integer.parseInt(all_id));
				json.add("comments", comments);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			try {
				comments=commentService.getComments(ajaxnum,Integer.parseInt(comment_type), Integer.parseInt(all_id));
				json.add("comments", comments);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.getWriter().write(json.toString());
		System.out.println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
