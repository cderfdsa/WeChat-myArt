package com.qk.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 此表为数据库中USER的model类
 * @author dyb1296
 *
 */
public class User {
	/** 序号，设计为自增 */
	private int id;
	/** 用户名，采用微信读取的用户名 */
	private String username;
	/** 创建时间   */
	private Date createTime;
	/** 最后登录时间  */
	private Date lastLoginTime;
	/** 点赞的文章   */
	private Set<Article> loveArticleSet = new HashSet<Article>();
	/** 阅读过的文章   */
	private Set<Article> readArticleSet = new HashSet<Article>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Set<Article> getLoveArticleSet() {
		return loveArticleSet;
	}
	public void setLoveArticleSet(Set<Article> loveArticleSet) {
		this.loveArticleSet = loveArticleSet;
	}
	public Set<Article> getReadArticleSet() {
		return readArticleSet;
	}
	public void setReadArticleSet(Set<Article> readArticleSet) {
		this.readArticleSet = readArticleSet;
	}
	/** 无参构造方法   */
	public User() {
		// TODO Auto-generated constructor stub
	}
	/** 有参构造方法，传入username */
	public User(String username){
		this.setUsername(username);
	}
}
