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


public class getArticleByTypeAction {
	int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		List<Article> list = (List<Article>)articleDaoImpl.getArticlesByType(type);
		JSONObject obj = new JSONObject();
		String string = "";
		//String obj=gson.toJson(list);
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
		out.write(jsonArray.toString());
		return null;

	}
public static void main(String[] args) {
	int type = 1;
	ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
	List<Article> list = (List<Article>)articleDaoImpl.getArticlesByType(type);
	JSONObject obj = new JSONObject();
	String string = "";
	//String obj=gson.toJson(list);
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
