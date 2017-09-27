package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;

public class delArticleByIdAction implements actionInterface{
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
		try {
			adminDaoImpl.delArticleById(id);
 			out.write("ɾ���ɹ���");
		} catch (Exception e) {
 			out.write("ɾ��ʧ�ܣ�����ϵ����Ա��dyb1296@gmail.com");
		}
		
		return null;
	}
	

}
