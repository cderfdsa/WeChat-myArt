package com.qk.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qk.dao.ArticleDao;
import com.qk.hibernate.SessionFactory;
import com.qk.model.Article;
import com.qk.model.User;

public class ArticleDaoImpl implements ArticleDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		String sql = "from Article";
		Query<Article> query = session.createQuery(sql);
		List<Article> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getArticlesByType(int type) {
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		String sql = "from Article article where article.type="+type;
		Query<Article> query = session.createQuery(sql);
		List<Article> list = query.list();
		session.getTransaction().commit();
		//session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getUsersReadByArticleId(int id) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		String sql = "from Article article where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		Article article = query.list().get(0);
		List<User> list = new ArrayList<User>(article.getReaders());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersLoveByArticleId(int id) {
		Session session = SessionFactory.getSession();
		String sql = "from Article article where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		Article article = query.list().get(0);
		List<User> list = new ArrayList<User>(article.getLovers());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getDivArticlePage(int pageSize, int pageIndex) {
		int count = getArticlesCounts();  //鏁版嵁閲忔�绘暟
		if(pageSize == 0){
			return null;
		}
		int pageCounts = count/pageSize + 1; //鎬荤殑椤垫暟
		if(pageSize > count || pageCounts == 0){  //姣忛〉鏁版嵁澶т簬鏁版嵁鎬婚噺   
			pageSize = count;  
			pageIndex = 1;  
		}else
			if(pageIndex > pageCounts){   //绱㈠紩澶т簬椤垫暟  杩斿洖null
				return null;
			}
		if(pageIndex < 1){  //灏忎簬1  璁剧疆1
			pageIndex = 1;
		}
		int startIndex = pageSize * (pageIndex - 1) ;
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		String sql = "from Article article "+"order by article.createDate desc";
		Query<Article> query = session.createQuery(sql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize); //zheli璁剧疆鏉′欢
		List<Article> list = query.list();  //涓�鐩村埌杩欓噷 鎵嶅紑濮嬫煡璇�
		session.getTransaction().commit();
		session.close();
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getDivArticlePageByType(int type, int pageSize, int pageIndex) {
		int count = getArticlesCountByType(type);  //鏁版嵁閲忔�绘暟
		if(pageSize == 0){
			return null;
		}
		int pageCounts = count/pageSize + 1; //鎬荤殑椤垫暟
		if(pageSize > count || pageCounts == 0){  //姣忛〉鏁版嵁澶т簬鏁版嵁鎬婚噺   
			pageSize = count;  
			pageIndex = 1;  
		}else if(pageIndex > pageCounts){   //绱㈠紩澶т簬椤垫暟  杩斿洖null   
			return null;
		}
		//鍏堝垽鏂璽ype pageSize pageIndex鏄惁鍚堟硶 浣犳潵鍒ゆ柇涓嬬瓑涓�
		if(pageIndex < 1||pageSize<1){  //灏忎簬1  璁剧疆1
			pageIndex = 1;
			pageSize=10;//
		}
		//int limitStart=pageIndex-1;
		if(pageSize > count || pageCounts == 0){  //姣忛〉鏁版嵁澶т簬鏁版嵁鎬婚噺   
			pageSize = count;  
			pageIndex = 1;  
		}
		int startIndex = pageSize * (pageIndex - 1) ;
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		String sql = "from Article article where article.type="+type+" order by article.createDate desc ";
		Query<Article> query = session.createQuery(sql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List<Article> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
		//淇敼涓婇潰鐨勬暟鎹氨琛屼簡 
	}

	@Override
	public String returnArticleJson(List<Article> list) {
		JSONObject obj = new JSONObject();
		String string = "";
		//String obj=gson.toJson(list);
		string += "[";
		for(int i = 0;i < list.size();i++){
			obj.put("id", list.get(i).getId());
			obj.put("title", list.get(i).getTitle());
			obj.put("type", list.get(i).getType());
			obj.put("content", list.get(i).getContent());
			obj.put("creater", list.get(i).getCreater());
			obj.put("createDate", list.get(i).getCreateDate());
			obj.put("updateDate", list.get(i).getUpdateDate());
			obj.put("loversCount", list.get(i).getLoversCount());
			obj.put("readersCount", list.get(i).getReadersCount());
			string += obj.toString();
			string += ",";
		}
		string += "]";
		JSONArray jsonArray = new JSONArray(string);
		return jsonArray.toString();
	}

	@Override
	public String returnUserJson(List<User> list) {
		JSONObject obj = new JSONObject();
		String string = "";
		//String obj=gson.toJson(list);
		string += "[";
		for(int i = 0;i < list.size();i++){
			obj.put("id", list.get(i).getId());
			obj.put("username", list.get(i).getUsername());
			obj.put("createTime", list.get(i).getCreateTime());
			obj.put("lastLoginTime", list.get(i).getLastLoginTime());
			string += obj.toString();
			string += ",";
		}
		string += "]";
		JSONArray jsonArray = new JSONArray(string);
		return jsonArray.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Article getArticleContent(int id) {
		Session session = SessionFactory.getSession();
		int count = getArticleReadCountById(id) + 1;
		updateArticleReadCountById(count,id);
		String sql = "from Article article where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		Article article = query.list().get(0);
		return article;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int getArticleReadCountById(int id) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		String sql = "from Article article where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		int count = 0;
		try {
			count = query.list().get(0).getReadersCount();
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
		
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void updateArticleReadCountById(int count,int id) {
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		count = getArticleReadCountById(id) + 1;
		String sql = "update Article article set article.readersCount="+count+"where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int getArticleLoveCountById(int id) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		String sql = "from Article article where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		int count;
		try {
			count = query.list().get(0).getLoversCount();
		} catch (IndexOutOfBoundsException e) {
			return 0 ;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void updateArticleLoveCountById(int count, int id) {
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("鍙戠幇閿欒锛屼絾鏄洰鍓嶅凡缁忚В鍐�");}
		count = getArticleLoveCountById(id) + 1;
		String sql = "update Article article set article.loversCount="+count+"where article.id="+id;
		Query<Article> query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isLovedById(int userId, int articleId) {
		Session session = SessionFactory.getSession();
		String sql = "from User user where user.id="+userId;
		Query<User> query = session.createQuery(sql);
		User user = null;
		try {
			user = query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		Set<Article> articles = user.getLoveArticleSet();
		Iterator<Article> it = articles.iterator();
		while(it.hasNext()){
			if(it.next().getId() == articleId){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isReadById(int userId, int articleId) {
		Session session = SessionFactory.getSession();
		String sql = "from User user where user.id="+userId ;
		Query<User> query = session.createQuery(sql);
		User user = query.list().get(0);
		Set<Article> articles = user.getReadArticleSet();
		Iterator<Article> it = articles.iterator();
		while(it.hasNext()){
			if(it.next().getId() == articleId){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesCountByType(int type) {
		Session session = SessionFactory.getSession();
		String sql = "select count(*) from Article article where article.type="+type;
		Query<Integer> query = session.createQuery(sql);
		int count = Integer.parseInt(String.valueOf(query.uniqueResult()));
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesCounts() {
		Session session = SessionFactory.getSession();
		String sql = "select count(*) from Article";
		Query<Integer> query = session.createQuery(sql);
		int count = Integer.parseInt(String.valueOf(query.uniqueResult()));
		return count;
	}
}
