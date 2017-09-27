package com.test;

import com.qk.daoImpl.ArticleDaoImpl;
import com.qk.daoImpl.UserDaoImpl;

public class test {
	public static void main(String[] args) {
		UserDaoImpl util = new UserDaoImpl();
		ArticleDaoImpl util1 = new ArticleDaoImpl();
		System.out.println(util.getArticlesLoveByIdCount(11));
		util.readArticleByIds(11,11);
		util1.getArticleContent(11).getContent();
		util.loveArticleByIds(11,11);
		System.out.println(util1.isLovedById(11, 11));
 	}
}
