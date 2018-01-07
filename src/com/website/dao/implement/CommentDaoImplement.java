package com.website.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.CommentDao;
import com.website.enty.Comments;
import com.website.util.DbConnect;

//外部评论
public class CommentDaoImplement implements CommentDao{
	
	int num;
	
	//增加
	public static String add_comment="insert into website_comments (user_id,comment_type,comment_content,comment_time,all_id) values(?,?,?,?,?)";
	//查找
	public static String get_comment="select c.comment_id , c.comment_time , c.comment_content , count(z.comment_id) ,u.user_name "+ 
									"from website_comments c left outer join website_user u on c.user_id = u.user_id left outer join website_zan z on z.comment_id=c.comment_id "+
									"where c.comment_type=?"
									+ " and c.all_id = ? group by c.comment_id order by c.comment_time desc limit ?,10 ";
	public static String get_comment_hot="select c.comment_id , c.comment_time , c.comment_content , count(z.comment_id) ,u.user_name "+ 
			"from website_comments c left outer join website_user u on c.user_id = u.user_id left outer join website_zan z on z.comment_id=c.comment_id "+
			"where c.comment_type=?"
			+ " and c.all_id = ? group by c.comment_id order by count(z.comment_id) desc limit ?,10 ";
	
	//评论总数
	public static String get_comment_num="select * from website_comments where comment_type=? and all_id = ?";
	

	//增加
	public boolean addComment(Comments comment) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(add_comment);
			prestmt.setInt(1, comment.getUser_id());
			prestmt.setInt(2, comment.getComment_type());
			prestmt.setString(3, comment.getComment_content());
			prestmt.setTimestamp(4, comment.getComment_time());
			prestmt.setInt(5, comment.getAll_id());
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	//查找
	public JsonArray getComments(int ajaxnum, int comment_type, int all_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		int n=0;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_comment);
			prestmt.setInt(1, comment_type);
			prestmt.setInt(2, all_id);
			prestmt.setInt(3, (ajaxnum)*10);
			rs=prestmt.executeQuery();
			while(rs.next()){
				//n++;
				JsonObject obj= new JsonObject();
				obj.addProperty("zan_count", rs.getString("count(z.comment_id)"));
				obj.addProperty("user_name", rs.getString("user_name"));
				obj.addProperty("comment_id", rs.getString("comment_id"));
				obj.addProperty("comment_content", rs.getString("comment_content"));
				obj.addProperty("comment_time", rs.getString("comment_time"));
				//obj.addProperty("num", n);
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	//获得评论总数
	public int getCommentsNum(int comment_type, int all_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_comment_num);
			prestmt.setInt(1, comment_type);
			prestmt.setInt(2, all_id);
			rs=prestmt.executeQuery();
			rs.last();
			num=rs.getRow();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return num;
	}

	@Override
	public JsonArray getCommentsHot(int ajaxnum, int comment_type, int all_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		int n=0;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_comment_hot);
			prestmt.setInt(1, comment_type);
			prestmt.setInt(2, all_id);
			prestmt.setInt(3, (ajaxnum)*10);
			rs=prestmt.executeQuery();
			while(rs.next()){
				//n++;
				JsonObject obj= new JsonObject();
				obj.addProperty("zan_count", rs.getString("count(z.comment_id)"));
				obj.addProperty("user_name", rs.getString("user_name"));
				obj.addProperty("comment_id", rs.getString("comment_id"));
				obj.addProperty("comment_content", rs.getString("comment_content"));
				obj.addProperty("comment_time", rs.getString("comment_time"));
				//obj.addProperty("num", n);
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
