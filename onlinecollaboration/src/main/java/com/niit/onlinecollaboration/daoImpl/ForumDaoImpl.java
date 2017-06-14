package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.ForumDao;
import com.niit.onlinecollaboration.model.Forum;
@Repository("forumDao")
@Transactional
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Transactional
	@Override
	public List<Forum> list() {
		//String hql = "FROM Forum";
	/*	Query query = sessionFactory.getCurrentSession().createQuery(FROM Forum);*/
		return sessionFactory.getCurrentSession().createQuery("FROM Forum").list();
	}

	@Transactional
	@Override
	public List<Forum> mainList() {
	//	String hql = "FROM Forum order by postDate";
	//	Query query = sessionFactory.getCurrentSession().createQuery( "FROM Forum order by postDate");
		sessionFactory.getCurrentSession().createQuery( "FROM Forum order by postDate").setFirstResult(0);
		sessionFactory.getCurrentSession().createQuery( "FROM Forum order by postDate").setMaxResults(3); 
		return sessionFactory.getCurrentSession().createQuery( "FROM Forum order by postDate").list();

	}
	@Transactional
	@Override
	public Forum getForum(int id) {
		return sessionFactory.getCurrentSession().get(Forum.class, id);

	}
	@Transactional
	@Override
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	@Transactional
	@Override
	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	@Transactional
	@Override
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	

}
