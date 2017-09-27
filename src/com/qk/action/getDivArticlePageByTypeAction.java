package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import com.qk.daoImpl.ArticleDaoImpl;
import com.qk.model.Article;

public class getDivArticlePageByTypeAction {
	int type;
	int pageSize;
	int pageIndex;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		List<Article> list = articleDaoImpl.getDivArticlePageByType(type, pageSize, pageIndex);
		JSONArray jsonObject = new  JSONArray("[]");
		if(list == null){
 			out.write(jsonObject.toString());
		}else{
			out.write(articleDaoImpl.returnArticleJson(list));
		}
		return null;
	}
}
