package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.UserDaoImpl;

public class loveArticleAction implements actionInterface{
	int userId;
	int articleId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.loveArticleByIds(userId, articleId);
		out.write(200);
		return null;
	}

}
