package com.test;

import com.qk.daoImpl.AdminDaoImpl;


public class testDelArticle {
	public static void main(String[] args) {
 		//		UserDaoImpl userDaoImpl = new UserDaoImpl();
		//		User user = userDaoImpl.getUserById(id);
		//		user.getLoveArticleSet()
		AdminDaoImpl articleDaoImpl = new AdminDaoImpl();
		for(int i= 222;i<400;i++)
		articleDaoImpl.delArticleById(i);

		
	}

}
