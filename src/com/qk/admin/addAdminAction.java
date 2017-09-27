package com.qk.admin;

import java.util.Date;


import com.qk.daoImpl.AdminDaoImpl;
import com.qk.model.Admin;

public class addAdminAction {


	public static void execute(String username,String password) {
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setCreateTime(new Date());
		admin.setLastLoginTime(new Date());
		adminDaoImpl.addAdmin(admin);
	}
	public static void main(String[] args) {
		execute("dyb1296", "dyb1296");
	}

}
