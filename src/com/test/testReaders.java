package com.test;

import com.qk.daoImpl.ArticleDaoImpl;

public class testReaders {
	public static void main(String[] args) {
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		System.out.println(articleDaoImpl.getUsersReadByArticleId(11).get(0).getId());
		System.out.println(articleDaoImpl.getUsersLoveByArticleId(11).get(0).getId());
	}

}
