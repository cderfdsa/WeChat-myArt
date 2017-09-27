package com.test;

 import java.io.FileNotFoundException;
import java.util.Date;


import com.qk.daoImpl.UserDaoImpl;
import com.qk.model.User;
 
public class testInsertUser {
	public static void main(String[] args) throws FileNotFoundException {
		
		for(int i = 0;i<2;i++){
			User user = new User();
			user.setCreateTime(new Date());
			user.setLastLoginTime(new Date());
	 		user.setUsername("dyb1296"+i);
	 		UserDaoImpl userDaoImpl = new UserDaoImpl();
	 		userDaoImpl.addUser(user);
		}
	}

}
