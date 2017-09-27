package com.qk.dao;

import java.util.Date;
import java.util.List;

import com.qk.model.Article;
import com.qk.model.User;

public interface UserDao {
	public int addUser(User user);
	public void updateUser(String username);
	public List<User> getAllUsers();
	public List<Article> getArticlesReadById(int id);
	public int getArticlesReadByIdCount(int id);
	public List<Article> getArticlesLoveById(int id);
	public int getArticlesLoveByIdCount(int id);
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public void readArticleByIds(int userId,int articleId);
	public void loveArticleByIds(int userId,int articleId);
	public int getUserIdByUsername(String username);
	public boolean existsByUsername(String username);
	public Date[] getTimeByUsername(String username);
}
