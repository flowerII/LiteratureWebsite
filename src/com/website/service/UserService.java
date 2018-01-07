package com.website.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.implement.UserDaoImplement;
import com.website.enty.User;

/** 
* @author  hua'er 
* @time    2017年4月21日 
*/
public class UserService {
	private UserDaoImplement userDaoImplement=new UserDaoImplement();
	public List<User> userlist() throws Exception{
		return userDaoImplement.userlist();
	}

	public JsonArray getUsers() throws Exception{
		JsonArray all_users=userDaoImplement.getAllUsers();
		return all_users;
	}
	public boolean alteruserRole(User user) throws Exception{
		boolean flag=userDaoImplement.alteruserRole(user);
		return flag;
	}
	public boolean alteruserinfo(String user_name,String user_description,int user_sno,int user_id) throws Exception {
		return userDaoImplement.alteruserinfo(user_name, user_description,user_sno, user_id);
	}
	
	public JsonObject getUserInfo(int user_id) throws Exception{
		return userDaoImplement.getUserInfo(user_id);
	}
}
