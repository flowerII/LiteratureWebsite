package com.website.dao;

import java.util.List;

import com.google.gson.JsonArray;
import com.website.enty.Article;

public interface ArticleDao {
	public abstract boolean addArticle(Article a)throws Exception;
	public abstract int  getArticlesPageNum(int activity_id)throws Exception;
	public abstract void  deleteArticleById(int article_id)throws Exception;
	public abstract JsonArray getArticle(int n,int activity_id)throws Exception;
	public abstract List<Article> getArticleByActivityIDAndPage(int n,int activity_id)throws Exception;
	public abstract boolean SetArticlePrize(int article_id,int prize)throws Exception;
	public abstract Article getArticleByArticleId(int article_id)throws Exception;
	public abstract List<Article> getArticleByActivityId(int activity_id)throws Exception;
}
