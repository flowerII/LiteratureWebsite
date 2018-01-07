package com.website.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.IndexDao;
import com.website.enty.Activity;
import com.website.enty.News;
import com.website.util.DbConnect;

/** 
* @author  hua'er 
* @time    2017年4月17日 
*/
public class IndexDaoImplement implements IndexDao {
	public static String get_news="select * from website_news order by new_id desc limit 5";
	public static String get_activities="select * from website_activities where activity_surepublish=0 order by activity_id desc limit 5";
	public static String get_activities_years="select distinct activity_year from website_activities";
	public static String get_news_years="select distinct new_time from website_news";
	public static String get_activities_by_years="select activity_id,activity_name from website_activities where activity_year =? and activity_surepublish = 0";
	public static String get_activitiy_by_id="select * from website_activities where activity_id =?";
	public static String add_activities="insert into website_activities (activity_name,activity_starttime,activity_endtime,activity_year,activity_description) values(?,?,?,?,?)";
	public static String add_news="insert into website_news (new_name,new_time,new_content) values(?,?,?)";
	public static String getAll_news="select new_id,new_name from website_news";
	public static String getNewsContent="select * from website_news where new_id = ?";
	public static String publishActivity="update website_activities set activity_surepublish = 1 where activity_id = ?";
	public static String getActivityStatus="select activity_surepublish from website_activities where activity_id = ?";
	public static String getActivityPublishYear="select activity_year from website_activities where activity_surepublish = 1";
	public static String getActivityPublish="select activity_name,activity_id from website_activities where activity_year = ?";
	
	@Override
	public JsonArray getNews() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_news);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("new_id", rs.getString("new_id"));
				obj.addProperty("user_id", rs.getString("user_id"));
				obj.addProperty("new_name", rs.getString("new_name"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public JsonArray getActivities() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_activities);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("activity_id", rs.getString("activity_id"));
				obj.addProperty("activity_name", rs.getString("activity_name"));
				obj.addProperty("activity_starttime", rs.getString("activity_starttime"));
				obj.addProperty("activity_endtime", rs.getString("activity_endtime"));
				obj.addProperty("activity_year", rs.getString("activity_year"));
				obj.addProperty("activity_description", rs.getString("activity_description"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public boolean inserActivity(Activity a) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(add_activities);
			prestmt.setString(1, a.getActivity_name());
			prestmt.setString(2, a.getActivity_starttime().toString());
			prestmt.setString(3, a.getActivity_endtime().toString());
			prestmt.setString(4, a.getActivity_year().toString());
			prestmt.setString(5, a.getActivity_description());
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	@Override
	public boolean inserNews(News a) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(add_news);
			prestmt.setString(1, a.getNew_name());
			prestmt.setString(2, a.getNew_time());
			prestmt.setString(3, a.getNew_content());
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	@Override
	public JsonArray getActivities_years() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_activities_years);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("activity_year", rs.getString("activity_year"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public JsonArray getActivities_Byyear(String year) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_activities_by_years);
			prestmt.setString(1, year);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("activity_id", rs.getString("activity_id"));
				obj.addProperty("activity_name", rs.getString("activity_name"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public boolean deleteActivity(int id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		String deleteSql="delete from website_activities where activity_id= ?";
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(deleteSql);
			prestmt.setInt(1, id);
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	@Override
	public JsonArray getNews_years() throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_news_years);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("new_time", rs.getString("new_time"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public JsonObject getActivityById(int activity_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonObject json=new JsonObject();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_activitiy_by_id);
			prestmt.setInt(1, activity_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
				json.addProperty("activity_name", rs.getString("activity_name"));
				json.addProperty("activity_starttime",rs.getString("activity_starttime"));
				json.addProperty("activity_endtime",rs.getString("activity_endtime"));
				json.addProperty("activity_description",rs.getString("activity_description"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return json;
	}

	@Override
	public Activity getActivityBy_Id(int activity_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Activity activity=new Activity();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_activitiy_by_id);
			prestmt.setInt(1, activity_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
				activity.setActivity_name( rs.getString("activity_name"));
				activity.setActivity_starttime(rs.getString("activity_starttime"));
				activity.setActivity_endtime(rs.getString("activity_endtime"));
				activity.setActivity_description(rs.getString("activity_description"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return activity;
	}
	
	@Override
	public JsonArray getAll_news() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(getAll_news);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("new_id", rs.getString("new_id"));
				obj.addProperty("new_name", rs.getString("new_name"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	public boolean deleteNewByID(int new_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		String deleteSql="delete from website_news where new_id= ?";
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(deleteSql);
			prestmt.setInt(1, new_id);
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	public News getNewsContent(int new_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		News jsonObject =new News();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(getNewsContent);
			prestmt.setInt(1, new_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
/*				jsonObject.addProperty("new_id", rs.getString("new_id"));
				jsonObject.addProperty("new_name", rs.getString("new_name"));
				jsonObject.addProperty("user_id", rs.getString("user_id"));
				jsonObject.addProperty("new_time", rs.getString("new_time"));
				jsonObject.addProperty("new_content", rs.getString("new_content"));
*/				
				jsonObject.setNew_id(rs.getInt("new_id"));
				jsonObject.setNew_name(rs.getString("new_name"));
				jsonObject.setUser_id(rs.getInt("user_id"));
				jsonObject.setNew_time(rs.getString("new_time"));
				jsonObject.setNew_content(rs.getString("new_content"));
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return jsonObject;
	}

	@Override
	public boolean publishActivity(int activity_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		boolean flag=false;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(publishActivity);
			prestmt.setInt(1, activity_id);
			int n=prestmt.executeUpdate();
			if(n==1){
				flag=true;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	@Override
	public int getActivityStatus(int activity_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		int status=0;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(getActivityStatus);
			prestmt.setInt(1, activity_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
				status=rs.getInt("activity_surepublish");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return status;
	}

	@Override
	public JsonArray getActivityPublishYear() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(getActivityPublishYear);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("activity_year", rs.getString("activity_year"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	@Override
	public JsonArray getActivityPublish(String activity_year) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(getActivityPublish);
			prestmt.setString(1, activity_year);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("activity_name", rs.getString("activity_name"));
				obj.addProperty("activity_id", rs.getString("activity_id"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}


}
