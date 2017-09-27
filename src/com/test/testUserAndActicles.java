package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.hibernate.Session;

import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;
import com.qk.model.User;

public class testUserAndActicles {
	static int type0 = 0;
	static int type1 = 0;
	static int type2 = 0;
	static Random random = new Random();
public static void main(String[] args) throws InterruptedException, FileNotFoundException {
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
		int a = random.nextInt(3);
		if(a == 0){
			type0++;
			article.setTitle("类别"+a+"超清时代来临！国内首个4K试验频道将由广东开启"+type0);
			article.setContent(i+string);
		}
		if(a == 1){
			type1++;
			article.setTitle("类别"+a+"超清时代来临！国内首个4K试验频道将由广东开启"+type1);
			article.setContent(i+string);
		}
		if(a == 2){
			type2++;
			article.setTitle("类别"+a+"超清时代来临！国内首个4K试验频道将由广东开启"+type2);
			article.setContent(i+string);
		}
		article.setType(a);
		article.setCreateDate(new Date());
		String creater = "";
		if(a == 0){
			creater = "自媒体";
		}else if(a == 1){
			creater = "网站编辑";
		}else{
			creater = "运营";
		}
		article.setCreater(creater);
		article.setUpdateDate(new Date());
		session.beginTransaction();
		session.save(article);
		session.getTransaction().commit();
		session.close();
		session = SessionFactory.getSession();
		session.beginTransaction();
		User user = new User();
		user.setUsername("dyb1296"+i);
		user.setCreateTime(new Date());
		user.setLastLoginTime(new Date());
		Set<Article> articles = new HashSet<>();
		articles.add(article);
		user.setLoveArticleSet(articles);
		user.setReadArticleSet(articles);
		session.save(user);
		session.getTransaction().commit();
		session.close();
		//Thread.sleep(200);
	}
}
}
