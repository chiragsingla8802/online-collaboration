package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.ForumRequestDao;
import com.niit.onlinecollaboration.model.ForumRequest;

@Repository("forumRequestDao")
@Transactional
public class ForumRequestDaoImpl implements ForumRequestDao{
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<ForumRequest> list() {
	//	String hql = "FROM ForumRequest";
	//	Query query = sessionFactory.getCurrentSession().createQuery("FROM ForumRequest");
		return  sessionFactory.getCurrentSession().createQuery("FROM ForumRequest").list();

	}

	@Override
	public List<ForumRequest> list(String status) {
	//	String hql = "FROM ForumRequest where status = '" + status +"'";
	//	Query query = sessionFactory.getCurrentSession().createQuery( "FROM ForumRequest where status = '" + status +"'");
		return sessionFactory.getCurrentSession().createQuery( "FROM ForumRequest where status = '" + status +"'").list();
	}

	@Override
	public List<ForumRequest> list(int id) {
	//	String hql = "FROM ForumRequest where forum = '" + id +"'";
	//	Query query = sessionFactory.getCurrentSession().createQuery("FROM ForumRequest where forum = '" + id +"'");
		return sessionFactory.getCurrentSession().createQuery("FROM ForumRequest where forum = '" + id +"'").list();

	}

	@Override
	public ForumRequest getForumRequest(int id) {
		return sessionFactory.getCurrentSession().get(ForumRequest.class, id);

	}

	@Override
	public boolean addForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().save(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().update(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().delete(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
