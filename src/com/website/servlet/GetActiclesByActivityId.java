package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.implement.ArticleDaoImplement;

/**
 * Servlet implementation class GetActiclesByActivityId
 */
@WebServlet("/GetActiclesByActivityId")
public class GetActiclesByActivityId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int n;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a=request.getParameter("page");
		request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=utf-8");
		int num=Integer.parseInt(a);
		ArticleDaoImplement articleDaoImplent=new ArticleDaoImplement();
		JsonObject JsonBack=new JsonObject();
		int activity_id=Integer.parseInt(request.getParameter("activity_id"));
		try {
			n=articleDaoImplent.getArticlesPageNum(activity_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonArray jsonarray=new JsonArray();
		try {
			jsonarray=articleDaoImplent.getArticle((num-1)*10,activity_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonBack.addProperty("num",n);
		JsonBack.add("articles", jsonarray);
		
		//System.out.println(n);
		//System.out.println(jsonarray.toString());
		
		response.getWriter().write(JsonBack.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
