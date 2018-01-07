package com.website.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.service.ArticleService;

/**
 * Servlet implementation class DeleteArticleByArcticleId
 */
@WebServlet("/DeleteArticleByArcticleId")
public class DeleteArticleByArcticleId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticleByArcticleId() {
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
		String article_Id=request.getParameter("article_id");
		ArticleService articleService=new ArticleService();
		String back;
		HttpSession session=request.getSession();
		Object user_role=session.getAttribute("user_role");
		System.out.println(user_role);
		Integer i=0;
		if(user_role!=null){
			i=Integer.parseInt(String.valueOf(user_role));
		}		
		if(i!=5){
			back="你没有权限删除！！";
			System.out.println(back);			
		}else{
			try {
				articleService.deleteArticleById(Integer.parseInt(article_Id));
				back="ok";
				response.getWriter().write(back);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
