package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.ForumPostDao;
import com.niit.onlinecollaboration.model.ForumPosts;

@Repository("forumPostDao")
@Transactional
public class ForumPostDaoImpl implements ForumPostDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ForumPosts> list() {
	//	String hql = "FROM ForumPosts";
	//	Query query = sessionFactory.getCurrentSession().createQuery("FROM ForumPosts");
		return sessionFactory.getCurrentSession().createQuery("FROM ForumPosts").list();
	}


	@Override
	public List<ForumPosts> list(int id) {
		//String hql = "FROM ForumPosts where forum = " + id +"";
	//	Query query = sessionFactory.getCurrentSession().createQuery("FROM ForumPosts where forum = " + id +"");
		return sessionFactory.getCurrentSession().createQuery("FROM ForumPosts where forum = " + id +"").list();
	}


	@Override
	public ForumPosts getForumPosts(int id) {
		return sessionFactory.getCurrentSession().get(ForumPosts.class, id);

	}

	@Override
	public boolean addForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().save(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().update(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().delete(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


}
