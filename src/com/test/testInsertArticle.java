package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import org.hibernate.Session;

import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;

public class testInsertArticle {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("C:\\Users\\dyb1296\\Desktop\\wx.txt");
		java.util.Scanner scanner = new java.util.Scanner(file);
		String string = "";
		while(scanner.hasNextLine()){
			string += scanner.nextLine();
		}
		scanner.close();
		for(int i = 0;i<100;i++){
			Session session = SessionFactory.getSession();
			Article article = new Article();
			
			article.setType(1);
			article.setCreateDate(new Date());
			article.setCreater("网站编辑");
			article.setContent(string);
			article.setTitle("超清时代来临！国内首个4K试验频道将由广东开启");
			article.setUpdateDate(new Date());
			session.beginTransaction();
			session.save(article);
			session.getTransaction().commit();
			session.close();
		}
		
	}
}
