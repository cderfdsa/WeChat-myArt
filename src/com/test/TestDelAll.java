package com.test;

 
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;

import org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.qk.daoImpl.AdminDaoImpl;
import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;
import com.qk.model.User;

public class TestDelAll {
	public static void main(String[] args) {
		delarticle();
	}
	@SuppressWarnings("unchecked")
	public static void delarticle(){
		Session session = SessionFactory.getSession();
		session.beginTransaction();
		String sql = "from Article article where article.id != null";
		Query<Article> query = session.createQuery(sql);
		Set<Integer> set = new HashSet<>();
		for(Article article : query.list()){
			set.add(article.getId());
		}
		AdminDaoImpl articleDaoImpl = new AdminDaoImpl();

		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext()){
			articleDaoImpl.delArticleById(iterator.next());
		}
		if(!session.isOpen()){
			session.beginTransaction();
		}
		session.getTransaction().commit();
		session.close();
		session = SessionFactory.getSession();
		session.beginTransaction();
		sql = "delete User user where user.id != null";
		Query<User> query2 = session.createQuery(sql);
		query2.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		 
	}

}
