package com.website.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.UserDao;
import com.website.enty.User;
import com.website.util.DbConnect;

/** 
* @author  hua'er 
* @time    2017年4月7日 
*/
public class UserDaoImplement implements UserDao {
	
	public static final String Fields="USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_DESCRIPTION,USER_ROLE";
	public static final String get_user_info="select user_name,user_sno,user_description from website_user where user_id = ?";
	public static String insert_user="insert into website_user("+Fields+")"+"values(?,?,?,?,0)";
	public static String find_user="select * from website_user where USER_ACCOUNT = ? and USER_PASSWORD = ?";
	public static String find_user_register="select * from website_user where USER_ACCOUNT = ?";
	public static String alter_user_role="update website_user set user_role = ? where USER_Id = ?";
	public static String all_user="select * from website_user where user_role != 5 order by user_role desc";
	public static String alter_user_info="update website_user set user_name = ?,user_description = ? ,user_sno=? where USER_Id= ?";

	@Override
	public boolean create(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		boolean f=false;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(insert_user);
			prestmt.setString(1, user.getUser_account());
			prestmt.setString(2, user.getUser_password());
			prestmt.setString(3, user.getUser_account());
			prestmt.setString(4, user.getUser_description());
			prestmt.executeUpdate();
			f=true;
		}catch(Exception e){
			e.printStackTrace();
			f=false;
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return f;
	}

	@Override
	public User find(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		User user2=new User();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(find_user);
			prestmt.setString(1, user.getUser_account());
			prestmt.setString(2, user.getUser_password());
			rs=prestmt.executeQuery();
			if(rs.next()){
				user2.setUser_id(rs.getInt("user_id"));
				user2.setUser_account(rs.getString("user_account"));
				user2.setUser_password(rs.getString("user_password"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setUser_description(rs.getString("user_description"));
				user2.setUser_role(rs.getInt("user_role"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return user2;
	}
	
	public User register_finde_user(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		User user2=new User();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(find_user_register);
			prestmt.setString(1, user.getUser_account());
			rs=prestmt.executeQuery();
			if(rs.next()){
				user2.setUser_account(rs.getString("user_account"));
				user2.setUser_password(rs.getString("user_password"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setUser_description(rs.getString("user_description"));
				user2.setUser_role(rs.getInt("user_role"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return user2;
	}

	@Override
	public List<User> userlist() throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		List<User> array=new ArrayList<User>();
		User user=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(all_user);
			rs=prestmt.executeQuery();
			while(rs.next()){
				user=new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_account(rs.getString("user_account"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_description(rs.getString("user_description"));
				user.setUser_role(rs.getInt("user_role"));
				array.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		//System.out.println(array.toString());
		return array;
	}

	@Override
	public JsonArray getAllUsers() throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(all_user);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("user_id", rs.getString("user_id"));
				obj.addProperty("user_account", rs.getString("user_account"));
				obj.addProperty("user_name", rs.getString("user_name"));
				obj.addProperty("user_role", rs.getString("user_role"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;	}

	@Override
	public boolean alteruserRole(User user) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		boolean flag=false;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(alter_user_role);
			prestmt.setInt(1, user.getUser_role());
			prestmt.setInt(2, user.getUser_id());
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
	public boolean alteruserinfo(String user_name,String user_description,int user_sno,int user_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		boolean flag=false;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(alter_user_info);
			prestmt.setString(1, user_name);
			prestmt.setString(2, user_description);
			prestmt.setInt(3, user_sno);
			prestmt.setInt(4, user_id);
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
	public JsonObject getUserInfo(int user_id) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonObject json=new JsonObject();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_user_info);
			prestmt.setInt(1, user_id);
			rs=prestmt.executeQuery();
			if(rs.next()){
				json.addProperty("user_name", rs.getString("user_name"));
				json.addProperty("user_sno", rs.getString("user_sno"));			
				json.addProperty("user_description", rs.getString("user_description"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return json;
	}

}
