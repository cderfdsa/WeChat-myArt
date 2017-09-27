package com.qk.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.qk.dao.AdminDao;
import com.qk.hibernate.SessionFactory;
import com.qk.model.Admin;
import com.qk.model.Article;
import com.qk.model.User;

public class AdminDaoImpl implements AdminDao{

	@Override
	@Transactional
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		session.save(admin);
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void delAdminById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete Admin admin where admin.id="+id;
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		Query<Admin> query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	@Transactional
	public boolean delArticleById(int id) {

		//		UserDaoImpl userDaoImpl = new UserDaoImpl();
		//		User user = userDaoImpl.getUserById(id);
		//		user.getLoveArticleSet()
		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
		Article article =null;
		try {
			article = articleDaoImpl.getArticleContent(id);
			if(article == null){
				return true;
			}

			Set<User> loveUsers = article.getLovers();
			Set<User> readUsers = article.getReaders();
			Session session = SessionFactory.getSession();
			session.beginTransaction();
			loveUsers.clear();
			readUsers.clear();
			session.update(article);
			session.getTransaction().commit();
			session.close();
			session = SessionFactory.getSession();
			session.beginTransaction();
			String sql = "delete from Article article where article.id="+id;
			session.createQuery(sql).executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("文章不存在");
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean exists(String username, String password) {
		Session session = SessionFactory.getSession();
		try{
			session.beginTransaction();
		}catch (IllegalStateException e) {
			System.out.println("发现错误，但是目前已经解决");
		}
		String sql = "from Admin admin where admin.username='"+username+"' and admin.password='"+password+"'";
		Query<Admin> query = session.createQuery(sql);
		try {
			query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			session.getTransaction().commit();
			session.close();
			return false;
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("1234");
		return true;
	}

	@Override
	public boolean isLogin(HttpSession session) {
		try {
			if(String.valueOf(session.getAttribute("username")).length()>5)
				return true;
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean insert(String title, String content, int type,String creater) {
		try {
			Article article = new Article();
			article.setContent(content);
			article.setCreateDate(new Date());
			article.setTitle(title);
			article.setType(type);
			article.setUpdateDate(new Date());
			article.setCreater(creater);
			Session session = SessionFactory.getSession();
			try{
				session.beginTransaction();
			}catch (IllegalStateException e) {
				System.out.println("发现错误，但是目前已经解决");
			}
			session.save(article);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}



	@Override
	public String getUsername(HttpSession session) {
		// TODO Auto-generated method stub
		return String.valueOf(session.getAttribute("username"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesCount() {
		Session session = SessionFactory.getSession();
		String sql = "select count(*) from Article";
		Query<Integer> query = session.createQuery(sql);
		int count = Integer.parseInt(String.valueOf(query.uniqueResult()));
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAdminDivArticlePage(int pageIndex, int pageSize) {
		int count = getArticlesCount(); //数据量总数
		int pageCounts = count/pageSize + 1; //总的页数
		if(pageSize > count || pageCounts == 0){  //每页数据大于数据总量   
			pageSize = count;  
			pageIndex = 1;
			pageCounts = 1;
		}
		if(pageIndex > pageCounts){   
			pageIndex = pageCounts;
		}
		if(pageIndex < 1){  //小于1  设置1
			pageIndex = 1;
		}
		int startIndex = pageSize * (pageIndex - 1) ;
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("发现错误，但是目前已经解决");}
		String sql = "from Article article "+"order by article.updateDate desc";
		Query<Article> query = session.createQuery(sql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize); //zheli设置条件
		List<Article> list = query.list();  //一直到这里 才开始查询
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAdminDivArticlePageByType(int type, int pageIndex, int pageSize) {
		int count = getArticlesByType(type); //数据量总数
		//System.out.println("数据量总数"+ count+"pageSize是"+pageSize);
		
		int pageCounts = count/pageSize + 1; //总的页数
		if(pageSize > count || pageCounts == 0){  //每页数据大于数据总量   
			pageSize = count;  
			pageIndex = 1;
			pageCounts = 1;
		}
		if(pageIndex > pageCounts){   
			pageIndex = pageCounts;
		}
		if(pageIndex < 1){  //小于1  设置1
			pageIndex = 1;
		}
		//System.out.println(pageCounts+"开始查询的index是"+pageIndex);
		int startIndex = pageSize * (pageIndex - 1) ;
		Session session = SessionFactory.getSession();
		try{session.beginTransaction();}catch (IllegalStateException e) {System.out.println("发现错误，但是目前已经解决");}
		String sql = "from Article article where article.type='"+type+"' order by article.updateDate desc";
		Query<Article> query = session.createQuery(sql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize); //zheli设置条件
		List<Article> list = query.list();  //一直到这里 才开始查询
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public String returnViewAerticleString(int pageIndex, int pageSize) {
		System.out.println(pageIndex);
		List<Article> list = getAdminDivArticlePage(pageIndex, pageSize);
		String s = " <table class=\"table\">"+
        "<caption>上下文表格布局</caption>"
				+ "<thead><tr> <th>序号id</th>"+
            "<th>标题</th>"+
            "<th>类型</th>"+
            "<th>内容缩写</th>"+
            "<th>创建时间</th>"+
            "<th>最后更新时间</th>"+
            "<th>创建者</th>"+
            "<th>点赞数目</th>"+
            "<th>浏览数目</th>"+
            "<th>编辑</th>"+
            "<th>删除</th>"+
        "</tr>"+
        "</thead>"+
        "<tbody>";
		String type = "";
		String status = "";
		//System.out.println("list的大小是"+list.size());
		for(Article article:list){
			if(article.getType() == 0){
				type = "苹果";
				status = "success";
			}
			if(article.getType() == 1){
				type = "安卓";
				status = "warning";
			}
			if(article.getType() == 2){
				type = "Windows Phone";
				status = "active";
			}
			if(article.getType() == 3){
				type = "其他";
				status = "danger";
			}
			String title = "";
 			if(article.getTitle().length()>15){
				title = article.getTitle().substring(0,14);
			}else{
				title = article.getTitle();
			}
			s += "<tr class='"+status+"'>";
			s +="<td>"+article.getId()+"</td>";
			s +="<td>"+title+"</td>";
			s +="<td>"+type+"</td>";
			s +="<td>"+title+"</td>";
			s +="<td>"+article.getCreateDate().toString()+"</td>";
			s +="<td>"+article.getUpdateDate().toString()+"</td>";
			s +="<td>"+article.getCreater()+"</td>";
			s +="<td><a href=\"#\" onclick=\"changeLoveCount("+article.getId()+")\">"+article.getLoversCount()+"</a></td>";
			s +="<td><a href=\"#\"  onclick=\"changeReadCount("+article.getId()+")\">"+article.getReadersCount()+"</a></td>";
			s +="<td>"+"<button type=\"button\" class=\"btn btn-primary\" onclick=\"jumpTo("+article.getId()+")\">编辑</button>"+"</td>";
			s +="<td>"+"<button type=\"button\" class=\"btn btn-danger\" onclick=\"del("+article.getId()+")\">删除</button>"+"</td>";
 			s +="</tr>";
 			//System.out.println(s);
		}
		s += "</tbody></table>";
		s += "<div class=\"nav_self\">";
		s +="<ul class=\"pagination\">"+
	"<li><a href=\"#\" onclick=\"getIndexContent("+(pageIndex-1)+",20)\">&laquo;</a></li>";
		int count = getArticlesCount(); //数据量总数
		int pageCounts = count/pageSize + 1; //总的页数
		if(pageSize > count || pageCounts == 0){  //每页数据大于数据总量   
			pageSize = count;  
			pageIndex = 1;
			pageCounts = 1;
		}
		if(pageIndex > pageCounts){   
			pageIndex = pageCounts;
		}
		if(pageIndex < 1){  //小于1  设置1
			pageIndex = 1;
		}
		String style = "";
		for(int i = 0;i< pageCounts; i++){
			if((i+1) == pageIndex){
				style = "class='active'";
			}else{
				style = "";
			}
			s +="<li "+style+"><a href=\"#\" onclick=\"getIndexContent("+(i+1)+",20)\">"+(i+1)+"</a></li>";
		}
		s +="<li><a href=\"#\" onclick=\"getIndexContent("+(pageIndex+1)+",20)\">&raquo;</a></li></ul></div>";
		s +="<br>共有"+count+"条数据，分"+pageCounts+"显示";
		 
		
//		s +="<div class=\"rows\"><div class=\"col-xs-2\"> 输入页码跳转： </div>"+
//        "<div class=\"col-xs-2\">"+
//            "<input placeholder=\"比如‘1’\" type=\"text\" class=\"form-control\"  id=\"pageIndex\">"+
//       " </div>"+
//       " <div class=\"col-xs-5\">"+
//           " <button type=\"button\" class=\"btn btn-info\" onclick=\"javascript:jump()\">跳转</button>"+
//       " </div>";
 		return s;
	}

	@Override
	public void changeLoversCount(int id,int newCount) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		session.beginTransaction();
		Article article = session.get(Article.class, id);
		article.setLoversCount(newCount);
		article.setUpdateDate(new Date());
 		session.update(article);
 		session.getTransaction().commit();
		
	}

	@Override
	public void changeReadersCount(int id,int newCount) {
		// TODO Auto-generated method stub
		Session session = SessionFactory.getSession();
		try {
			session.beginTransaction();
		} catch (Exception e) {
			}
		Article article = session.get(Article.class, id);
		article.setReadersCount(newCount);
		article.setUpdateDate(new Date());
 		session.update(article);
 		session.getTransaction().commit();
	}

	@Override
	@Transactional
	public Article getArticleContentById(int id) {
		Session session = SessionFactory.getSession();
		try {
			session.beginTransaction();
		} catch (Exception e) {
			}
		Article article = session.get(Article.class, id);
		session.getTransaction().commit();
		//System.out.println(article.getContent());
		return article;
	}

	@Override
	public boolean updateArticleById(int id, String title, String content, int type, String creater) {
		// TODO Auto-generated method stub
		
		try {
			Session session = SessionFactory.getSession();
			try {
				session.beginTransaction();
			} catch (Exception e) {
  			}
			Article article = session.get(Article.class, id);
			article.setCreater(creater);
			article.setTitle(title);
			article.setContent(content);
			article.setType(type);
			article.setUpdateDate(new Date());
			session.update(article);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String returnViewAerticleByTypeString(int type, int pageIndex, int pageSize) {
		String[] obj = getStatusAndVersion(type);
		List<Article> list = getAdminDivArticlePageByType(type,pageIndex, pageSize);
		String s = "<h1>文章查看系统</h1>-- "+"<h2>"+obj[0]+"</h2>"
				+ "<table class=\"table\">"+
        "<caption>上下文表格布局</caption>"
				+ "<thead><tr> <th>序号id</th>"+
            "<th>标题</th>"+
            "<th>类型</th>"+
            "<th>内容缩写</th>"+
            "<th>创建时间</th>"+
            "<th>最后更新时间</th>"+
            "<th>创建者</th>"+
            "<th>点赞数目</th>"+
            "<th>浏览数目</th>"+
            "<th>编辑</th>"+
            "<th>删除</th>"+
        "</tr>"+
        "</thead>"+
        "<tbody>";
		System.out.println("list的大小是"+list.size());
		int typeDiv = 0;
		for(Article article:list){
			typeDiv = article.getType();
			String title = "";
 			if(article.getTitle().length()>15){
				title = article.getTitle().substring(0,14);
			}else{
				title = article.getTitle();
			}
			s += "<tr class='"+obj[1]+"'>";
			s +="<td>"+article.getId()+"</td>";
			s +="<td>"+title+"</td>";
			s +="<td>"+obj[0]+"</td>";
			s +="<td>"+title+"</td>";
			s +="<td>"+article.getCreateDate().toString()+"</td>";
			s +="<td>"+article.getUpdateDate().toString()+"</td>";
			s +="<td>"+article.getCreater()+"</td>";
			s +="<td><a href=\"#\" onclick=\"changeLoveCount("+article.getId()+")\">"+article.getLoversCount()+"</a></td>";
			s +="<td><a href=\"#\"  onclick=\"changeReadCount("+article.getId()+")\">"+article.getReadersCount()+"</a></td>";
			s +="<td>"+"<button type=\"button\" class=\"btn btn-primary\" onclick=\"jumpTo("+article.getId()+")\">编辑</button>"+"</td>";
			s +="<td>"+"<button type=\"button\" class=\"btn btn-danger\" onclick=\"del("+article.getId()+")\">删除</button>"+"</td>";
 			s +="</tr>";
 			//System.out.println(s);
		}
		s += "</tbody></table>";
		s += "<div class=\"nav_self\">";
		s +="<ul class=\"pagination\">"+
	"<li><a href=\"#\" onclick=\"getIndexContent("+typeDiv+","+(pageIndex-1)+",20)\">&laquo;</a></li>";
		int count = getArticlesByType(type); //数据量总数
		
		int pageCounts = count/pageSize + 1; //总的页数
		if(pageSize > count || pageCounts == 0){  //每页数据大于数据总量   
			pageSize = count;  
			pageIndex = 1;
			pageCounts = 1;
		}
		if(pageIndex > pageCounts){   
			pageIndex = pageCounts;
		}
		if(pageIndex < 1){  //小于1  设置1
			pageIndex = 1;
		}
		System.out.println(pageCounts+"              "+pageIndex);
		
		String style = "";
		for(int i = 0;i< pageCounts; i++){
			if((i+1) == pageIndex){
				style = "class='active'";
			}else{
				style = "";
			}
			s +="<li "+style+"><a href=\"#\" onclick=\"getIndexContent("+typeDiv+","+(i+1)+",20)\">"+(i+1)+"</a></li>";
		}
		s +="<li><a href=\"#\" onclick=\"getIndexContent("+typeDiv+","+(pageIndex+1)+",20)\">&raquo;</a></li></ul></div>";
		s +="<br>共有"+count+"条数据，分"+pageCounts+"显示";
		 
		
//		s +="<div class=\"rows\"><div class=\"col-xs-2\"> 输入页码跳转： </div>"+
//        "<div class=\"col-xs-2\">"+
//            "<input placeholder=\"比如‘1’\" type=\"text\" class=\"form-control\"  id=\"pageIndex\">"+
//       " </div>"+
//       " <div class=\"col-xs-5\">"+
//           " <button type=\"button\" class=\"btn btn-info\" onclick=\"javascript:jump()\">跳转</button>"+
//       " </div>";
 		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getArticlesByType(int type) {
		Session session = SessionFactory.getSession();
		String sql = "select count(*) from Article article where article.type="+type;
		Query<Integer> query = session.createQuery(sql);
		int count = Integer.parseInt(String.valueOf(query.uniqueResult()));
		return count;
	}

	@Override
	public String[] getStatusAndVersion(int type) {
		String[] obj = new String[2];
		if( type == 0){
			obj[0] = "苹果";
			obj[1] = "success";
		}
		if( type == 1){
			obj[0] = "安卓";
			obj[1] = "warning";
		}
		if( type == 2){
			obj[0] = "Windows Phone";
			obj[1] = "active";
		}
		if( type == 3){
			obj[0] = "其他";
			obj[1] = "danger";
		}
		return obj;
	}
 

	 



}
