package com.website.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.implement.IndexDaoImplement;
import com.website.enty.Activity;
import com.website.enty.News;

/** 
* @author  hua'er 
* @time    2017年4月17日 
*/
public class IndexService {
	private IndexDaoImplement indexDaoImplement=new IndexDaoImplement();
	public JsonArray getNews() throws Exception{
		JsonArray news=indexDaoImplement.getNews();
		return news;
	}
	public JsonArray getActivities() throws Exception{
		JsonArray news=indexDaoImplement.getActivities();
		return news;
	}
	public boolean inserActivity(Activity a) throws Exception{
		boolean flag=indexDaoImplement.inserActivity(a);
		return flag;
	}
	public boolean inserNews(News a) throws Exception{
		boolean flag=indexDaoImplement.inserNews(a);
		return flag;
	}
	public JsonArray getActivitiesYears() throws Exception{
		JsonArray years=indexDaoImplement.getActivities_years();
		return years;
	}
	public JsonArray getActivities_Byyear(String year) throws Exception{
		JsonArray activities=indexDaoImplement.getActivities_Byyear(year);
		return activities;
	}
	public boolean deleteActivity(int id) throws Exception{
		return indexDaoImplement.deleteActivity(id);
	}
	public JsonArray getNews_years() throws Exception{
		return indexDaoImplement.getNews_years();
	}
	public JsonObject getActivityById(int activity_id) throws Exception{
		return indexDaoImplement.getActivityById(activity_id);
	}
	public Activity getActivityBy_Id(int activity_id) throws Exception{
		return indexDaoImplement.getActivityBy_Id(activity_id);
	}
	public JsonArray getAll_news() throws Exception {
		return indexDaoImplement.getAll_news();
	}
	public boolean deleteNewByID(int new_id) throws Exception{
		return indexDaoImplement.deleteNewByID(new_id);
	}
	public News getNewsContent(int new_id) throws Exception{
		return indexDaoImplement.getNewsContent(new_id);
	}
	public boolean publishActivity(int activity_id) throws Exception{
		return indexDaoImplement.publishActivity(activity_id);
	}
	public int getActivityStatus(int activity_id) throws Exception{
		return indexDaoImplement.getActivityStatus(activity_id);
	}
	public JsonArray getActivityPublishYear() throws Exception {
		return indexDaoImplement.getActivityPublishYear();
	}
	public JsonArray getActivityPublish(String activity_year) throws Exception {
		return indexDaoImplement.getActivityPublish(activity_year);
	}
}
