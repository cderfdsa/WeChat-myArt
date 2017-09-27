package com.qk.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.qk.dao.UserDao;
import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;
import com.qk.model.User;

public class UserDaoImpl implements UserDao{

	@Override
	@Transactional
	public int addUser(User user) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return 0;//成功注册用户
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(String username) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		String sql = "update User user set user.lastLoginTime='"+date+"'  where user.username='"+username+"'";
		Query<User> query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User";
		Query<User> query = session.createQuery(sql);
		List<User> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesReadById(int id) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.id="+id;
		Query<User> query = session.createQuery(sql);
		User user = null;
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		List<Article> list = new ArrayList<Article>(user.getReadArticleSet());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesLoveById(int id) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.id="+id;
		Query<User> query = session.createQuery(sql);
		User user = null;
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		List<Article> list = new ArrayList<Article>(user.getLoveArticleSet());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(int id) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.id="+id;
		Query<User> query = session.createQuery(sql);
		List<User> list =  query.list();
		User user = null;
		try {
			user = list.get(0);
		} catch (IndexOutOfBoundsException e) {
			user = null;
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.username='"+username+"'";
		Query<User> query = session.createQuery(sql);
		List<User> list =  query.list();
		User user = null;
		try {
			user = list.get(0);
		} catch (IndexOutOfBoundsException e) {
			user = null;
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesReadByIdCount(int id) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.id="+id;
		Query<User> query = session.createQuery(sql);
		User user = null;
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			session.getTransaction().commit();
			session.close();
			e.printStackTrace();
			return 0;
		}
		int count = user.getReadArticleSet().size();
		session.getTransaction().commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesLoveByIdCount(int id) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from User user where user.id="+id;
		Query<User> query = session.createQuery(sql);
		User user = new User();
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			session.getTransaction().commit();
			session.close();
			//e.printStackTrace();
			System.out.println("报溢出错误，此时return -1");
			return -1;
		}
		int count = user.getLoveArticleSet().size();
		session.getTransaction().commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void readArticleByIds(int userId, int articleId) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String selectUser = "from User user where user.id="+userId;
		String selectArticle = "from Article article where article.id="+articleId;
		Query<User> query0 = session.createQuery(selectUser);
		User user = query0.list().get(0);
		Query<Article> query1 = session.createQuery(selectArticle);
		Article article = null;
		try {
			article = query1.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		Set<Article> set = user.getReadArticleSet();
		set.add(article);
		user.setReadArticleSet(set);
		session.save(user);
		session.getTransaction().commit();
		session.close();
		System.out.println("ok");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void loveArticleByIds(int userId, int articleId) {
		ArticleDaoImpl impl = new ArticleDaoImpl();
		int count = impl.getArticleLoveCountById(articleId) + 1;
		impl.updateArticleLoveCountById(count,articleId);
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String selectUser = "from User user where user.id="+userId;
		String selectArticle = "from Article article where article.id="+articleId;
		Query<User> query0 = session.createQuery(selectUser);
		User user;
		try {
			user = query0.list().get(0);
		} catch (IndexOutOfBoundsException e1) {
			return;
		}
		Query<Article> query1 = session.createQuery(selectArticle);
		Article article = null;
		try {
			article = query1.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		Set<Article> set = user.getLoveArticleSet();
		set.add(article);
		user.setLoveArticleSet(set);;
		session.save(user);
		session.getTransaction().commit();
		session.close();
		System.out.println("ok");
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserIdByUsername(String username) {
		Session session = SessionFactory.getSession();
		String sql = "from User user where user.username='"+username+"'";
		Query<User> query = session.createQuery(sql);
		User user = null;
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
		return user.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean existsByUsername(String username) {
		String sql = "from User user where user.username='"+username+"'";
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		Query<User> query = session.createQuery(sql);
		try {
			query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			session.getTransaction().commit();
			session.close();
			return false;
		}
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Date[] getTimeByUsername(String username) {
		Date[] dates = new Date[2];
		String sql = "from User user where user.username='"+username+"'";
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		Query<User> query = session.createQuery(sql);
		User user = query.list().get(0);
		dates[0] = user.getCreateTime();
		dates[1] = user.getLastLoginTime();
		session.getTransaction().commit();
		session.close();
		return dates;
	}
}
