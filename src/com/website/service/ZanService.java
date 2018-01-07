package com.website.service;

import com.google.gson.JsonArray;
import com.website.dao.ZanDao;
import com.website.dao.implement.ZanDaoImplement;
import com.website.enty.Zan;

public class ZanService {
	
	ZanDao zanDao=new ZanDaoImplement();
	
	public boolean addZan(int user_id,int comment_id) throws Exception{
		return zanDao.addZan(user_id,comment_id);
	}
	
	public JsonArray getZan(int user_id) throws Exception{
		return zanDao.getZan(user_id);
	}
	public boolean findZan(int user_id, int comment_id) throws Exception{
		return zanDao.findZan(user_id, comment_id);
	}

}
