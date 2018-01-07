package com.website.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.ZanDao;
import com.website.enty.Zan;
import com.website.util.DbConnect;

//点赞
public class ZanDaoImplement implements ZanDao{
	int num;
	
	//增加
	public static String add_zan="insert into website_zan (user_id,comment_id) values(?,?)";
	//
	public static String get_zan="select * from  website_zan where user_id=?";
	//
	public static String find_zan="select * from  website_zan where user_id=? and comment_id=?";

	//增加
	public boolean addZan(int user_id,int comment_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(add_zan);
			prestmt.setInt(1, user_id);
			prestmt.setInt(2, comment_id);
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
	public JsonArray getZan(int user_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_zan);
			prestmt.setInt(1, user_id);
			rs=prestmt.executeQuery();
			rs.last();
			num=rs.getRow();
			rs.beforeFirst();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("zan_id", rs.getString("zan_id"));
				obj.addProperty("comment_id", rs.getString("comment_id"));
				obj.addProperty("user_id", rs.getString("user_id"));
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
	public boolean findZan(int user_id, int comment_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(find_zan);
			prestmt.setInt(1, user_id);
			prestmt.setInt(2, comment_id);
			rs=prestmt.executeQuery();
			if(rs.next()){
				flag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

}
