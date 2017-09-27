package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.qk.daoImpl.UserDaoImpl;

public class getUserIdAction {
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int id = userDaoImpl.getUserIdByUsername(username);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		out.write(jsonObject.toString());
		return null;
 	}

}
