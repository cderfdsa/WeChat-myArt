package com.qk.dao;

import java.util.List;

import com.qk.model.Article;
import com.qk.model.User;

public interface ArticleDao {
	public Article getArticleContent(int id);
	public List<Article> getAllArticles();
	public List<Article> getArticlesByType(int type);
	public List<User> getUsersReadByArticleId(int id);
	public List<User> getUsersLoveByArticleId(int id);
	public List<Article> getDivArticlePage(int pageSize,int pageIndex);
	public List<Article> getDivArticlePageByType(int type,int pageSize,int pageIndex);
	public String returnArticleJson(List<Article> list);
	public String returnUserJson(List<User> list);
	//**
	public int getArticleReadCountById(int id);
	public void updateArticleReadCountById(int count,int id);
	//**
	public int getArticleLoveCountById(int id);
	public void updateArticleLoveCountById(int count,int id);
	
	public boolean isLovedById(int userId,int articleId);
	public boolean isReadById(int userId,int articleId);
	public int getArticlesCountByType(int type);
	public int getArticlesCounts();
}
