package com.website.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.website.dao.ArticleDao;
import com.website.enty.Article;
import com.website.util.DbConnect;

public class ArticleDaoImplement implements ArticleDao {
	int count;
	int num;
	public static String add_article="insert into website_articles (article_id,user_id,activity_id,article_name,article_url) values(?,?,?,?,?)";
	public static String all_articles_by_activityId="select * from website_articles where activity_id=?";
	public static String delete_article_by_articleId="delete from website_articles where article_id=?";
	public static String get_articles_limit="select * from website_articles where activity_id=? order by article_id asc limit ?,?";
	public static String set_articles_prize="update website_articles set article_prize = ? where article_id=?";
	public static String get_article_by_article_id="select article_name,article_url,website_user.user_id,user_name from website_user,website_articles where website_user.user_id=website_articles.user_id and article_id=?";
	public static String get_article_by_activity_id="select article_id,article_name,article_prize,user_name from website_articles,website_user where website_user.user_id=website_articles.user_id and activity_id=? and article_prize !=0 order by article_prize asc";

	//添加稿件
	@Override
	public boolean addArticle(Article a) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(add_article);
			prestmt.setInt(1, a.getArticle_id());
			prestmt.setInt(2, a.getUser_id());
			prestmt.setInt(3, a.getActivity_id());
			prestmt.setString(4, a.getArticle_name());
			prestmt.setString(5, a.getArticle_url());
			prestmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	//计算每个活动的稿件页数
	public int getArticlesPageNum(int activity_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(all_articles_by_activityId);
			//System.out.println(activity_id);
			prestmt.setInt(1, activity_id);
			rs=prestmt.executeQuery();
			rs.last();
			count=rs.getRow();
			//System.out.println(count);
			num=(int)Math.ceil((double)count/10);
			//System.out.println(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return num;
	}

	//分页查询
	@Override
	public JsonArray getArticle(int n,int activity_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		JsonArray array=new JsonArray();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_articles_limit);
			prestmt.setInt(1, activity_id);
			prestmt.setInt(2, (n-1)*10);
			rs=prestmt.executeQuery();
			while(rs.next()){
				JsonObject obj= new JsonObject();
				obj.addProperty("article_id", rs.getString("article_id"));
				obj.addProperty("article_name", rs.getString("article_name"));
				array.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	//活动文章分页查询
	@Override
	public List<Article> getArticleByActivityIDAndPage(int n,int activity_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		int n2;
		if(n*10<count){
			n2=10;
		}else{
			n2=count-(n-1)*10;
		}
		Article article;
		List<Article> array=new ArrayList<Article>();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_articles_limit);
			prestmt.setInt(1, activity_id);
			prestmt.setInt(2, (n-1)*10);
			prestmt.setInt(3, n2);
			rs=prestmt.executeQuery();
			while(rs.next()){
				article= new Article();
				article.setArticle_id(Integer.parseInt(rs.getString("article_id")));
				article.setArticle_name(rs.getString("article_name"));
				article.setArticle_prize(rs.getInt("article_prize"));
				array.add(article);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return array;
	}

	//删除文章
	public void deleteArticleById(int article_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(delete_article_by_articleId);
			//System.out.println(article_id);
			prestmt.setInt(1, article_id);
			prestmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return ;
	}

	
	//设置文章奖项
	public boolean SetArticlePrize(int article_id, int prize) throws Exception {
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(set_articles_prize);
			prestmt.setInt(2, article_id);
			prestmt.setInt(1, prize);
			int m=prestmt.executeUpdate();
			if(m>0){
			    flag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return flag;
	}

	//查找文章
	public Article getArticleByArticleId(int article_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Article article=new Article();
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_article_by_article_id);
			prestmt.setInt(1, article_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
				article.setArticle_name(rs.getString("article_name"));
				article.setUser_id(rs.getInt("user_id"));
				article.setUser_name(rs.getString("user_name"));
				article.setArticle_url(rs.getString("article_url"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return article;
	}

	@Override
	public List<Article> getArticleByActivityId(int activity_id) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		List<Article> article=new ArrayList<Article>();
		Article ar;
		try{
			con=DbConnect.getDBconnection();
			prestmt=con.prepareStatement(get_article_by_activity_id);
			prestmt.setInt(1, activity_id);
			rs=prestmt.executeQuery();
			while(rs.next()){
				ar=new Article();
				ar.setArticle_id(rs.getInt("article_id"));
				ar.setArticle_name(rs.getString("article_name"));
				ar.setUser_name(rs.getString("user_name"));
				ar.setArticle_prize(rs.getInt("article_prize"));
				article.add(ar);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbConnect.closeDB(con, prestmt, rs);
		}
		return article;
	}

}
