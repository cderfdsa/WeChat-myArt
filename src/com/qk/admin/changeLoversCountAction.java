package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;

public class changeLoversCountAction implements actionInterface{
	int id;
	int newCount;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNewCount() {
		return newCount;
	}


	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}


	@Override
	public String execute() throws IOException {
		// TODO Auto-generated method stub
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		try {
			adminDaoImpl.changeLoversCount(id, newCount);;
			out.write("�޸ĳɹ���");
		} catch (Exception e) {
			out.write("�޸�ʧ�ܣ�����ϵ����Ա��������Ϣ��"+e.getMessage());
		}		return null;
	}

}
