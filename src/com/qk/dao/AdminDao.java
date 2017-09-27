package com.qk.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.qk.model.Admin;
import com.qk.model.Article;

public interface AdminDao {
	public void addAdmin(Admin admin);
	public void delAdminById(int id);
	public boolean delArticleById(int id);
	public boolean exists(String username,String password);
	public boolean isLogin(HttpSession session);
	public boolean insert(String title,String content,int type,String creater);
	public String getUsername(HttpSession session);
	public int getArticlesCount();
	public List<Article> getAdminDivArticlePage(int pageIndex,int pageSize);
	public List<Article> getAdminDivArticlePageByType(int type,int pageIndex,int pageSize);
	public String returnViewAerticleString(int pageIndex, int pageSize);
	public String returnViewAerticleByTypeString(int type,int pageIndex, int pageSize);
	public void changeLoversCount(int id,int newCount);
	public void changeReadersCount(int id,int newCount);
	public Article getArticleContentById(int id);
	public boolean updateArticleById(int id,String title,String content,int type,String creater);
	public int getArticlesByType(int type);
	public String[] getStatusAndVersion(int type);
}
