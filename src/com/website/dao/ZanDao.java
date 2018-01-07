package com.website.dao;

import com.google.gson.JsonArray;

public interface ZanDao{
	//添加赞
	public abstract boolean addZan(int user_id,int comment_id)throws Exception;
	//
	public abstract boolean findZan(int user_id,int comment_id)throws Exception;
	
	public abstract JsonArray getZan(int user_id)throws Exception;

}
