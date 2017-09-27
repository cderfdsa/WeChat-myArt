package com.qk.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.qk.daoImpl.UserDaoImpl;
import com.qk.mapper.UserMapper;

public class addUserAction {
	String username;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	String execute() throws IOException{
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDaoImpl daoImpl = new UserDaoImpl();
		boolean exist = true;
 		JSONObject jsonObject = new JSONObject();
		if(!daoImpl.existsByUsername(username)){
			exist = false;
		} 
		if(exist){
			System.out.println("存在");
			daoImpl.updateUser(username);
 			jsonObject.put("username", username);
			jsonObject.put("exist", true);//存在并更新了用户信息
		}else{
			UserMapper userMapper = new UserMapper();
			userMapper.setUser(username, new Date(), new Date());
			daoImpl.addUser(userMapper.getUser());
 			jsonObject.put("username", username);
			jsonObject.put("exist", false);//"不存在此用户，新建了用户"
		}
		out.write(jsonObject.toString());
		return null;
	}
	public static void main(String[] args) {
		String username = "dyb1296";
		UserDaoImpl daoImpl = new UserDaoImpl();
		boolean exist = true;
		JSONObject jsonObject = new JSONObject();
 		if(!daoImpl.existsByUsername(username)){
			exist = false;
		} 
		if(exist){
 			daoImpl.updateUser(username);
			jsonObject.put("username", username);
			jsonObject.put("exist", true);//存在并更新了用户信息
		}else{
			UserMapper userMapper = new UserMapper();
			userMapper.setUser(username, new Date(), new Date());
			daoImpl.addUser(userMapper.getUser());
		}
		System.out.println(jsonObject.toString());
	}
}
