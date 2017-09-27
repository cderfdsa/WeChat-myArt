package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.daoImpl.ArticleDaoImpl;
import com.qk.model.User;

public class getLoversByArticleIdAction {
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		List<User> list = articleDaoImpl.getUsersLoveByArticleId(id);
		out.write(articleDaoImpl.returnUserJson(list));
 		return null;
	}
}
