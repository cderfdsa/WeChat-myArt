package com.qk.model;

import java.util.Date;
import java.util.Set;
/**
 * 此表为数据库中ARTICLE的model类
 * @author dyb1296
 *
 */
public class Article {
	/** 序号，默认自增 */
	int id;
	/** 文章标题 */
	String title;
	/** 文章内容 */
	String content;
	/** 文章发布者 */
	String creater;
	/** 文章类型 */
	int type;
	/** 文章创建的日期 */
	Date createDate;
	/** 文章修改的日期 */
	Date updateDate;
	/** 赞过文章的人 ，对应实体User*/
	Set<User> lovers;
	/** 看过文章的人 ，对应实体User */
	Set<User> readers;
	/** 点赞过文章的人的数字 */
	int loversCount;
	/** 读过文章的人的数量 */
	int readersCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Set<User> getLovers() {
		return lovers;
	}
	public void setLovers(Set<User> lovers) {
		this.lovers = lovers;
	}
	public Set<User> getReaders() {
		return readers;
	}
	public void setReaders(Set<User> readers) {
		this.readers = readers;
	}
	public int getLoversCount() {
		return loversCount;
	}
	public void setLoversCount(int loversCount) {
		this.loversCount = loversCount;
	}
	public int getReadersCount() {
		return readersCount;
	}
	public void setReadersCount(int readersCount) {
		this.readersCount = readersCount;
	}
	/** 无参构造方法 */
	public Article() {
		// TODO Auto-generated constructor stub
	}
	/** 有参构造方法 */
	public Article(String title) {
		this.setTitle(title);
	}
}
