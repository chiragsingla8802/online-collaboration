package com.niit.onlinecollaboration.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.onlinecollaboration.dao.FriendDao;
import com.niit.onlinecollaboration.model.Friend;
import com.niit.onlinecollaboration.model.User_Detail;

@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {

	@Autowired
	SessionFactory sessionFactory;

	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*@Transactional
	public boolean saveOrUpdate(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}*/

	/*@Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}*/

	/*@Transactional
	public Friend get(int userId, int friendId) {
		String hql = "from Friend where userId = "+userId+" and friendId = "+friendId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			return (Friend) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}*/

	/*@Transactional
	public List<Friend> list() {
		String hql = "from Friend";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}*/

	/*@Override
	@Transactional
	public List<Friend> getFriends(int userId) { 
		String hql = "from Friend where userId = "+userId+" and status = 'ACCEPT'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}*/
	
	
	
	/*@Override
	@Transactional
	public List<Friend> getRequest(int userId) {
		String hql = "from Friend where friendId = "+userId+" and status = 'PENDING'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}*/

	
	/*@Override
	@Transactional
	public List<Friend> getTopFriends(int n) {
		String hql = "FROM Friend WHERE status = 'ACCEPT' ORDER BY id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(n);
		return query.getResultList();
	}*/

	@Override
	@Transactional
	public boolean addFriend(Friend friends) {
		try {
			sessionFactory.getCurrentSession().save(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Friend> list(int userId) {
		//String hql = "FROM Friend where friendId = "+userId;
		return sessionFactory.getCurrentSession().createQuery("FROM Friend where friendId = "+userId).list();
	}

	@Override
	@Transactional
	public boolean updateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Transactional
	public List<User_Detail> myFriends(int id) {
		String selectQuery = "SELECT * FROM User_Detail WHERE userId IN (SELECT userId1 FROM Friend WHERE (userId2 = :id OR userId1 = :id) AND STATUS = 'APPROVED' UNION SELECT userId2 FROM Friend WHERE (userId2 = :id OR userId1 = :id) AND STATUS = 'APPROVED')";
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,User_Detail.class)
						.setParameter("id", id)
							.getResultList();
	}


	}

	
