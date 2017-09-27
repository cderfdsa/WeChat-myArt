package com.qk.mapper;

import java.util.Date;

import com.qk.model.User;

public class UserMapper {
	private User user;
	/** 序号，设计为自增 */
	private int id;
	/** 用户名，采用微信读取的用户名 */
	private String username;
	/** 创建时间   */
	private Date createTime;
	/** 最后登录时间  */
	private Date lastLoginTime;
	public User getUser() {
		return user;
	}
	public void setUser(String username,Date createTime,Date lastLoginTime) {
		User user = new User();
		this.username = username;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		user.setUsername(username);
		user.setCreateTime(createTime);
		user.setLastLoginTime(lastLoginTime);
		this.user = user;
	}
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
}
