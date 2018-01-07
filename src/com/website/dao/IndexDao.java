package com.website.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.enty.Activity;
import com.website.enty.News;

/** 
* @author  hua'er 
* @time    2017年4月17日 
*/
public interface IndexDao {
	public abstract JsonArray getNews()throws Exception;
	public abstract JsonArray getActivities()throws Exception;
	public abstract JsonArray getActivities_Byyear(String year)throws Exception;
	public abstract JsonArray getActivities_years()throws Exception;
	public abstract boolean inserActivity(Activity a)throws Exception;
	public abstract boolean publishActivity(int activity_id)throws Exception;
	public abstract int getActivityStatus(int activity_id)throws Exception;
	public abstract boolean deleteActivity(int id)throws Exception;
	public abstract boolean inserNews(News a)throws Exception;
	public abstract JsonArray getNews_years()throws Exception;
	public abstract JsonObject getActivityById(int activity_id)throws Exception;
	public abstract Activity getActivityBy_Id(int activity_id)throws Exception;
	public abstract JsonArray getAll_news()throws Exception;
	public abstract boolean deleteNewByID(int new_id) throws Exception;
	public abstract News getNewsContent(int new_id) throws Exception;
	public abstract JsonArray getActivityPublishYear() throws Exception;
	public abstract JsonArray getActivityPublish(String activity_year) throws Exception;	
}
