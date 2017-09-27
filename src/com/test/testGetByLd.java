package com.test;

import com.qk.daoImpl.AdminDaoImpl;
import com.qk.model.Article;

public class testGetByLd {
public static void main(String[] args) {
	AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
	Article article = adminDaoImpl.getArticleContentById(530);
	System.out.println(article.getContent());
}
}
