package com.website.dao;

import com.google.gson.JsonArray;
import com.website.enty.Comments;

public interface CommentDao{
	//添加评论
	public abstract boolean addComment(Comments comment)throws Exception;
	//获得最新评论
	public abstract JsonArray getComments(int ajaxnum,int comment_type,int all_id)throws Exception;
	//获得最热评论
	public abstract JsonArray getCommentsHot(int ajaxnum, int comment_type, int all_id)throws Exception;
	//获得评论总数
	public abstract int getCommentsNum(int comment_type,int all_id)throws Exception;

}
