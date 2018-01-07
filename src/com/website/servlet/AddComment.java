package com.website.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.website.enty.Comments;
import com.website.service.CommentService;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String comment_content=request.getParameter("comment_content");
		System.out.println(comment_content);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        HttpSession session=request.getSession();
        String back="false";
        if(session.getAttribute("user_id")==null){
        	back="对不起，你尚未登录，请登录！！";
        }else{
        	int user_id=Integer.parseInt(String.valueOf(session.getAttribute("user_id")));
        	int all_id=Integer.parseInt(String.valueOf(session.getAttribute("all_id")));
        	int comment_type=Integer.parseInt(String.valueOf(session.getAttribute("comment_type")));
        	//System.out.println(user_id);
        	//System.out.println(all_id);
        	Comments comment=new Comments();
        	comment.setComment_type(comment_type);
        	comment.setUser_id(user_id);
        	comment.setComment_content(comment_content);
        	comment.setAll_id(all_id);
        	CommentService commentService=new CommentService();
        	try {
				boolean flag=commentService.addComment(comment);
				if(flag==true){
					back="ok";
				}
				
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
