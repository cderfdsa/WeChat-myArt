package com.test;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;
import com.qk.model.User;

public class testSelectUserArticle {
	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) {
		Session session = SessionFactory.getSession();
		session.beginTransaction();
		User user = new User();
		String sql = "from User user where user.id=1";
		Query<User> query = session.createQuery(sql);
		user = query.list().get(0);
		Article article = new Article();
		System.out.println(user.getLoveArticleSet().size());
		session.getTransaction().commit();
		session.close();
		;
	}
}
