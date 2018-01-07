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
 * Servlet implementation class SetArticlePrize
 */
@WebServlet("/SetArticlePrize")
public class SetArticlePrize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetArticlePrize() {
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
		System.out.println(article_Id);
		String article_prize=request.getParameter("article_prize");
		System.out.println(article_prize);
		ArticleService articleService=new ArticleService();
		String back;
		HttpSession session=request.getSession();
		Object user_role1 = session.getAttribute("user_role");
		if(user_role1 == null){
			back ="你没有登录";
		}else{
			Integer user_role = Integer.parseInt(String.valueOf(user_role1));
			if(user_role != 1){
				back = "你没有权限";
			}else{
				try {
					boolean flag=articleService.SetArticlePrize(Integer.parseInt(article_Id), Integer.parseInt(article_prize));
					if(flag==true){
						back="ok";
						response.getWriter().write(back);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
