package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;
import com.qk.model.Article;

import net.sf.json.JSONObject;

public class getSingleArticleByIdAction implements actionInterface{
	int id;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws IOException {
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
 		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		Article article = adminDaoImpl.getArticleContentById(id);
		JSONObject obj = new JSONObject();
		obj.put("id", article.getId());
		obj.put("title", article.getTitle());
		obj.put("type", article.getType());
		obj.put("content", article.getContent());
		obj.put("creater", article.getCreater());
		obj.put("createDate", article.getCreateDate());
		obj.put("updateDate", article.getUpdateDate());
		System.out.println(obj.toString());
 		out.write(obj.toString());
		
		return null;
	}
	
public static void main(String[] args) {
	AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
	Article article = adminDaoImpl.getArticleContentById(100);
	String string = article.getContent();
	System.out.println(string+"!");
}
}
