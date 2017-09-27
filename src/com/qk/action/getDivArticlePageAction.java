package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qk.daoImpl.ArticleDaoImpl;
import com.qk.model.Article;

public class getDivArticlePageAction {
	int pageSize;
	int pageIndex;
	
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
	public String execute() throws IOException {
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		List<Article> list = (List<Article>)articleDaoImpl.getDivArticlePage(pageSize, pageIndex);
		JSONArray jsonObject = new  JSONArray("[]");
		if(list == null){
 			out.write(jsonObject.toString());
		}else{
			out.write(articleDaoImpl.returnArticleJson(list));
		}
		
		return null;
	}
	public static void main(String[] args) {
		int pageSize = 1;
		int pageIndex = 3;
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		List<Article> list = (List<Article>)articleDaoImpl.getDivArticlePage(pageSize, pageIndex);
		JSONObject obj = new JSONObject();
		String string = "";
		//String obj=gson.toJson(list);
		System.out.println(list.size());
		string += "[";
		for(int i = 0;i < list.size();i++){
			obj.put("id", list.get(i).getId());
			obj.put("title", list.get(i).getTitle());
			obj.put("type", list.get(i).getType());
			obj.put("content", list.get(i).getContent());
			obj.put("creater", list.get(i).getCreater());
			obj.put("createDate", list.get(i).getCreateDate());
			obj.put("updateDate", list.get(i).getUpdateDate());
			obj.put("loversCount", list.get(i).getLoversCount());
			obj.put("readersCount", list.get(i).getReadersCount());
			string += obj.toString();
			string += ",";
		}
		string += "]";
		JSONArray jsonArray = new JSONArray(string);
		System.out.println(jsonArray.toString());
	}

}
