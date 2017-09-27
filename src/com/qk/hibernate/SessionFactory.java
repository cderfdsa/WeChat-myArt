package com.qk.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactory {
	// private static String configfile="/hibernate.cfg.xml";
	private static ThreadLocal<Session> threadlocal=new ThreadLocal<Session>();
	private static org.hibernate.SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry ;
	static{
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();  
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public static Session getSession() throws HibernateException{
		Session session = (Session) threadlocal.get();
		if(session==null||!session.isOpen()){
			if(sessionFactory == null){
				rebuildSessionFactory();
			}
			session = (sessionFactory!=null)?sessionFactory.openSession():null;
			threadlocal.set(session);
		}

		return session;

	}

	public static void rebuildSessionFactory() {
		// TODO Auto-generated method stub
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();  
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void closeSession() throws HibernateException{ 
		Session session = (Session) threadlocal.get();
		threadlocal.set(null);
		if(session!=null){
			session.close();
		}
	}
}

