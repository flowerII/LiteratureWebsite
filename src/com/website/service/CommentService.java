package com.website.service;

import com.google.gson.JsonArray;
import com.website.dao.CommentDao;
import com.website.dao.implement.CommentDaoImplement;
import com.website.enty.Comments;

public class CommentService {
	
	CommentDao commentDao=new CommentDaoImplement();
	
	public boolean addComment(Comments comment) throws Exception{
		return commentDao.addComment(comment);
	}
	
	public JsonArray getComments(int ajaxnum,int comment_type, int all_id) throws Exception {
		return commentDao.getComments(ajaxnum,comment_type, all_id);
		
	}
	public int getCommentsNum(int comment_type, int all_id) throws Exception {
		return commentDao.getCommentsNum(comment_type, all_id);
		
	}
	
	public JsonArray getCommentsHot(int ajaxnum, int comment_type, int all_id) throws Exception{
		return commentDao.getCommentsHot(ajaxnum, comment_type, all_id);
	}

}
