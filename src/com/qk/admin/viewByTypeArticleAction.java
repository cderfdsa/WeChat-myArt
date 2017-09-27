package com.qk.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.qk.dao.actionInterface;
import com.qk.daoImpl.AdminDaoImpl;

public class viewByTypeArticleAction implements actionInterface{
	int type;
	int pageIndex;
	int pageSize;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String execute() throws IOException {
		HttpServletResponse
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
  		out.write(adminDaoImpl.returnViewAerticleByTypeString(type,pageIndex, pageSize));
		return null;
 	}
}
