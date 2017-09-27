package com.test;

import com.qk.daoImpl.ArticleDaoImpl;
import com.qk.daoImpl.UserDaoImpl;

public class testIsLoveArticles {
	public static void main(String[] args) {
		int a = 320;
		ArticleDaoImpl impl = new ArticleDaoImpl();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		System.out.println(impl.isLovedById(48, 24));;
//		System.out.println(impl.isReadById(48, 23));;
		for(int i = 50;i<80;i++){
			System.out.println(impl.isLovedById(a, i));;
			userDaoImpl.loveArticleByIds(a, i);
			System.out.println(impl.isLovedById(a, i));;
		}
		System.out.println(userDaoImpl.getArticlesReadByIdCount(a));
		System.out.println(userDaoImpl.getArticlesLoveByIdCount(a));
		
	}

}
