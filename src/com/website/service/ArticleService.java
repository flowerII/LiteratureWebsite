package com.website.service;

import java.util.List;

import com.website.dao.implement.ArticleDaoImplement;
import com.website.enty.Article;

public class ArticleService {
	private ArticleDaoImplement articleDaoImplement=new ArticleDaoImplement();
	public boolean addArticle(Article a) throws Exception{
		return articleDaoImplement.addArticle(a);
	}	
	public int getArticlesPageNum(int activity_id) throws Exception{
		return articleDaoImplement.getArticlesPageNum(activity_id);
	}
	public List<Article> getArticleByActivityIDAndPage(int n, int activity_id) throws Exception{
		return articleDaoImplement.getArticleByActivityIDAndPage(n, activity_id);
	}
	
	public void deleteArticleById(int article_id) throws Exception{
		articleDaoImplement.deleteArticleById(article_id);
		return ;
	}
	
	public boolean SetArticlePrize(int article_id, int prize) throws Exception{
		return articleDaoImplement.SetArticlePrize(article_id, prize);
	}
	
	public Article getArticleByArticleId(int article_id) throws Exception{
		return articleDaoImplement.getArticleByArticleId(article_id);
	}
	public List<Article> getArticleByActivityId(int activity_id) throws Exception{
		return articleDaoImplement.getArticleByActivityId(activity_id);
	}

}
