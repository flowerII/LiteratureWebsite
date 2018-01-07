package com.website.dao;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.enty.User;

/** 
* @author  hua'er 
* @time    2017年4月7日 
*/
public interface UserDao {
	public abstract boolean create(User user)throws Exception;
	public abstract boolean alteruserRole(User user)throws Exception;
	public abstract JsonObject getUserInfo(int user_id)throws Exception;
	public abstract User find(User user)throws Exception;
	public abstract List<User> userlist() throws Exception;
    public User register_finde_user(User user) throws Exception;
    public abstract JsonArray getAllUsers()throws Exception;
    public abstract boolean alteruserinfo(String user_name,String user_description,int user_sno,int user_id)throws Exception;
}
