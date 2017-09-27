package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;

public class isLoginAction implements actionInterface{

	@Override
	public String execute() throws IOException {
		HttpServletResponse
		response = ServletActionContext.getResponse();
		HttpServletRequest
		request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		HttpSession session = request.getSession();
		if(adminDaoImpl.isLogin(session)){
			out.write("true");
		}else{
			out.write("false");
		}
		return null;
	}
	

}
