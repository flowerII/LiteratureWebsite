package com.website.service;

import com.website.dao.implement.UserDaoImplement;
import com.website.enty.User;

/** 
* @author  hua'er 
* @time    2017年4月6日 
*/
public class RegisterService {
	private UserDaoImplement userDaoImplement=new UserDaoImplement();
	public boolean create(User user) throws Exception{
		return userDaoImplement.create(user);
	}
	public User find(User user) throws Exception{
		return userDaoImplement.find(user);
	}
	public User register_find_user(User user) throws Exception{
		return userDaoImplement.register_finde_user(user);
	}
}
