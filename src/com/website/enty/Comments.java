package com.website.enty;

import java.sql.Timestamp;

public class Comments {
	
	private int comment_id;
	private int user_id;
	private int comment_type;
	private String comment_content;
	private Timestamp comment_time;
	private int  all_id;
	
	public Comments(){
		this.comment_time=new Timestamp(System.currentTimeMillis());
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getComment_type() {
		return comment_type;
	}
	public void setComment_type(int comment_type) {
		this.comment_type = comment_type;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getAll_id() {
		return all_id;
	}
	public void setAll_id(int all_id) {
		this.all_id = all_id;
	}
	public Timestamp getComment_time() {
		return comment_time;
	}
	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}

}
