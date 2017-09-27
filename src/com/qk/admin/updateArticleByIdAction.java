package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;

public class updateArticleByIdAction implements actionInterface{
	int id;
	String title;
	String content;
	String creater;
	int type;
	

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


	@Override
	public String execute() throws IOException {
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		boolean flag = adminDaoImpl.updateArticleById(id, title, content, type, creater);
		if(flag){
			out.write("插入成功！");
		}else{
			out.write("插入失败！请联系管理员");
		}
		return null;

	}
	

}
